package user.repository;

import cost.domain.Cost;
import user.domain.User;

import java.util.List;

/**
 * Created by Arne on 11/02/2016.
 */
public interface UserRepository {

    List<User> getAllUsers();
    User getUserByEmail(String email);
    void addUser(User user);
    void deleteUser(String email);
    void updateCost(Cost cost);
}
