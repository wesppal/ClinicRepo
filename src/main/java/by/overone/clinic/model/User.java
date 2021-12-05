package by.overone.clinic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
    private String phoneNumber;
    private Role role;


}
