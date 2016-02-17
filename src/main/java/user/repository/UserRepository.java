package user.repository;

import user.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by Arne on 11/02/2016.
 */
public interface UserRepository {

    List<User> getAllUsers() throws DbUserException, UnsupportedEncodingException, NoSuchAlgorithmException;
    User getUserByEmail(String email) throws UnsupportedEncodingException, NoSuchAlgorithmException, DbUserException;
    void addUser(User user) throws DbUserException, UnsupportedEncodingException, NoSuchAlgorithmException;
    void deleteUser(String email) throws DbUserException;
}
