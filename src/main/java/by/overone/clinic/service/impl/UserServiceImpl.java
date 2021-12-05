package by.overone.clinic.service.impl;

import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.dao.impl.UserDAOImpl;
import by.overone.clinic.dto.UserDataDTO;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.model.User;
import by.overone.clinic.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDAO userDAO = new UserDAOImpl();

    @Override
    public List<UserDataDTO> getAllUsers() {
        return null;
    }

    @Override
    public UserDataDTO addUser(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        user.setLogin(userRegistrationDTO.getLogin());
        user.setPassword(userRegistrationDTO.getPassword());
        user.setEmail(userRegistrationDTO.getEmail());
        try {
            user = userDAO.addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new UserDataDTO(0,user.getLogin(),user.getEmail());
    }

}
