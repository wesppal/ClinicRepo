package by.overone.clinic.util.validation;

import by.overone.clinic.model.User;

public class UserValidate {
    private final static String LOGIN_REGEX = "^[\\w]{4,10}$";
    private final static String EMAIL_REGEX = "^[\\S]+@[\\w]+\\.+[a-z]+$";
    private final static String PASS_REGEX = "[a-zA-Z]{2,16}";
    private final static String FULLNAME_REGEX = "[a-zA-Z\\d]{4,16}";

    public static boolean validateRegistrationData(User user) {
        return validateLogin(user.getLogin()) && validateEmail(user.getEmail()) && validatePassword(user.getPassword());
    }

    public static boolean validateId(long id) {
        return (id > 0) ? true : false;
    }

    public static boolean validateFullnameDate(String name, String surname) {
        return validateName(name) && validateSurname(surname);
    }

    private static boolean validateLogin(String login) {
        return !login.isBlank() && !login.isEmpty() && login.matches(LOGIN_REGEX);
    }

    private static boolean validateEmail(String email) {
        return !email.isBlank() && !email.isEmpty() && email.matches(EMAIL_REGEX);
    }

    private static boolean validatePassword(String password) {
        return !password.isBlank() && !password.isEmpty() && password.matches(PASS_REGEX);
    }

    private static boolean validateName(String name) {
        return name.matches(FULLNAME_REGEX) && !name.isEmpty() && !name.isBlank();
    }

    private static boolean validateSurname(String surname) {
        return surname.matches(FULLNAME_REGEX) && !surname.isEmpty() && !surname.isBlank();
    }
}
