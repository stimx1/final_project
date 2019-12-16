package by.epam.web.service;

import by.epam.web.action.PasswordEncrypter;
import by.epam.web.command.AttributeName;
import by.epam.web.entity.User;
import by.epam.web.entity.UserRole;
import by.epam.web.exception.EntityRepositoryException;
import by.epam.web.exception.ServiceException;
import by.epam.web.repository.UserRepository;
import by.epam.web.specification.user.UserEmailPasswordSpecification;
import by.epam.web.specification.user.UserEmailSpecification;
import by.epam.web.specification.user.UserSpecification;
import by.epam.web.validation.RegistrationValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class UserService {
    private static final Logger logger = LogManager.getLogger(UserService.class);
    private static final UserService INSTANCE = new UserService();
    private UserRepository repository = new UserRepository();

    private UserService() {
    }

    public static UserService getINSTANCE() {
        return INSTANCE;
    }

    public void addUser(String email, String password, String lastName, String firstName, UserRole role) throws ServiceException {
        String encryptPass = PasswordEncrypter.encrypt(password);
        User user = new User(encryptPass, email, lastName, firstName, role);
        try {
            repository.addEntity(user);
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("UserService add error", e);
        }
    }

    public void deleteUser(int id) throws ServiceException {
        User user = new User();
        user.setId(id);
        try {
            repository.removeEntity(user);
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("UserService remove error", e);
        }
    }

    public List<User> findUsers() throws ServiceException {
        try {
            return repository.query(new UserSpecification());
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("Users find error", e);
        }
    }

    public List<User> findUserByEmailAndPassword(String email, String password) throws ServiceException {
        try {
            String encrypted = PasswordEncrypter.encrypt(password);
            return repository.query(new UserEmailPasswordSpecification(email, encrypted));
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("Users find error", e);
        }
    }


    public Map<String, Object> register(String email, String pass, String repeatedPass, String firstName, String lastName) throws ServiceException {
        RegistrationValidator validator = new RegistrationValidator();
        Map<String, Object> map = validator.validate(email, pass, repeatedPass,firstName, lastName);
        try {
            List<User> userList = repository.query(new UserEmailSpecification(email));
            if (userList.size() > 0) {
                map.put(AttributeName.REGISTRATION_ERROR, true);
                map.put(AttributeName.FLAG,true);
                return map;
            }
            if (map.containsKey(AttributeName.FLAG)) {
                return map;
            }
            String password = PasswordEncrypter.encrypt(pass);
            User user = new User(password, email, lastName, firstName, UserRole.USER);
            logger.info(user);
            repository.addEntity(user);
        } catch (EntityRepositoryException e) {
            logger.catching(e);
            throw new ServiceException("Registration error", e);
        }
        return map;
    }

    public Map<String,Object> updateUser(int userId,String email, String pass, String repeatedPass, String firstName, String lastName) throws ServiceException {
        RegistrationValidator validator = new RegistrationValidator();
        Map<String, Object> map = validator.validate(email, pass, repeatedPass,firstName, lastName);
        if(map.containsKey(AttributeName.FLAG)){
            return map;
        }
        String password = PasswordEncrypter.encrypt(pass);
        User user = new User(password, email, lastName, firstName, UserRole.USER);
        user.setId(userId);
        try {
            repository.updateEntity(user);
        }catch (EntityRepositoryException e){
            logger.catching(e);
            throw new ServiceException("Update error",e);
        }
        return map;
    }
}
