package by.overone.clinic.service.impl;

import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.dao.impl.UserDAOImpl;
import by.overone.clinic.dto.UserDataDTO;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.dto.UserRemoveDTO;
import by.overone.clinic.model.User;
import by.overone.clinic.service.UserService;
import by.overone.clinic.util.exception.DAOException;
import by.overone.clinic.util.exception.ServiceExceptions;
import by.overone.clinic.util.exception.ValidationException;
import by.overone.clinic.util.validation.UserValidate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public List<UserDataDTO> getAllUsers() throws SQLException {
        List<User> users = userDAO.getAllUsers();
        List<UserDataDTO> usersDataDTO = new ArrayList<>();

        for (int i = 0; i < users.size(); i++) {
            UserDataDTO userDataDTO = new UserDataDTO(users.get(i).getId(), users.get(i).getLogin(),
                    users.get(i).getEmail());
            usersDataDTO.add(userDataDTO);
        }
        return usersDataDTO;
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
        try {
            user = userDAO.addUser(user);
        } catch (DAOException | SQLException e) {
            throw new ServiceExceptions("UserService. AddUser.User not added.", e);
        }
        return new UserDataDTO(0, user.getLogin(), user.getEmail());
    }

    @Override
    public UserRemoveDTO removeUserByLogin(String login) throws SQLException, DAOException {
        UserRemoveDTO userRemoveDTO = new UserRemoveDTO();
        List<User> users = userDAO.getAllUsers();
        for (int i = 0; i < users.size(); i++) {
            User tempUser = users.get(i);
            if (login.equals(tempUser.getLogin())) {
                userRemoveDTO.setId(tempUser.getId());
                userRemoveDTO.setLogin(login);
                userRemoveDTO.setEmail(tempUser.getEmail());
                userDAO.removeUserById(tempUser.getId());
                return userRemoveDTO;
            }
        }
        return null;
    }

}
