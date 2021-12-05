package by.overone.clinic.dao;

import by.overone.clinic.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<User> getAllUsers() throws SQLException;
    User getUserById(long id) throws SQLException;
    User getUserByFullName (String fullName) throws SQLException;
    User addUser(User user) throws SQLException;
    User removeUserById(long id) throws SQLException;

}
