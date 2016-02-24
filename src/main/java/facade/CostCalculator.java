package facade;

import cost.Cost;
import cost.repository.CostRepository;
import cost.repository.DbCostException;
import factory.CostRepositoryFactory;
import factory.RepositoryTypes;
import factory.UserRepositoryFactory;
import user.User;
import user.repository.DbUserException;
import user.repository.UserRepository;

import java.util.List;

/**
 * Created by Arne on 11/02/2016.
 */
public class CostCalculator {

    private CostRepository costRepository;
    private UserRepository userRepository;

    public CostCalculator(){
        this.costRepository=new CostRepositoryFactory().createCostRepository(RepositoryTypes.FAKE);
        this.userRepository=new UserRepositoryFactory().createUserRepository(RepositoryTypes.FAKE);
    }

    private CostRepository getCostRepository() {
        return costRepository;
    }

    private UserRepository getUserRepository() {
        return userRepository;
    }

    public List<Cost> getAllCosts(){
        List<Cost> returnList=null;
        try {
            returnList= this.getCostRepository().getAllCosts();
        } catch (DbCostException e) {
            e.printStackTrace();
        }
        return returnList;
    }
    public void addCost(Cost cost){
        try {
            this.getCostRepository().addCost(cost);
        } catch (DbCostException e) {
            e.printStackTrace();
        }
    }
    public void deleteCost(Cost cost){
        try {
            this.getCostRepository().deleteCost(cost);
        } catch (DbCostException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers(){
        List<User> userReturn=null;
        try {
            userReturn = this.getUserRepository().getAllUsers();
        } catch (DbUserException e) {
            e.printStackTrace();
        }
        return userReturn;
    }

    public User getUserByEmail(String email){
        User user =null;
        try {
            user= this.getUserRepository().getUserByEmail(email);
        } catch (DbUserException e) {
            e.printStackTrace();
        }
        return user;
    }
    public void addUser(User user){
        try {
            this.getUserRepository().addUser(user);
        } catch (DbUserException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(String email){
        try {
            this.getUserRepository().deleteUser(email);
        } catch (DbUserException e) {
            e.printStackTrace();
        }
    }
}
