package by.overone.clinic.util.validation;

import by.overone.clinic.model.User;

public class UserValidate {
    private final static String LOGIN_REGEX = "^[\\w]{4,10}$";
    private final static String EMAIL_REGEX = "^[\\S]+@[\\w]+\\.+[a-z]+$";
    private final static String PASS_REGEX = "[a-zA-Z\\d]{4,16}";

    public static boolean validateRegistrationData(User user) {
        return validateLogin(user.getLogin()) && validateEmail(user.getEmail()) && validatePassword(user.getPassword());
    }

    private static boolean validateLogin(String login) {
        return !login.isBlank() && !login.isEmpty() && login != null && login.matches(LOGIN_REGEX);
    }

    private static boolean validateEmail(String email) {
        return !email.isBlank() && !email.isEmpty() && email != null && email.matches(EMAIL_REGEX);
    }

    private static boolean validatePassword(String password) {
        return !password.isBlank() && !password.isEmpty() && password != null && password.matches(PASS_REGEX);
    }
}
