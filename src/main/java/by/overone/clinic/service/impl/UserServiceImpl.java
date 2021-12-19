package by.overone.clinic.service.impl;

import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.dao.impl.UserDAOImpl;
import by.overone.clinic.dto.UserDataDTO;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.model.InfoAllUser;
import by.overone.clinic.model.User;
import by.overone.clinic.model.UserDetail;
import by.overone.clinic.service.UserService;
import by.overone.clinic.util.exception.DAOException;
import by.overone.clinic.util.exception.ServiceExceptions;
import by.overone.clinic.util.exception.ValidationException;
import by.overone.clinic.util.validation.UserDetailValidate;
import by.overone.clinic.util.validation.UserValidate;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public List<UserDataDTO> getAllUsers() throws ServiceExceptions {
        List<User> users;
        try {
            users = userDAO.getAllUsers();
        } catch (DAOException e) {
            throw new ServiceExceptions("UserServiceImpl. getAllUsers failed. Not connection.");
        }
        List<UserDataDTO> usersDataDTO;
        usersDataDTO = users.stream().map(user -> new UserDataDTO(user.getId(),user.getLogin(),user.getEmail()))
                .collect(Collectors.toList());
        return usersDataDTO;
    }

    @Override
    public UserDataDTO getUserById(long id) throws ServiceExceptions, ValidationException {
        UserDataDTO userDataDTO = new UserDataDTO();
        User user;
        if (!UserValidate.validateId(id)) {
            throw new ValidationException("Incorrect user id.");
        }
        try {
            user = userDAO.getUserById(id);
        } catch (DAOException e) {
            throw new ServiceExceptions("UserServiceImpl. getUserById failed. User not found.");
        }

        userDataDTO.setId(user.getId());
        userDataDTO.setLogin(user.getLogin());
        userDataDTO.setEmail(user.getEmail());
        return userDataDTO;
    }

    @Override
    public UserDataDTO getUserByFullname(String name, String surname) throws ServiceExceptions, ValidationException {
        if (UserValidate.validateFullnameDate(name, surname)) {
            throw new ValidationException("Incorrect user details: name, surname.");
        }

        User user;
        UserDataDTO userDataDTO = new UserDataDTO();

        try {
            user = userDAO.getUserByFullName(name, surname);
        } catch (DAOException e) {
            throw new ServiceExceptions("UserServiceImpl. getUserById failed. User not found.");
        }

        userDataDTO.setId(user.getId());
        userDataDTO.setLogin(user.getLogin());
        userDataDTO.setEmail(user.getEmail());
        return userDataDTO;
    }

    @Override
    public UserDataDTO addUser(UserRegistrationDTO userRegistrationDTO) throws ServiceExceptions, ValidationException {
        User user = new User();
        user.setLogin(userRegistrationDTO.getLogin());
        user.setPassword(userRegistrationDTO.getPassword());
        user.setEmail(userRegistrationDTO.getEmail());

        if (!UserValidate.validateRegistrationData(user)) {
            throw new ValidationException("Incorrect registration data.");
        }

        user.setPassword(DigestUtils.md5Hex(userRegistrationDTO.getPassword()));

        try {
            user = userDAO.addUser(user);
        } catch (DAOException e) {
            throw new ServiceExceptions("UserService. AddUser.User not added.", e);
        }
        return new UserDataDTO(0, user.getLogin(), user.getEmail());
    }

    @Override
    public void removeUserById(long id) throws ServiceExceptions, ValidationException {
        UserDataDTO userDataDTO;
        userDataDTO = getUserById(id);
        try {
            userDAO.removeUserById(userDataDTO.getId());
        } catch (DAOException e) {
            throw new ServiceExceptions("UserServiceImpl. removeUserById failed.");
        }
    }

    @Override
    public boolean updateUserDetails(UserDetail userDetail) throws ServiceExceptions, ValidationException {
        if (!UserDetailValidate.validateDetails(userDetail)) {
            throw new ValidationException("Incorrect details data");
        }
        try {
            userDAO.updateUserDetails(userDetail);
        } catch (DAOException e) {
            throw new ServiceExceptions("UserServiceImpl. UpdateUserDetail failed.");
        }
        return true;
    }

    @Override
    public UserDetail getUserDetailById(long id) throws ServiceExceptions, ValidationException {
        if (!UserValidate.validateId(id)) {
            throw new ValidationException("Incorrect user id.");
        }
        UserDetail userDetail;
        try {
            userDetail = userDAO.getUserDetailById(id);
        } catch (DAOException e) {
            throw new ServiceExceptions("UserServiceImpl. GetUserDetailById failed.");
        }
        return userDetail;
    }

    @Override
    public InfoAllUser getAllUserInfo(long id) throws ServiceExceptions, ValidationException {
        if (!UserValidate.validateId(id)) {
            throw new ValidationException("Incorrect user id.");
        }

        InfoAllUser user;
        try {
            user = userDAO.getAllUserInfo(id);

        } catch (DAOException e) {
            throw new ServiceExceptions("UserServiceImpl. getInfoAllUser failed.");
        }
        return user;
    }
}
