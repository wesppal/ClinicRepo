package by.overone.clinic.service;

import by.overone.clinic.model.Pet;
import by.overone.clinic.util.exception.DAOException;
import by.overone.clinic.util.exception.ServiceException;
import by.overone.clinic.util.exception.ValidationException;

import java.util.List;

public interface PetService {
    List<Pet> getPets() throws ServiceException;

    Pet getPetById(long id) throws ServiceException;

    Pet addPet(long user_id, Pet pet) throws ServiceException;

    Pet updatePet(long id, Pet pet) throws ServiceException;

    boolean deletePet(long id) throws ServiceException, ValidationException;

    List<Pet> getPetByUserId(long user_id) throws ServiceException, DAOException, ValidationException;
}
