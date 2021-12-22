package by.overone.clinic;


import by.overone.clinic.dao.PetDAO;
import by.overone.clinic.dao.impl.PetDAOImpl;
import by.overone.clinic.service.PetService;
import by.overone.clinic.service.impl.PetServiceImpl;
import by.overone.clinic.util.exception.DAOException;
import by.overone.clinic.util.exception.ServiceException;
import by.overone.clinic.util.exception.ValidationException;


public class Main {
    public static void main(String[] args) throws DAOException, ServiceException, ValidationException {
        PetDAO petDAO = new PetDAOImpl();
        System.out.println(petDAO.getPetByUserId(17));
        PetService petService = new PetServiceImpl();
        petService.deletePet(0);
    }
}
