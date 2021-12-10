package by.overone.clinic.service;

import by.overone.clinic.dto.UserDataDTO;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.dto.UserRemoveDTO;
import by.overone.clinic.util.exception.DAOException;
import by.overone.clinic.util.exception.ServiceExceptions;
import by.overone.clinic.util.exception.ValidationException;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    List<UserDataDTO> getAllUsers() throws SQLException;
    UserDataDTO addUser(UserRegistrationDTO userRegistrationDTO) throws ServiceExceptions, ValidationException;
    UserRemoveDTO removeUserByLogin(String login) throws SQLException, DAOException;
}
