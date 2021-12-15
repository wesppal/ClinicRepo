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

    public InfoAllUser(User user, UserDetail userDetail) {
        id = user.getId();
        login = user.getLogin();
        email = user.getEmail();
        role = user.getRole();
        status = user.getStatus();
        name = userDetail.getName();
        surname = userDetail.getSurname();
        address = userDetail.getAddress();
        phoneNumber = userDetail.getPhoneNumber();
    }
}
