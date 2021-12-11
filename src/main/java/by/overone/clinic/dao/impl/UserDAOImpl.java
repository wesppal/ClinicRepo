package by.overone.clinic.dao.impl;

import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.model.Role;
import by.overone.clinic.model.Status;
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
    private final static String ADD_NEW_USER_SQL = "INSERT INTO user VALUE (0,?,?,?,?,?)";
    private final static String GET_USER_BY_ID_SQL = "SELECT * FROM user WHERE id=(?)";
    private final static String ADD_USER_DETAILS_SQL = "INSERT INTO details (user_id) VALUES (?)";
    private final static String REMOVE_USER_SQL = "UPDATE user SET status =(?) WHERE id=(?)";
    private final static String GET_USER_BY_FULLNAME = "SELECT * FROM user " +
            "JOIN details on (user_id)=id where name=? AND surname=?";


    @Override
    public List<User> getAllUsers() throws DAOException {
        List<User> users = new ArrayList<>();

        try {
            connection = DBConnect.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_USERS_SQL);

            long id;
            String login;
            String password;
            String email;
            Role role;
            Status status;

            while (resultSet.next()) {
                User user = new User();

                id = resultSet.getLong(UserConst.ID);
                login = resultSet.getString(UserConst.LOGIN);
                password = resultSet.getString(UserConst.PASSWORD);
                email = resultSet.getString(UserConst.EMAIL);
                role = Role.valueOf(resultSet.getString(UserConst.ROLE).toUpperCase(Locale.ROOT));
                status = Status.valueOf(resultSet.getString(UserConst.STATUS).toUpperCase(Locale.ROOT));

                user.setId(id);
                user.setLogin(login);
                user.setPassword(password);
                user.setEmail(email);
                user.setRole(role);
                user.setStatus(status);

                users.add(user);
            }
        } catch (SQLException e) {
            throw new DAOException("Not connection.", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public User getUserById(long id) throws DAOException {
        User user = new User();
        try {
            Connection connection = DBConnect.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID_SQL);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new DAOException("UserDAOImpl. GetUserById failed. This is no such user.");
            }

            String login = resultSet.getString(UserConst.LOGIN);
            String password = resultSet.getString(UserConst.PASSWORD);
            String email = resultSet.getString(UserConst.EMAIL);
            Role role = Role.valueOf(resultSet.getString(UserConst.ROLE).toUpperCase(Locale.ROOT));
            Status status = Status.valueOf(resultSet.getString(UserConst.STATUS).toUpperCase(Locale.ROOT));

            user.setId(id);
            user.setLogin(login);
            user.setPassword(password);
            user.setEmail(email);
            user.setRole(role);
            user.setStatus(status);
        } catch (SQLException e) {
            throw new DAOException("UserDAOImpl. GetUserById failed.", e);
        }
        return user;
    }

    @Override
    public User getUserByFullName(String name, String surname) throws DAOException {
        User user = new User();
        try {
            Connection connection = DBConnect.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_FULLNAME);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new DAOException("UserDAOImpl. GetUserByFullname failed. This is no such user.");
            }

            long id = resultSet.getLong(UserConst.ID);
            String login = resultSet.getString(UserConst.LOGIN);
            String password = resultSet.getString(UserConst.PASSWORD);
            String email = resultSet.getString(UserConst.EMAIL);
            Role role = Role.valueOf(resultSet.getString(UserConst.ROLE).toUpperCase(Locale.ROOT));
            Status status = Status.valueOf(resultSet.getString(UserConst.STATUS).toUpperCase(Locale.ROOT));

            user.setId(id);
            user.setLogin(login);
            user.setPassword(password);
            user.setEmail(email);
            user.setRole(role);
            user.setStatus(status);
        } catch (SQLException | DAOException e) {
            throw new DAOException("UserDAOImpl. GetUserByFullname failed.", e);
        }
        return user;
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
            preparedStatement.setString(4, Role.USER.toString());
            preparedStatement.setString(5, Status.VERIFY.toString());
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
            preparedStatement.setString(1, Status.DELETED.toString());
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
                throw new DAOException("UserDAOImpl. Already removed.");
            } catch (SQLException ex) {
                throw new DAOException("UserDAOImpl. Remove failed.");
            }
        }
    }
}
