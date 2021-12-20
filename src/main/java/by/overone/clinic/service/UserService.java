package by.overone.clinic.service;

import by.overone.clinic.dto.UserDataDTO;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.model.InfoAllUser;
import by.overone.clinic.model.UserDetail;
import by.overone.clinic.util.exception.DAOException;
import by.overone.clinic.util.exception.ServiceException;
import by.overone.clinic.util.exception.ValidationException;

import java.util.List;

public interface UserService {
    List<UserDataDTO> getAllUsers() throws ServiceException, DAOException;
    UserDataDTO getUserById(long id) throws ServiceException, ValidationException;
    UserDataDTO getUserByFullname(String name, String surname) throws ServiceException, ValidationException;
    UserDataDTO addUser(UserRegistrationDTO userRegistrationDTO) throws ServiceException, ValidationException;
    void removeUserById(long id) throws DAOException, ServiceException, ValidationException;
    boolean updateUserDetails(UserDetail userDetail) throws ServiceException, ValidationException;
    UserDetail getUserDetailById(long id) throws ServiceException, ValidationException;
    InfoAllUser getAllUserInfo(long id) throws ServiceException, ValidationException;
}
