package owner.repository;

import cost.domain.Cost;
import owner.domain.Owner;

import java.util.List;

/**
 * Created by Arne on 11/02/2016.
 */
public interface OwnerRepository {

    List<Owner> getAllUsers();
    Owner getUserByEmail(String email);
    void addUser(Owner user);
    void deleteUser(Owner owner);
    void updateUser(Owner owner);
}
