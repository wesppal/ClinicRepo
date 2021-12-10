package by.overone.clinic.dto;

import by.overone.clinic.util.constant.UserConst;
import lombok.Data;
@Data
public class UserRemoveDTO {
    private long id;
    private String login;
    private String email;
    private String status = UserConst.STATUS_DELETED;

}
