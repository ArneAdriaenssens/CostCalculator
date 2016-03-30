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
    public void updateCost(Cost cost) {
        if(cost.getOwner()==null) throw new DbOwnerException("Owner does not exist");
        else{
            Owner owner = cost.getOwner();
            for(int i=0; i<owner.getCosts().size(); i++){
                if(owner.getCosts().get(i).getId()==(cost.getId())){
                    owner.getCosts().remove(i);
                    owner.addCosts(cost);
                }
            }
        }
    }
}
