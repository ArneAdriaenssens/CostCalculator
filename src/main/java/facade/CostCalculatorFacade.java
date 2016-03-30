package facade;

import cost.domain.Cost;
import cost.repository.CostRepository;
import cost.repository.DbCostException;
import cost.repository.CostRepositoryFactory;
import common.RepositoryTypes;
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
    private final RepositoryTypes type=RepositoryTypes.DB;
    
    public CostCalculatorFacade(){
        this.costRepository=new CostRepositoryFactory().createCostRepository(type);
        this.userRepository=new OwnerRepositoryFactory().createUserRepository(type);
    }

    public CostRepository getCostRepository() {
        return costRepository;
    }

    public OwnerRepository getUserRepository() {
        return userRepository;
    }

    @Override
    public List<Cost> getAllCosts() {
        List<Cost> returnList=null;
        try {
            returnList= this.getCostRepository().getAllCosts();
        } catch (DbCostException e) {
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void deleteCost(Cost cost){
        try {
            this.getCostRepository().deleteCost(cost);
        } catch (DbCostException e) {
            System.out.println(e.getMessage());
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
            System.out.println("Something went wrong");
            System.out.println(e);
        }
        return user;
    }
    
    @Override
    public void addUser(Owner user){
        try {
            this.getUserRepository().addUser(user);
        } catch (DbOwnerException e) {
            System.out.println(e);
            System.out.println("Somthing went wrong");
        }
    }

    @Override
    public void deleteUser(Owner owner){
        try {
            this.getUserRepository().deleteUser(owner);
        } catch (DbOwnerException e) {
            System.out.println("Somthing went wrong");
            System.out.println(e);
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

    @Override
    public List<Cost> getCostsByEmail(String email) {
        return getCostRepository().getCostsByEmail(email);
    }
}
