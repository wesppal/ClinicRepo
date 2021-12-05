package by.overone.clinic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class UserDataDTO {
    private long id;
    private String login;
    private String email;
}
