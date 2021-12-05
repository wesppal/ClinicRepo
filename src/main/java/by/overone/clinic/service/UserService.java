package by.overone.clinic.service;

import by.overone.clinic.dto.UserDataDTO;
import by.overone.clinic.dto.UserRegistrationDTO;

import java.util.List;

public interface UserService {
    List<UserDataDTO> getAllUsers();
    UserDataDTO addUser(UserRegistrationDTO userRegistrationDTO);
}
