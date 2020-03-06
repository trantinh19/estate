package repository.impl;

import annotation.Entity;
import annotation.Table;
import mapper.ResultSetMapper;
import org.apache.commons.lang3.StringUtils;
import paging.Pageable;
import repository.JpaRepository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class SimpleJpaRepository<T> implements JpaRepository<T> {

    private Class<T> clazz;

    @SuppressWarnings("unchecked")
    public SimpleJpaRepository() {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;
        clazz = (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

    @Override
    public List<T> findAll(Map<String, Object> properties, Pageable pageable, Object... where) {
        String tableName = "";
        if(clazz.isAnnotationPresent(Entity.class) && clazz.isAnnotationPresent(Table.class)){
            Table tableClass = clazz.getAnnotation(Table.class);
            tableName = tableClass.name();
        }
        StringBuilder sql = new StringBuilder("select * from "+tableName+" A where 1=1 ");
        sql = createSQLfindAll(sql, properties);
        if(where != null && where.length > 0) {
           sql.append(where[0]);
        }
        sql.append(" limit "+pageable.getOffset()+", "+pageable.getSize()+"");
        ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
        Connection connection = null;
//        PreparedStatement statement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = EntityManagerFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql.toString());
            return resultSetMapper.mapRow(resultSet, this.clazz);
        } catch (SQLException | ClassNotFoundException e) {
            return null;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                return null;
            }
        }
    }

    private StringBuilder createSQLfindAll(StringBuilder whereCondition , Map<String, Object> params) {
        if(params != null && params.size() > 0) {
            String[] keys = new String[params.size()];
            Object[] values = new Object[params.size()];
            int i = 0;
            for(Map.Entry<String, Object> item : params.entrySet()) {
                item.getKey();
                keys[i] = item.getKey();
                values[i] = item.getValue();
                i++;
            }
            for (int i1 = 0; i1 < keys.length ; i1++) {
                if (values[i1] instanceof String && StringUtils.isNotBlank(values[i1].toString())) {
                    whereCondition.append("AND LOWER(A."+keys[i1]+") LIKE '%"+values[i1].toString()+"%' ");
                } else if (values[i1] instanceof Integer && values[i1] != null) {
                    whereCondition.append("AND LOWER(A."+keys[i1]+") = "+values[i1]+" ");
                } else if (values[i1] instanceof Long && values[i1] != null) {
                    whereCondition.append("AND LOWER(A."+keys[i1]+") = "+values[i1]+" ");
                }
            }
        }
        return whereCondition;
    }
}
