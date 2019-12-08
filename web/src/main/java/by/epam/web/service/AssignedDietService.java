package by.epam.web.service;

import by.epam.web.entity.AssignedDiet;
import by.epam.web.exception.EntityRepositoryException;
import by.epam.web.exception.ServiceException;
import by.epam.web.repository.AssignedDietRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AssignedDietService {
    private static final Logger logger = LogManager.getLogger(AssignedDiet.class);
    private static final AssignedDietService INSTANCE = new AssignedDietService();
    private AssignedDietRepository repository = new AssignedDietRepository();

    private AssignedDietService() {
    }

    public static AssignedDietService getInstance() {
        return INSTANCE;
    }

    public void assignDiet(int userId, int dietId) throws ServiceException {
        AssignedDiet assignedDiet = new AssignedDiet(userId, dietId);
        try {
            repository.addEntity(assignedDiet);
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("Assigned diet service add error", e);
        }
    }

    public void deleteDiet(int id) throws ServiceException {
        AssignedDiet assignedDiet = new AssignedDiet();
        assignedDiet.setId(id);
        try {
            repository.removeEntity(assignedDiet);
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("Assigned diet service remove error", e);
        }
    }
}
