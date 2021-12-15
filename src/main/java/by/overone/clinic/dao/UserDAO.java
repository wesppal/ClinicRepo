package by.overone.clinic.dao;

import by.overone.clinic.model.InfoAllUser;
import by.overone.clinic.model.User;
import by.overone.clinic.model.UserDetail;
import by.overone.clinic.util.exception.DAOException;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers() throws DAOException;
    User getUserById(long id) throws DAOException;
    User getUserByFullName (String name, String surname) throws DAOException;
    User addUser(User user) throws DAOException;
    void removeUserById(long id) throws DAOException;
    boolean UpdateUserDetails(UserDetail userDetail) throws DAOException;
    UserDetail getUserDetailById(long id) throws DAOException;
    InfoAllUser getInfoAllUser(long id) throws DAOException;

}
