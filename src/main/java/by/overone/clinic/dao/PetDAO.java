package by.overone.clinic.dao;

import by.overone.clinic.model.Pet;
import by.overone.clinic.util.exception.DAOException;

import java.util.List;

public interface PetDAO {

    List<Pet> getPets() throws DAOException;

    Pet getPetById(long id) throws DAOException;

    Pet addPet(long user_id, Pet pet) throws DAOException;

    Pet updatePet(long id, Pet pet) throws DAOException;

    boolean deletePet(long id) throws DAOException;

    List<Pet> getPetByUserId(long user_id) throws DAOException;
}
