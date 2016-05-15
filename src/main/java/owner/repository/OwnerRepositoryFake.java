package owner.repository;

import cost.domain.Cost;
import owner.domain.Owner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Arne on 17/02/2016.
 */
public class OwnerRepositoryFake implements OwnerRepository{

    private static Map<String, Owner> users;
    private static OwnerRepositoryFake instance;
    
    public static OwnerRepositoryFake getInstance(){
        if(instance==null) instance = new OwnerRepositoryFake();
        return instance;
    }

    public OwnerRepositoryFake(){
        users=new HashMap<String, Owner>();
    }

    public Map<String, Owner> getUsers() {
        return users;
    }

    public List<Owner> getAllUsers() {
        return new ArrayList<Owner>(getUsers().values());
    }

    public Owner getUserByEmail(String email){
        if(email.equals("")) throw new DbOwnerException("Email can't be empty");
        return getUsers().get(email);
    }

    public void addUser(Owner user) {
        if(user==null)throw new DbOwnerException("User does not exist");
        this.getUsers().put(user.getEmail(), user);
    }

    public void deleteUser(Owner owner)  {
        if(owner==null) throw new DbOwnerException("Email can't be empty");
        this.getUsers().remove(owner.getEmail());
    }

    @Override
    public void updateUser(Owner owner) {
        if(this.getUserByEmail(owner.getEmail())==null) throw new DbOwnerException("User does not exist in DB");
        this.getUsers().get(owner.getEmail()).setCosts(owner.getCosts());
        this.getUsers().get(owner.getEmail()).setFirstName(owner.getFirstName());
        this.getUsers().get(owner.getEmail()).setLastName(owner.getLastName());
        this.getUsers().get(owner.getEmail()).setPassword(owner.getPassword());
    }
}
