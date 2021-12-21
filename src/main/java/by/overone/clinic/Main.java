package by.overone.clinic;


import by.overone.clinic.dao.PetDAO;
import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.dao.impl.PetDAOImpl;
import by.overone.clinic.dao.impl.UserDAOImpl;
import by.overone.clinic.model.Pet;
import by.overone.clinic.model.Status;
import by.overone.clinic.model.UserDetail;
import by.overone.clinic.service.PetService;
import by.overone.clinic.service.impl.PetServiceImpl;
import by.overone.clinic.util.exception.DAOException;
import by.overone.clinic.util.exception.ServiceException;
import by.overone.clinic.util.exception.ValidationException;


public class Main {
    public static void main(String[] args) throws DAOException, ServiceException, ValidationException {
        PetDAO petDAO = new PetDAOImpl();
        Pet pet = new Pet();
        pet.setName("Reks");
        pet.setType("dog");
        pet.setUser_id(11);
        pet.setOwner("Polyna");
        pet.setAge(2);
        pet.setUser_id(11);
        System.out.println(petDAO.addPet(11,pet));

    }
}
