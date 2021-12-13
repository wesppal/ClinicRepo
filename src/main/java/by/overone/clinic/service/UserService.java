package by.overone.clinic.service;

import by.overone.clinic.dto.UserDataDTO;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.model.UserDetail;
import by.overone.clinic.util.exception.DAOException;
import by.overone.clinic.util.exception.ServiceExceptions;
import by.overone.clinic.util.exception.ValidationException;

import java.util.List;

public interface UserService {
    List<UserDataDTO> getAllUsers() throws ServiceExceptions, DAOException;
    UserDataDTO getUserById(long id) throws ServiceExceptions, ValidationException;
    UserDataDTO getUserByFullname(String name, String surname) throws ServiceExceptions, ValidationException;
    UserDataDTO addUser(UserRegistrationDTO userRegistrationDTO) throws ServiceExceptions, ValidationException;
    void removeUserById(long id) throws DAOException, ServiceExceptions, ValidationException;
    boolean updateUserDetails(UserDetail userDetail) throws ServiceExceptions, ValidationException;
    public UserDetail getUserDetailById(long id) throws ServiceExceptions, ValidationException;
}
