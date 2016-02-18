package user.repository;

import user.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by Arne on 11/02/2016.
 */
public interface UserRepository {

    List<User> getAllUsers() throws  DbUserException;
    User getUserByEmail(String email) throws DbUserException;
    void addUser(User user) throws DbUserException;
    void deleteUser(String email) throws DbUserException;
}
