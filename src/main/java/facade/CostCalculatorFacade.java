package facade;

import cost.domain.Cost;
import cost.repository.CostRepository;
import cost.repository.DbCostException;
import cost.repository.CostRepositoryFactory;
import common.RepositoryTypes;
import cost.domain.Category;
import owner.repository.OwnerRepositoryFactory;
import owner.domain.Owner;
import owner.repository.DbOwnerException;

import java.util.List;
import owner.repository.OwnerRepository;

/**
 * Created by Arne on 11/02/2016.
 */
public class CostCalculatorFacade implements CostCalculator{

    private final CostRepository costRepository;
    private final OwnerRepository userRepository;
    private final RepositoryTypes type=RepositoryTypes.FAKE;
    
    public CostCalculatorFacade(){
        this.costRepository=new CostRepositoryFactory().createCostRepository(type);
        this.userRepository=new OwnerRepositoryFactory().createUserRepository(type);
        Owner wouter = new Owner("Wooterq", "Adsriaens", "wouter.adriaens@email.be", "123");
        Cost cost = new Cost(15, "Leuven", wouter,  Category.DRINKS, "Arne trakteren");
        this.addUser(wouter);
        this.addCost(cost);
        Cost cost2 = new Cost(15, "Leuven", wouter, Category.DRINKS, "Arne nog eens trakteren");
        this.addCost(cost2);
    }

    private CostRepository getCostRepository() {
        return costRepository;
    }

    private OwnerRepository getUserRepository() {
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
            Owner owner = cost.getOwner();
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
    public List<Owner> getAllUsers(){
        List<Owner> userReturn=null;
        try {
            userReturn = this.getUserRepository().getAllUsers();
        } catch (DbOwnerException e) {
            System.out.println("Somthing went wrong");
        }
        return userReturn;
    }

    @Override
    public Owner getUserByEmail(String email){
        Owner user =null;
        try {
            user= this.getUserRepository().getUserByEmail(email);
        } catch (DbOwnerException e) {
            System.out.println("Somthing went wrong");
        }
        return user;
    }
    
    @Override
    public void addUser(Owner user){
        try {
            this.getUserRepository().addUser(user);
        } catch (DbOwnerException e) {
            System.out.println("Somthing went wrong");
        }
    }

    @Override
    public void deleteUser(String email){
        try {
            this.getUserRepository().deleteUser(email);
        } catch (DbOwnerException e) {
            System.out.println("Somthing went wrong");
        }
    }

    @Override
    public Cost getCostById(long id) {
        return costRepository.getCostById(id);
    }

    @Override
    public void updateCost(Cost changedCost) {
        costRepository.updateCost(changedCost);
        userRepository.updateCost(changedCost);
    }
}
