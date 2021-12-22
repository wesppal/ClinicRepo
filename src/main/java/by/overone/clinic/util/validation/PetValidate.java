package by.overone.clinic.util.validation;

public class PetValidate {

    public static boolean validateId(long id) {
        return (id > 0) ? true : false;
    }
}
