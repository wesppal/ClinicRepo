package by.overone.clinic;


import by.overone.clinic.dao.impl.UserDAOImpl;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.service.UserService;
import by.overone.clinic.service.impl.UserServiceImpl;
import by.overone.clinic.util.exception.DAOException;
import by.overone.clinic.util.exception.ServiceExceptions;
import by.overone.clinic.util.exception.ValidationException;

import java.sql.*;


public class Main {
    public static void main(String[] args) throws SQLException, DAOException {
        UserService userService = new UserServiceImpl();

        //test service addUser
/*        UserRegistrationDTO userRegistrationDTO= new UserRegistrationDTO();
        userRegistrationDTO.setLogin("TestUser4");
        userRegistrationDTO.setPassword("tested4");
        userRegistrationDTO.setEmail("Test4@mail.ru");


        try {
            userService.addUser(userRegistrationDTO);
        } catch (ServiceExceptions | ValidationException e) {
            e.printStackTrace();
        }

 */
        //test service getAllUsers
        UserDAOImpl userDAO = new UserDAOImpl();
/*
        try {
            userDAO.removeUserById(1);
        } catch (DAOException e) {
            e.printStackTrace();
        }
*/


//        try {
//            userService.removeUserByLogin("TestUser");
//        } catch (DAOException e) {
//            throw new SQLException("Remove failed.");
//        }
        System.out.println("By id " + userDAO.getUserById(4));
        userDAO.getAllUsers().stream().forEach(System.out::println);
    }
}
