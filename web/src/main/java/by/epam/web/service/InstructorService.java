package by.epam.web.service;

import by.epam.web.entity.Instructor;
import by.epam.web.exception.EntityRepositoryException;
import by.epam.web.exception.ServiceException;
import by.epam.web.repository.InstructorRepository;
import by.epam.web.specification.instructor.InstructorSpecification;
import by.epam.web.specification.instructor.InstructorUserSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class InstructorService {
    private static final Logger logger = LogManager.getLogger(InstructorService.class);
    private static final InstructorService INSTANCE = new InstructorService();
    private InstructorRepository repository = new InstructorRepository();

    private InstructorService(){}

    public static InstructorService getInstance(){
        return INSTANCE;
    }

    public void addInstructor(String firstName, String lastName, String info) throws ServiceException {
        Instructor instructor = new Instructor(firstName, lastName, info);
        try {
            repository.addEntity(instructor);
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw  new ServiceException("InstructorService add error", e);
        }
    }

    public void deleteInstructor(int id) throws ServiceException {
        Instructor instructor = new Instructor();
        instructor.setId(id);
        try {
            repository.removeEntity(instructor);
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("InstructorService remove error", e);
        }
    }

    public List<Instructor> findInstructors() throws ServiceException {
        try {
            return repository.query(new InstructorSpecification());
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("Instructors not found", e);
        }
    }

    public List<Instructor> findSelectedInstructor(int userId) throws ServiceException {
        try {
            return repository.query(new InstructorUserSpecification(userId));
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("Selected instructors not found", e);
        }
    }


}
