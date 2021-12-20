package by.overone.clinic.service;

import by.overone.clinic.model.Pet;
import by.overone.clinic.util.exception.ServiceExceptions;

import java.util.List;

public interface PetService {
    List<Pet> getPets() throws ServiceExceptions;

    Pet getPetById(long id) throws ServiceExceptions;

    Pet addPet(long user_id, Pet pet) throws ServiceExceptions;

    Pet updatePet(long id, Pet pet) throws ServiceExceptions;

    boolean deletePet(long id) throws ServiceExceptions;

    List<Pet> getPetByUserId(long user_id) throws ServiceExceptions;
}
