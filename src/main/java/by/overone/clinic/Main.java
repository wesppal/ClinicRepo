package by.overone.clinic;

import by.overone.clinic.dao.impl.UserDAOImpl;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.model.User;
import by.overone.clinic.service.UserService;
import by.overone.clinic.service.impl.UserServiceImpl;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {


        UserDAOImpl userDAO = new UserDAOImpl();
        UserService userService = new UserServiceImpl() ;

        List<User> users = userDAO.getAllUsers();
        users.stream().forEach(System.out::println);

        UserRegistrationDTO userRegistrationDTO= new UserRegistrationDTO();
        userRegistrationDTO.setLogin("Vasya008");
        userRegistrationDTO.setPassword("11112222");
        userRegistrationDTO.setEmail("vasya008@mail.ru");

        userService.addUser(userRegistrationDTO);

//        String userId = "4";
//
//        Statement statement = connection.createStatement();
//        String sql = "SELECT * FROM user where id="+userId;
//
//        ResultSet resultSet = statement.executeQuery(sql);
//        String insert = "INSERT INTO user VALUES (0,'"+name+"','"+surname+"','"+login+"','"+email+"','"+phoneNumber")";
//        Statement statement1 = connection.createStatement();
//
//        String insert = "INSERT INTO user VALUES (0,?,?,?,?,?)";
//        PreparedStatement preparedStatement1 = connection.prepareStatement(insert);
//        preparedStatement1.setString(1,"Gosha");
//        preparedStatement1.setString(2,"Vosha");
//        preparedStatement1.setString(3,"Gosha1");
//        preparedStatement1.setString(4,"Gosha@asd.by");
//        preparedStatement1.setString(5,"+375757515151");
//        preparedStatement1.executeUpdate();









    }
}
