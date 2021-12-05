package by.overone.clinic;

import by.overone.clinic.dao.impl.UserDAOImpl;
import by.overone.clinic.model.Role;
import by.overone.clinic.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws SQLException {


        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.getAllUsers();

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
