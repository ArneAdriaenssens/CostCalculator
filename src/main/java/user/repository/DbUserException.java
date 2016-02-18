package user.repository;

/**
 * Created by Arne on 11/02/2016.
 */
public class DbUserException extends RuntimeException {
    public DbUserException(String e) {
        super(e);
    }
}
