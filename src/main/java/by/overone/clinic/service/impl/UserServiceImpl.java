package by.overone.clinic.service.impl;

import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.dao.impl.UserDAOImpl;
import by.overone.clinic.dto.UserDataDTO;
import by.overone.clinic.model.User;
import by.overone.clinic.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDAOImpl userDAO = new UserDAOImpl();


//    public UserDataDTO getUserInfo(long id){
//        User user = userDAO.getUserById(id);
//    }

    @Override
    public List<UserDataDTO> getAllUsers() {
 return null;
     }
}
