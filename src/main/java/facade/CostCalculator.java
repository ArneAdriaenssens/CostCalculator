package facade;

import cost.Cost;
import cost.repository.CostRepository;
import factory.CostRepositoryFactory;
import factory.RepositoryTypes;
import factory.UserRepositoryFactory;
import user.User;
import user.repository.DbUserException;
import user.repository.UserRepository;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by Arne on 11/02/2016.
 */
public class CostCalculator {

    private CostRepository costRepository;
    private UserRepository userRepository;

    public CostCalculator(){
        try {
            this.costRepository=new CostRepositoryFactory().createCostRepository(RepositoryTypes.FAKE);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            this.userRepository=new UserRepositoryFactory().createUserRepository(RepositoryTypes.FAKE);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public CostRepository getCostRepository() {
        return costRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public List<Cost> getAllCosts(){
        return this.getCostRepository().getAllCosts();
    }
    public void addCost(Cost cost){
        this.getCostRepository().addCost(cost);
    }
    public void deleteCost(Cost cost){
        this.getCostRepository().deleteCost(cost);
    }

    public List<User> getAllUsers() throws DbUserException, UnsupportedEncodingException, NoSuchAlgorithmException{
        return this.getUserRepository().getAllUsers();
    }

    public User getUserByEmail(String email) throws UnsupportedEncodingException, NoSuchAlgorithmException, DbUserException{
        return this.getUserRepository().getUserByEmail(email);
    }
    public void addUser(User user) throws DbUserException, UnsupportedEncodingException, NoSuchAlgorithmException{
        this.getUserRepository().addUser(user);
    }

    public void deleteUser(String email) throws DbUserException{
        this.getUserRepository().deleteUser(email);
    }
}
