package by.epam.web.service;

import by.epam.web.entity.Diet;
import by.epam.web.entity.State;
import by.epam.web.exception.EntityRepositoryException;
import by.epam.web.exception.ServiceException;
import by.epam.web.repository.DietRepository;
import by.epam.web.specification.diet.DietSpecification;
import by.epam.web.specification.diet.DietUserSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class DietService {
    private static final Logger logger = LogManager.getLogger(DietService.class);
    private static final DietService INSTANCE = new DietService();
    private DietRepository repository = new DietRepository();

    private DietService() {
    }

    public static DietService getInstance() {
        return INSTANCE;
    }

    public void addDiet(String name, String description) throws ServiceException {
        try {
            repository.addEntity(new Diet(name, description));
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("Diet service add error", e);
        }
    }

    public void deleteDiet(int id, String name, String description) throws ServiceException {
        Diet diet = new Diet(id, State.BLOCKED, name, description);
        try {
            repository.updateEntity(diet);
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("Diet service remove error", e);
        }
    }

    public List<Diet> findDiets() throws ServiceException {
        try {
            return repository.query(new DietSpecification()).stream()
                    .filter(o -> o.getState() == State.UNBLOCKED)
                    .collect(Collectors.toList());
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("Diet find error", e);
        }
    }

    public List<Diet> findAssignedDiets(int id) throws ServiceException {
        try {
            return repository.query(new DietUserSpecification(id)).stream()
                    .filter(o -> o.getState() == State.UNBLOCKED)
                    .collect(Collectors.toList());
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("Assigned diets not found", e);
        }
    }

}
