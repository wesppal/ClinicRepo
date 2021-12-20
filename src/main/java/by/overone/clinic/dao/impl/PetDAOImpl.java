package by.overone.clinic.dao.impl;

import by.overone.clinic.dao.PetDAO;
import by.overone.clinic.model.Pet;
import by.overone.clinic.model.Status;
import by.overone.clinic.model.User;
import by.overone.clinic.util.DBConnect;
import by.overone.clinic.util.constant.PetConst;
import by.overone.clinic.util.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PetDAOImpl implements PetDAO {
    private static Connection connection;

    private final static String GET_ALL_PETS_SQL = "SELECT * FROM pets where status = 'active'";
    private final static String GET_PET_BY_ID_SQL = "SELECT * FROM pets WHERE pet_id=(?)";
    private final static String ADD_NEW_PET_SQL = "INSERT INTO user VALUE (0,?,?,?,?,?)";


    @Override
    public List<Pet> getPets() throws DAOException {
        List<Pet> pets = new ArrayList<>();

        try {
            connection = DBConnect.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PETS_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Pet pet = new Pet(
                        resultSet.getLong(PetConst.ID),
                        resultSet.getString(PetConst.NAME),
                        resultSet.getInt(PetConst.AGE),
                        resultSet.getString(PetConst.TYPE),
                        resultSet.getString(PetConst.OWNER),
                        resultSet.getInt(PetConst.USER_ID),
                        Status.valueOf(resultSet.getString(PetConst.STATUS.toUpperCase(Locale.ROOT)))
                );
                pets.add(pet);
            }
        } catch (SQLException e) {
            throw new DAOException("sad");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return pets;
    }

    @Override
    public Pet getPetById(long id) throws DAOException {
        Pet pet = new Pet();
        try {
            connection = DBConnect.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PET_BY_ID_SQL);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new DAOException("PetDAOImpl. GetPetById failed. The pet with the id=" + id +
                        "does not exist.");
            }
            pet.setId(resultSet.getLong(PetConst.ID));
            pet.setName(resultSet.getString(PetConst.NAME));
            pet.setAge(resultSet.getInt(PetConst.AGE));
            pet.setType(resultSet.getString(PetConst.TYPE));
            pet.setOwner(resultSet.getString(PetConst.OWNER));
            pet.setUser_id(resultSet.getLong(PetConst.USER_ID));
            pet.setStatus(Status.valueOf(resultSet.getString(PetConst.STATUS).toUpperCase(Locale.ROOT)));
        } catch (SQLException e) {
            throw new DAOException("PetDAOImpl. getPetById failed. no connection.", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return pet;
    }

    @Override
    public Pet addPet(long user_id, Pet pet) throws DAOException {
        return null;
    }

    @Override
    public Pet updatePet(long id, Pet pet) throws DAOException {
        return null;
    }

    @Override
    public boolean deletePet(long id) throws DAOException {
        return false;
    }

    @Override
    public List<Pet> getPetByUserId(long user_id) throws DAOException {
        return null;
    }
}
