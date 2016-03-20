package user.repository;

import cost.domain.Cost;
import user.domain.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Arne on 17/02/2016.
 */
public class UserRepositoryFake implements UserRepository{

    private static Map<String, User> users;

    public UserRepositoryFake(){
        users=new HashMap<String, User>();
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public List<User> getAllUsers() {
        return new ArrayList<User>(getUsers().values());
    }

    public User getUserByEmail(String email){
        if(email.equals("")) throw new DbUserException("Email can't be empty");
        return getUsers().get(email);
    }

    public void addUser(User user) {
        if(user==null)throw new DbUserException("User does not exist");
        this.getUsers().put(user.getEmail(), user);
    }

    public void deleteUser(String email)  {
        if(email.equals("")) throw new DbUserException("Email can't be empty");
        this.getUsers().remove(email);
    }

    @Override
    public void updateCost(Cost cost) {
        if(cost.getOwner()==null) throw new DbUserException("Owner does not exist");
        else{
            User owner = cost.getOwner();
            for(int i=0; i<owner.getCosts().size(); i++){
                if(owner.getCosts().get(i).getId()==(cost.getId())){
                    owner.getCosts().remove(i);
                    owner.addCosts(cost);
                }
            }
        }
    }
}
