package by.overone.clinic.service.impl;

import by.overone.clinic.dao.PetDAO;
import by.overone.clinic.dao.impl.PetDAOImpl;
import by.overone.clinic.model.Pet;
import by.overone.clinic.service.PetService;
import by.overone.clinic.util.exception.DAOException;
import by.overone.clinic.util.exception.ServiceException;

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
    public Pet addPet(long user_id, Pet pet) throws ServiceException {
        return null;
    }

    @Override
    public Pet updatePet(long id, Pet pet) throws ServiceException {
        return null;
    }

    @Override
    public boolean deletePet(long id) throws ServiceException {
        return false;
    }

    @Override
    public List<Pet> getPetByUserId(long user_id) throws ServiceException {
        return null;
    }
}
