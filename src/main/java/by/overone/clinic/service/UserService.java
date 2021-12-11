package by.overone.clinic.service;

import by.overone.clinic.dto.UserDataDTO;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.util.exception.DAOException;
import by.overone.clinic.util.exception.ServiceExceptions;
import by.overone.clinic.util.exception.ValidationException;

import java.util.List;

public interface UserService {
    List<UserDataDTO> getAllUsers() throws ServiceExceptions, DAOException;
    UserDataDTO getUserById(long id) throws ServiceExceptions;
    UserDataDTO addUser(UserRegistrationDTO userRegistrationDTO) throws ServiceExceptions, ValidationException;
    UserDataDTO removeUserById(long id) throws DAOException, ServiceExceptions;
}
