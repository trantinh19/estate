package repository.impl;//package com.trantinh.repository.impl;
//
//import com.trantinh.dto.UserDTO;
//import com.trantinh.repository.IUserRepository;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserRepository implements IUserRepository {
//    @Override
//    public List<UserDTO> findAll() {
//        String sql = "select * from user";
//        List<UserDTO> results = new ArrayList<>();
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = EntityManagerFactory.getConnection();
//            statement = connection.prepareStatement(sql);
////            setParameter(statement, parameters);
//            resultSet = statement.executeQuery();
//            while (resultSet.next()) {
////                results.add(rowMapper.mapRow(resultSet));
//                UserDTO userDTO = new UserDTO();
//                userDTO.setUserName(resultSet.getString("username"));
////                userDTO.setUserName(resultSet.getString("fullName"));
//                results.add(userDTO);
//            }
//            return results;
//        } catch (SQLException e) {
//            return null;
//        } finally {
//            try {
//                if (connection != null) {
//                    connection.close();
//                }
//                if (statement != null) {
//                    statement.close();
//                }
//                if (resultSet != null) {
//                    resultSet.close();
//                }
//            } catch (SQLException e) {
//                return null;
//            }
//        }
//    }
//}
