package user.repository;

import user.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Arne on 17/02/2016.
 */
public class UserRepositoryMap implements UserRepository{

    private Map<String, User> users;

    public UserRepositoryMap(){
        users=new HashMap<String, User>();
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public List<User> getAllUsers() throws DbUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        return new ArrayList<User>(getUsers().values());
    }

    public User getUserByEmail(String email) throws UnsupportedEncodingException, NoSuchAlgorithmException, DbUserException {
        if(email.equals("")) throw new DbUserException("Email can't be empty");
        return getUsers().get(email);
    }

    public void addUser(User user) throws DbUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if(user==null)throw new DbUserException("User does not exist");
        this.getUsers().put(user.getEmail(), user);
    }

    public void deleteUser(String email) throws DbUserException {
        if(email.equals("")) throw new DbUserException("Email can't be empty");
        this.getUsers().remove(email);
    }
}
