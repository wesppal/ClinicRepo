package by.overone.clinic;


import by.overone.clinic.dao.impl.UserDAOImpl;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.model.UserDetail;
import by.overone.clinic.service.UserService;
import by.overone.clinic.service.impl.UserServiceImpl;
import by.overone.clinic.util.exception.DAOException;
import by.overone.clinic.util.exception.ServiceExceptions;
import by.overone.clinic.util.exception.ValidationException;

import java.sql.*;


public class Main {
    public static void main(String[] args) throws DAOException, ServiceExceptions, ValidationException {
        UserService userService = new UserServiceImpl();

        //test service addUser
//        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
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


//        userService.removeUserById(5);
//        System.out.println("Find user - " + userService.getUserById(0));

//        System.out.println("By id " + userDAO.getUserById(3));

//        System.out.println(userService.getUserByFullname("Gon", "Friks"));
//        userDAO.getAllUsers().stream().forEach(System.out::println);
//
        UserDetail userDetail = new UserDetail();
        userDetail.setName("Polyna");
        userDetail.setSurname("Viera");
        userDetail.setAddress("Minsk, ulica 12-5");
        userDetail.setPhoneNumber("+375753332214");
        userDetail.setId(17);
//        userDAO.UpdateUserDetails(userDetail);

//        System.out.println(userDAO.UpdateUserDetails(userDetail));
//        System.out.println(userDAO.getUserDetailById(3));

//        System.out.println(userService.getUserDetailById(12));
        userService.updateUserDetails(userDetail);
    }
}
