package by.overone.clinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InfoAllUser {
    private long id;
    private String login;
    private String email;
    private Role role;
    private Status status;
    private String name;
    private String surname;
    private String address;
    private String phoneNumber;


}
