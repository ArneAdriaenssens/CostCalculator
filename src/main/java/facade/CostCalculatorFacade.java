package facade;

import cost.domain.Cost;
import cost.repository.CostRepository;
import cost.repository.DbCostException;
import cost.repository.CostRepositoryFactory;
import common.RepositoryTypes;
import user.repository.UserRepositoryFactory;
import user.domain.User;
import user.repository.DbUserException;
import user.repository.UserRepository;

import java.util.List;

/**
 * Created by Arne on 11/02/2016.
 */
public class CostCalculatorFacade implements CostCalculator{

    private final CostRepository costRepository;
    private final UserRepository userRepository;

    public CostCalculatorFacade(RepositoryTypes type){
        this.costRepository=new CostRepositoryFactory().createCostRepository(type);
        this.userRepository=new UserRepositoryFactory().createUserRepository(type);
        User wouter = new User("Wooterq", "Adsriaens", "wouter.adriaens@email.be", "123");
        Cost cost = new Cost(15, wouter, "Leuven", "DRINKS", "Arne trakteren");
        this.addUser(wouter);
        this.addCost(cost);
        Cost cost2 = new Cost(15, wouter, "Leuven", "DRINKS", "Arne nog eens trakteren");
        this.costRepository.addCost(cost2);
    }

    private CostRepository getCostRepository() {
        return costRepository;
    }

    private UserRepository getUserRepository() {
        return userRepository;
    }

    @Override
    public List<Cost> getAllCosts() {
        List<Cost> returnList=null;
        try {
            returnList= this.getCostRepository().getAllCosts();
        } catch (DbCostException e) {
            System.out.println("Somthing went wrong");
        }
        return returnList;
    }
    
    @Override
    public void addCost(Cost cost){
        try {
            this.getCostRepository().addCost(cost);
            User owner = cost.getOwner();
            owner.addCosts(cost);
        } catch (DbCostException e) {
            System.out.println("Somthing went wrong");
        }
    }
    
    @Override
    public void deleteCost(Cost cost){
        try {
            this.getCostRepository().deleteCost(cost);
        } catch (DbCostException e) {
            System.out.println("Somthing went wrong");
        }
    }

    @Override
    public List<User> getAllUsers(){
        List<User> userReturn=null;
        try {
            userReturn = this.getUserRepository().getAllUsers();
        } catch (DbUserException e) {
            System.out.println("Somthing went wrong");
        }
        return userReturn;
    }

    @Override
    public User getUserByEmail(String email){
        User user =null;
        try {
            user= this.getUserRepository().getUserByEmail(email);
        } catch (DbUserException e) {
            System.out.println("Somthing went wrong");
        }
        return user;
    }
    
    @Override
    public void addUser(User user){
        try {
            this.getUserRepository().addUser(user);
        } catch (DbUserException e) {
            System.out.println("Somthing went wrong");
        }
    }

    @Override
    public void deleteUser(String email){
        try {
            this.getUserRepository().deleteUser(email);
        } catch (DbUserException e) {
            System.out.println("Somthing went wrong");
        }
    }
}
