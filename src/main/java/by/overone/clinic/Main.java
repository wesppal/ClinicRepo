package by.overone.clinic;


import by.overone.clinic.dao.PetDAO;
import by.overone.clinic.dao.impl.PetDAOImpl;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.model.Pet;
import by.overone.clinic.service.PetService;
import by.overone.clinic.service.UserService;
import by.overone.clinic.service.impl.PetServiceImpl;
import by.overone.clinic.service.impl.UserServiceImpl;
import by.overone.clinic.util.exception.DAOException;
import by.overone.clinic.util.exception.ServiceException;
import by.overone.clinic.util.exception.ValidationException;


public class Main {
    public static void main(String[] args) throws DAOException, ServiceException, ValidationException {
        PetDAO petDAO = new PetDAOImpl();
        PetService petService = new PetServiceImpl();
        Pet pet = new Pet();
        pet.setName("goldy");
        pet.setAge(1);
        pet.setType("fish");
        pet.setOwner("Gon");
        pet.setUser_id(3);
        petService.updatePet(7,pet);
//        petDAO.updatePet(3,pet);
    }
}
