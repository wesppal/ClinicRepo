package by.overone.clinic.dao.impl;

import by.overone.clinic.dao.PetDAO;
import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.dto.PetParamDTO;
import by.overone.clinic.model.Pet;
import by.overone.clinic.model.Status;
import by.overone.clinic.model.UserDetail;
import by.overone.clinic.util.DBConnect;
import by.overone.clinic.util.constant.PetConst;
import by.overone.clinic.util.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PetDAOImpl implements PetDAO {
    private static Connection connection;
    private UserDAO userDAO = new UserDAOImpl();

    private final static String GET_ALL_PETS_SQL = "SELECT * FROM pets where status = 'ACTIVE'";
    private final static String GET_PET_BY_ID_SQL = "SELECT * FROM pets WHERE pet_id=(?)";
    private final static String ADD_NEW_PET_SQL = "INSERT INTO pets VALUE (0,?,?,?,?,?,?)";
    private final static String UPDATE_PET_STATUS_SQL = "UPDATE pets SET status =(?) WHERE pet_id=(?)";
    private final static String UPDATE_PET_SQL = "UPDATE pets SET";
    private final static String GET_PETS_BY_USER_ID_SQL = "SELECT * FROM pets WHERE user_id=(?)";


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
            throw new DAOException("PetDAOImpl. GetPets failed. Not connection.");
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
        UserDetail user;
        user = userDAO.getUserDetailById(user_id);

        try {
            connection = DBConnect.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_PET_SQL,
                    Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, pet.getName());
            preparedStatement.setInt(2, pet.getAge());
            preparedStatement.setString(3, pet.getType());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setLong(5, user_id);
            preparedStatement.setString(6, Status.ACTIVE.name().toUpperCase(Locale.ROOT));
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                pet.setId(resultSet.getLong(1));
            }
            pet.setStatus(Status.ACTIVE);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pet;
    }

    @Override
    public Pet updatePet(long id, Pet pet) throws DAOException {
        updatePetStatus(id, Status.ACTIVE);
        try {
            connection = DBConnect.getConnection();
            PetParamDTO petParamDTO = new PetParamDTO();
            petParamDTO = petParamDTO.copyParam(petParamDTO, pet);
            String params = petParamDTO.getParam().toString();

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PET_SQL + params);
            int i = 1;
            if (pet.getName() != null) {
                preparedStatement.setString(i, pet.getName());
                i++;
            }
            if (pet.getAge() != 0) {
                preparedStatement.setInt(i, pet.getAge());
                i++;
            }
            if (pet.getType() != null) {
                preparedStatement.setString(i, pet.getType());
                i++;
            }
            if (pet.getOwner() != null) {
                preparedStatement.setString(i, pet.getOwner());
                i++;
            }
            if (pet.getUser_id() != 0) {
                preparedStatement.setLong(i, pet.getUser_id());
                i++;
            }
            preparedStatement.setLong(i, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("PetDAOImpl. UpdatePet failed. Not connection db.");
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
    public void deletePet(long id) throws DAOException {
        updatePetStatus(id, Status.DELETED);
    }

    private void updatePetStatus(long id, Status status) throws DAOException {
        try {
            connection = DBConnect.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PET_STATUS_SQL);
            preparedStatement.setString(1, status.toString());
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            try {
                connection.rollback();
                throw new DAOException("UserDAOImpl. Already removed.");
            } catch (SQLException ex) {
                throw new DAOException("UserDAOImpl. Status update failed.");
            } finally {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Pet> getPetByUserId(long user_id) throws DAOException {
        List<Pet> pets = new ArrayList<>();
        try {
            connection = DBConnect.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PETS_BY_USER_ID_SQL);
            preparedStatement.setLong(1, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Pet pet = new Pet();
                pet.setId(resultSet.getLong(PetConst.ID));
                pet.setName(resultSet.getString(PetConst.NAME));
                pet.setAge(resultSet.getInt(PetConst.AGE));
                pet.setType(resultSet.getString(PetConst.TYPE));
                pet.setOwner(resultSet.getString(PetConst.OWNER));
                pet.setUser_id(resultSet.getLong(PetConst.USER_ID));
                pet.setStatus(Status.valueOf(resultSet.getString(PetConst.STATUS)));
                pets.add(pet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pets;
    }
}
