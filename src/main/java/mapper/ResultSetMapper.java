package mapper;

import annotation.Column;
import annotation.Entity;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

//import org.apache.log4j.Logger;

public class ResultSetMapper<T> {

//    private static final Logger logger = Logger.getLogger(ResultSetMapper.class);

    public List<T> mapRow(ResultSet rs, Class<T> clazz) {
        List<T> results = new ArrayList<>();
        try {
            if (clazz.isAnnotationPresent(Entity.class)) {
                ResultSetMetaData resultSetMetaData = rs.getMetaData();
                Field[] fields = clazz.getDeclaredFields();
                while (rs.next()) {
                    T object = clazz.newInstance();
                    for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
                        String columnName = resultSetMetaData.getColumnName(i + 1);
                        Object columnValue = rs.getObject(i + 1);
//                        for (Field field : fields) {
//                            if(field.isAnnotationPresent(Column.class)){
//                                Column column = field.getAnnotation(Column.class);
//                                if(column.name().equals(columnName) && columnValue != null) {
//                                    BeanUtils.setProperty(object, field.getName(), columnValue);
//                                    break;
//                                }
//                            }
//                        }
                        ColumnModel columnModel = new ColumnModel();
                        columnModel.setColumnName(columnName);
                        columnModel.setColumnValue(columnValue);
                        convertResultSetToEntity(fields, columnModel, object);
                        Class<?> parentClass = clazz.getSuperclass();
                        while (parentClass != null) {
                            Field[] fieldParents = parentClass.getDeclaredFields();
                            convertResultSetToEntity(fieldParents, columnModel, object);
                            parentClass = parentClass.getSuperclass();
                        }
                    }
                    results.add(object);
                }
            }
        } catch (Exception e) {
//            logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }

        return results;
    }

    private void convertResultSetToEntity(Field[] fields, ColumnModel columnModel, Object... objects) {
        T object = (T) objects[0];
        try {
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column column = field.getAnnotation(Column.class);
                    if (column.name().equals(columnModel.getColumnName()) && columnModel.getColumnValue() != null) {
                        BeanUtils.setProperty(object, field.getName(), columnModel.getColumnValue());
                        break;
                    }
                }
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }

    }

    static class ColumnModel {
        private String columnName;
        private Object columnValue;

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public Object getColumnValue() {
            return columnValue;
        }

        public void setColumnValue(Object columnValue) {
            this.columnValue = columnValue;
        }
    }
}
