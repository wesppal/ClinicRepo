package by.overone.clinic;


import by.overone.clinic.dao.PetDAO;
import by.overone.clinic.dao.impl.PetDAOImpl;
import by.overone.clinic.util.exception.DAOException;



public class Main {
    public static void main(String[] args) throws DAOException{
        PetDAO petDAO = new PetDAOImpl();
        System.out.println(petDAO.getPetByUserId(17));
    }
}
