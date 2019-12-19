package by.epam.web.service;

import by.epam.web.entity.Exercise;
import by.epam.web.entity.State;
import by.epam.web.exception.EntityRepositoryException;
import by.epam.web.exception.ServiceException;
import by.epam.web.repository.ExerciseRepository;
import by.epam.web.specification.exercise.ExerciseSpecification;
import by.epam.web.specification.exercise.ExerciseUserSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class ExerciseService {
    private static final Logger logger = LogManager.getLogger(ExerciseService.class);
    private static final ExerciseService INSTANCE = new ExerciseService();
    private ExerciseRepository repository = new ExerciseRepository();

    private ExerciseService() {
    }

    public static ExerciseService getInstance() {
        return INSTANCE;
    }

    public void addExercise(String name, String description) throws ServiceException {
        try {
            repository.addEntity(new Exercise(name, description));
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("ExerciseService add error", e);
        }
    }

    public void deleteExercise(int id, String name, String description) throws ServiceException {
        Exercise exercise = new Exercise(id, State.BLOCKED, name, description);
        try {
            repository.updateEntity(exercise);
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("ExerciseService delete error", e);
        }
    }

    public List<Exercise> findExercises() throws ServiceException {
        try {
            return repository.query(new ExerciseSpecification()).stream()
                    .filter(o -> o.getState() == State.UNBLOCKED)
                    .collect(Collectors.toList());
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("Exercises find error", e);
        }
    }


    public List<Exercise> findAssignedExercisesByUserId(int userId) throws ServiceException {
        try {
            return repository.query(new ExerciseUserSpecification(userId)).stream()
                    .filter(o -> o.getState() == State.UNBLOCKED)
                    .collect(Collectors.toList());
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("Assigned exercises not found", e);
        }
    }
}
