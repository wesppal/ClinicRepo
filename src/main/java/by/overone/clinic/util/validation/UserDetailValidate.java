package by.overone.clinic.util.validation;

import by.overone.clinic.model.UserDetail;

public class UserDetailValidate {
    private final static String NAME_REGEX = "[A-Za-z]+";
    private final static String SURNAME_REGEX = "[A-Za-z]+";
    private final static String ADDRESS_REGEX = "^[\\w]+\\,\\s[\\w]+\\s\\d+.\\d+$";
    private final static String PHONE_NUMBER_REGEX = "^[+]+[\\d]{12}";

    public static boolean validateDetails(UserDetail userDetail) {
        return validateName(userDetail.getName()) && validateSurname(userDetail.getSurname()) &&
                validateAddress(userDetail.getAddress()) && validatePhone(userDetail.getPhoneNumber());
    }

    private static boolean validateName(String name) {
        return !name.isBlank() && !name.isEmpty() && name.matches(NAME_REGEX);
    }

    private static boolean validateSurname(String surname) {
        return !surname.isBlank() && !surname.isEmpty() && surname.matches(SURNAME_REGEX);
    }

    private static boolean validateAddress(String address) {
        return !address.isBlank() && !address.isEmpty() && address.matches(ADDRESS_REGEX);
    }

    private static boolean validatePhone(String phone) {
        return !phone.isBlank() && !phone.isEmpty() && phone.matches(PHONE_NUMBER_REGEX);
    }
}
