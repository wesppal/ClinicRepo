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
    public static void main(String[] args) throws DAOException, ServiceExceptions {
        UserService userService = new UserServiceImpl();

        //test service addUser
//        UserRegistrationDTO userRegistrationDTO= new UserRegistrationDTO();
//        userRegistrationDTO.setLogin("TestUser5");
//        userRegistrationDTO.setPassword("tested4");
//        userRegistrationDTO.setEmail("Test5@mail.ru");
//
//
//        try {
//            userService.addUser(userRegistrationDTO);
//        } catch (ServiceExceptions | ValidationException e) {
//            e.printStackTrace();
//        }

        UserDAOImpl userDAO = new UserDAOImpl();

        //test service getAllUsers

//        try {
//            userDAO.removeUserById(17);
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }


        userService.removeUserById(3);
//        System.out.println("Find user - " + userService.getUserById(4));

//        System.out.println("By id " + userDAO.getUserById(3));
        userDAO.getAllUsers().stream().forEach(System.out::println);
    }
}
