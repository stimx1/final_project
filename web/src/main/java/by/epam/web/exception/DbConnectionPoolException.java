package by.epam.web.exception;

public class DbConnectionPoolException extends Exception {
    public DbConnectionPoolException() {
    }

    public DbConnectionPoolException(String message) {
        super(message);
    }

    public DbConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public DbConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
