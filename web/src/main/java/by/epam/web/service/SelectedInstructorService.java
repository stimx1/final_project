package by.epam.web.service;

import by.epam.web.entity.SelectedInstructor;
import by.epam.web.exception.EntityRepositoryException;
import by.epam.web.exception.ServiceException;
import by.epam.web.repository.SelectedInstructorRepository;
import by.epam.web.specification.instructor.SelectedInstructorUserIdSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.List;

public class SelectedInstructorService {
    private static final Logger logger = LogManager.getLogger(SelectedInstructorService.class);
    private static final SelectedInstructorService INSTANCE = new SelectedInstructorService();
    private SelectedInstructorRepository repository = new SelectedInstructorRepository();

    private SelectedInstructorService() {
    }

    public static SelectedInstructorService getInstance() {
        return INSTANCE;
    }

    public void changeInstructor(int userId, int instructorId) throws ServiceException {
        SelectedInstructor selectedInstructor = new SelectedInstructor();
        try {
            List<SelectedInstructor> list = findByUserId(userId);
            Iterator<SelectedInstructor> iterator = list.iterator();
            if (iterator.hasNext()) {
                selectedInstructor = iterator.next();
            } else {
                add(userId, instructorId);
            }
            selectedInstructor.setInstructorId(instructorId);
            selectedInstructor.setUserId(userId);
            repository.updateEntity(selectedInstructor);
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("Selected instructor update error", e);
        }
    }

    public void add(int userId, int instructorId) throws ServiceException {
        SelectedInstructor instructor = new SelectedInstructor(userId, instructorId);
        try {
            repository.addEntity(instructor);
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("Add error");
        }
    }

    public List<SelectedInstructor> findByUserId(int userId) throws ServiceException {
        try {
            return repository.query(new SelectedInstructorUserIdSpecification(userId));
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("Selected instructor not found", e);
        }
    }
}
