package by.epam.web.service;

import by.epam.web.entity.Instructor;
import by.epam.web.entity.State;
import by.epam.web.exception.EntityRepositoryException;
import by.epam.web.exception.ServiceException;
import by.epam.web.repository.InstructorRepository;
import by.epam.web.specification.instructor.InstructorSelectedInstructorSpecification;
import by.epam.web.specification.instructor.InstructorSpecification;
import by.epam.web.specification.instructor.InstructorUserSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

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

    public void deleteInstructor(int id,String firstName, String lastName, String info) throws ServiceException {
        Instructor instructor = new Instructor(id,State.BLOCKED,firstName,lastName,info);
        try {
            repository.updateEntity(instructor);
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("InstructorService remove error", e);
        }
    }

    public List<Instructor> findInstructors() throws ServiceException {
        try {
            return repository.query(new InstructorSpecification()).stream()
                    .filter(o->o.getState() == State.UNBLOCKED)
                    .collect(Collectors.toList());
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("Find error", e);
        }
    }

    public List<Instructor> findSelectedInstructorByUserId(int userId) throws ServiceException {
        try {
            return repository.query(new InstructorUserSpecification(userId)).stream()
                    .filter(o->o.getState() == State.UNBLOCKED)
                    .collect(Collectors.toList());
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("Find error", e);
        }
    }

    public List<Instructor> findSelectedInstructor() throws ServiceException {
        try {
            return repository.query(new InstructorSelectedInstructorSpecification()).stream()
                    .filter(o->o.getState() == State.UNBLOCKED)
                    .collect(Collectors.toList());
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("Find error", e);
        }
    }

}
