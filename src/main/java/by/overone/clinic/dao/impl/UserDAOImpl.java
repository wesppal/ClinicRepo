package by.overone.clinic.dao.impl;

import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.model.Role;
import by.overone.clinic.model.User;
import by.overone.clinic.util.constant.UserConst;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserDAOImpl implements UserDAO {
    private static Connection connection;
    private final static String GET_ALL_USERS_SQL = "SELECT * FROM user";
    private final static String ADD_NEW_USER_SQL = "INSERT INTO user VALUE (0,null,null,?,?,?,null,null)";


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
    public List<User> getAllUsers() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_ALL_USERS_SQL);

        List<User> users = new ArrayList<>();

        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong(UserConst.ID));
            user.setName(resultSet.getString(UserConst.NAME));
            user.setSurname(resultSet.getString(UserConst.SURNAME));
            user.setLogin(resultSet.getString(UserConst.LOGIN));
            user.setPassword(resultSet.getString(UserConst.PASSWORD));
            user.setEmail(resultSet.getString(UserConst.EMAIL));
            user.setPhoneNumber(resultSet.getString(UserConst.PHONENUMBER));
            user.setRole(Role.valueOf(resultSet.getString(UserConst.ROLE).toUpperCase(Locale.ROOT)));
            users.add(user);
        }
        statement.close();
        return users;
    }

    @Override
    public User getUserById(long id) throws SQLException {
        return null;
    }

    @Override
    public User getUserByFullName(String fullName) throws SQLException {
        return null;
    }

    @Override
    public User addUser(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_USER_SQL);

        preparedStatement.setString(1,user.getLogin());
        preparedStatement.setString(2,user.getPassword());
        preparedStatement.setString(3, user.getEmail());

        preparedStatement.executeUpdate();
        preparedStatement.close();
        return user;
    }

    @Override
    public User removeUserById(long id) throws SQLException {
        return null;
    }

}
