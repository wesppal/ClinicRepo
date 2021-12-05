package by.overone.clinic.dao.impl;

import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.model.Role;
import by.overone.clinic.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserDAOImpl implements UserDAO {
    private static Connection connection;
    private final static String GET_ALL_USERS_SQL = "SELECT * FROM user";

    static {
        try {
            String url = "jdbc:mysql://localhost:3306/clinicDB";
            String dbUser = "root";
            String password = "root";
            connection = DriverManager.getConnection(url, dbUser, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void getAllUsers() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_ALL_USERS_SQL);

        List<User> users = new ArrayList<>();

        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            user.setPhoneNumber(resultSet.getString("phoneNumber"));
            user.setRole(Role.valueOf(resultSet.getString("role").toUpperCase(Locale.ROOT)));
            users.add(user);
        }
        users.stream().forEach(System.out::println);
        statement.close();
    }

}
