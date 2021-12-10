package by.overone.clinic.dao.impl;

import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.model.Role;
import by.overone.clinic.model.User;
import by.overone.clinic.util.DBConnect;
import by.overone.clinic.util.constant.UserConst;
import by.overone.clinic.util.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserDAOImpl implements UserDAO {
    private static Connection connection;

    private final static String GET_ALL_USERS_SQL = "SELECT * FROM user where status!='deleted'";
    private final static String ADD_NEW_USER_SQL = "INSERT INTO user VALUE (0,null,null,?,?,?,null,?,?)";
    private final static String GET_USER_BY_ID_SQL = "SELECT * FROM user WHERE id=(?)";
    private final static String ADD_USER_DETAILS_SQL = "INSERT INTO details (user_id) VALUES (?)";
    private final static String REMOVE_USER_SQL = "UPDATE user SET status =(?) WHERE id=(?)";


    @Override
    public List<User> getAllUsers() throws SQLException {
        connection = DBConnect.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_ALL_USERS_SQL);

        List<User> users = new ArrayList<>();

        Long id;
        String name;
        String surname;
        String login;
        String password;
        String email;
        String phoneNumber;
        Role role;
        String status;

        while (resultSet.next()) {
            User user = new User();

            id = resultSet.getLong(UserConst.ID);
            name = resultSet.getString(UserConst.NAME);
            surname = resultSet.getNString(UserConst.SURNAME);
            login = resultSet.getString(UserConst.LOGIN);
            password = resultSet.getString(UserConst.PASSWORD);
            email = resultSet.getString(UserConst.EMAIL);
            phoneNumber = resultSet.getString(UserConst.PHONENUMBER);
            role = Role.valueOf(resultSet.getString(UserConst.ROLE).toUpperCase(Locale.ROOT));
            status = resultSet.getString(UserConst.STATUS);

            user.setId(id);
            user.setName(name);
            user.setSurname(surname);
            user.setLogin(login);
            user.setPassword(password);
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);
            user.setRole(role);
            user.setStatus(status);

            users.add(user);
        }
        connection.close();
        return users;
    }

    @Override
    public User getUserById(long id) throws SQLException, DAOException {
        User user = new User();
        try {
            Connection connection = DBConnect.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID_SQL);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return user;
            }

            String name = resultSet.getString(UserConst.NAME);
            String surname = resultSet.getNString(UserConst.SURNAME);
            String login = resultSet.getString(UserConst.LOGIN);
            String email = resultSet.getString(UserConst.EMAIL);
            String phoneNumber = resultSet.getString(UserConst.PHONENUMBER);
            Role role = Role.valueOf(resultSet.getString(UserConst.ROLE).toUpperCase(Locale.ROOT));
            String status = resultSet.getString(UserConst.STATUS);

            user.setId(id);
            user.setName(name);
            user.setSurname(surname);
            user.setLogin(login);
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);
            user.setRole(role);
            user.setStatus(status);
        } catch (SQLException e) {
            throw new DAOException("UserDAOImpl. GetUserById failed.", e);
        }

        return user;
    }

    @Override
    public User getUserByFullName(String fullName) throws SQLException {
        return null;
    }

    @Override
    public User addUser(User user) throws DAOException {
        try {
            connection = DBConnect.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_USER_SQL, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, Role.NEWBIE.toString());
            preparedStatement.setString(5, UserConst.STATUS_IDENTIFY);

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();


            while (resultSet.next()) {
                user.setId(resultSet.getLong(1));
                preparedStatement = connection.prepareStatement(ADD_USER_DETAILS_SQL);
                preparedStatement.setLong(1, user.getId());
                preparedStatement.executeUpdate();
            }


            connection.commit();
        } catch (SQLException e) {

            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new DAOException("UserDAO. AddUser. User is not added.", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    @Override
    public void removeUserById(long id) throws DAOException {
        try {
            connection = DBConnect.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER_SQL);
            preparedStatement.setString(1, UserConst.STATUS_DELETED);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DAOException("UserDAOImpl. Remove failed.");
            }
        }
    }

}
