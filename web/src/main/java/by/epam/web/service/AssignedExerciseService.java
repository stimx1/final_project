package by.epam.web.service;

import by.epam.web.entity.AssignedExercise;
import by.epam.web.exception.EntityRepositoryException;
import by.epam.web.exception.ServiceException;
import by.epam.web.repository.AssignedExerciseRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AssignedExerciseService {
    private static final Logger logger = LogManager.getLogger(AssignedExerciseService.class);
    private static final AssignedExerciseService INSTANCE = new AssignedExerciseService();
    private AssignedExerciseRepository repository = new AssignedExerciseRepository();

    private AssignedExerciseService() {
    }

    public static AssignedExerciseService getInstance() {
        return INSTANCE;
    }

    public void assignExercise(int userId, int exerciseId) throws ServiceException {
        AssignedExercise assignedExercise = new AssignedExercise(userId, exerciseId);
        try {
            repository.addEntity(assignedExercise);
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("AssignedExerciseService add error", e);
        }
    }

    public void deleteExercise(int id) throws ServiceException {
        AssignedExercise assignedExercise = new AssignedExercise();
        assignedExercise.setId(id);
        try {
            repository.removeEntity(assignedExercise);
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("AssignedExerciseService remove error", e);
        }
    }
}
