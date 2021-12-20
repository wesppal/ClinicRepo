package by.overone.clinic.service.impl;

import by.overone.clinic.model.Pet;
import by.overone.clinic.service.PetService;
import by.overone.clinic.util.exception.ServiceExceptions;

import java.util.List;

public class PetServiceImpl implements PetService {
    @Override
    public List<Pet> getPets() throws ServiceExceptions {
        return null;
    }

    @Override
    public Pet getPetById(long id) throws ServiceExceptions {
        return null;
    }

    @Override
    public Pet addPet(long user_id, Pet pet) throws ServiceExceptions {
        return null;
    }

    @Override
    public Pet updatePet(long id, Pet pet) throws ServiceExceptions {
        return null;
    }

    @Override
    public boolean deletePet(long id) throws ServiceExceptions {
        return false;
    }

    @Override
    public List<Pet> getPetByUserId(long user_id) throws ServiceExceptions {
        return null;
    }
}
