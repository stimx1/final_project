package by.epam.web.exception;

public class EntityRepositoryException extends Exception {
    public EntityRepositoryException() {
    }

    public EntityRepositoryException(String message) {
        super(message);
    }

    public EntityRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityRepositoryException(Throwable cause) {
        super(cause);
    }
}
