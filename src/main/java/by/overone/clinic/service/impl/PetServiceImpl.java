package by.overone.clinic.service.impl;

import by.overone.clinic.dao.PetDAO;
import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.dao.impl.PetDAOImpl;
import by.overone.clinic.dao.impl.UserDAOImpl;
import by.overone.clinic.model.Pet;
import by.overone.clinic.model.Status;
import by.overone.clinic.model.User;
import by.overone.clinic.service.PetService;
import by.overone.clinic.service.UserService;
import by.overone.clinic.util.exception.DAOException;
import by.overone.clinic.util.exception.ServiceException;
import by.overone.clinic.util.exception.ValidationException;
import by.overone.clinic.util.validation.PetValidate;

import java.util.ArrayList;
import java.util.List;

public class PetServiceImpl implements PetService {

    private final PetDAO petDAO = new PetDAOImpl();

    @Override
    public List<Pet> getPets() throws ServiceException {
        List<Pet> pets;
        try {
            pets = petDAO.getPets();
        } catch (DAOException e) {
            throw new ServiceException("PetServiceImpl. GetPets failed. Not connection.");
        }
        return pets;
    }

    @Override
    public Pet getPetById(long id) throws ServiceException {
        Pet pet;
        try {
            pet = petDAO.getPetById(id);
        } catch (DAOException e) {
            throw new ServiceException("PetServiceImpl. GetPetById failed. Not connection.");
        }
        return pet;
    }

    @Override
    public Pet addPet(long user_id, Pet pet) throws ServiceException, ValidationException {
        UserService userService = new UserServiceImpl();
        userService.getUserById(user_id);
        try {
            petDAO.addPet(user_id, pet);
        } catch (DAOException e) {
            throw new ServiceException("PetServiceImpl. AddPet failed. Error connection");
        }
        return null;
    }

    @Override
    public void updatePet(long id, Pet pet) throws ServiceException {
        getPetById(id);
        try {
            petDAO.updatePet(id, pet);
        } catch (DAOException e) {
            throw new ServiceException("PetServiceImpl. UpdatePet failed. Error connection");
        }
    }

    @Override
    public boolean deletePet(long id) throws ServiceException, ValidationException {
        if (!PetValidate.validateId(id)) {
            throw new ValidationException("PetServiceImpl. DeletePet failed. Id is incorrect.");
        }
        Pet pet;
        try {
            petDAO.deletePet(id);
        } catch (DAOException e) {
            throw new ServiceException("PetServiceImpl. DeletePet failed.");
        }
        pet = getPetById(id);
        return pet.getStatus().toString().equals(Status.DELETED.toString());
    }

    @Override
    public List<Pet> getPetByUserId(long user_id) throws ServiceException, ValidationException {
        UserService userService = new UserServiceImpl();
        userService.getUserById(user_id);
        List<Pet> pets = new ArrayList<>();
        try {
            pets = petDAO.getPetByUserId(user_id);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return pets;
    }
}
