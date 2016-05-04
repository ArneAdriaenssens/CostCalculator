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
import exchangeservice.WebServiceExchangeRate;

/**
 * Created by Arne on 11/02/2016.
 */
public class CostCalculatorFacade implements CostCalculator {

    private CostRepository costRepository;
    private OwnerRepository userRepository;
    private WebServiceExchangeRate exchange;

    public CostCalculatorFacade(RepositoryTypes type) {
        this.costRepository = new CostRepositoryFactory().createCostRepository(type);
        this.userRepository = new OwnerRepositoryFactory().createUserRepository(type);
        this.exchange = new WebServiceExchangeRate();
    }

    private CostRepository getCostRepository() {
        return costRepository;
    }

    private OwnerRepository getUserRepository() {
        return userRepository;
    }
    
    public void setCostRepository(CostRepository cost){
        this.costRepository=cost;
    }
    
    public void setOwnerRepository(OwnerRepository owner){
        this.userRepository=owner;
    }

    @Override
    public List<Cost> getAllCosts() {
        List<Cost> returnList = null;
        try {
            returnList = this.getCostRepository().getAllCosts();
        } catch (DbCostException e) {
            System.out.println(e.getMessage());
        }
        return returnList;
    }

    @Override
    public void addCost(Cost cost) {
        try {
            this.getCostRepository().addCost(cost);
            Owner owner = cost.getOwner();
            owner.addCosts(cost);
        } catch (DbCostException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteCost(Cost cost) {
        try {
            this.getCostRepository().deleteCost(cost);
        } catch (DbCostException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Owner> getAllUsers() {
        List<Owner> userReturn = null;
        try {
            userReturn = this.getUserRepository().getAllUsers();
        } catch (DbOwnerException e) {
            System.out.println("Somthing went wrong");
        }
        return userReturn;
    }

    @Override
    public Owner getUserByEmail(String email) {
        Owner user = null;
        try {
            user = this.getUserRepository().getUserByEmail(email);
        } catch (DbOwnerException e) {
            System.out.println("Something went wrong");
            System.out.println(e);
        }
        return user;
    }

    @Override
    public void addUser(Owner user) {
        try {
            this.getUserRepository().addUser(user);
        } catch (DbOwnerException e) {
            System.out.println(e);
            System.out.println("Somthing went wrong");
        }
    }

    @Override
    public void deleteUser(Owner owner) {
        this.getUserRepository().deleteUser(owner);
        
    }

    @Override
    public Cost getCostById(long id) {
        return costRepository.getCostById(id);
    }

    @Override
    public void updateCost(Cost changedCost) {
        costRepository.updateCost(changedCost);
    }

    @Override
    public List<Cost> getCostsByEmail(String email) {
        return getCostRepository().getCostsByEmail(email);
    }

    @Override
    public int calculateAmountOfCostsForUser(String email) {
        return this.getCostRepository().calculateAmountOfCostsForUser(email);
    }

    @Override
    public double calculateTotalPriceForUser(String email) {
        return this.getCostRepository().calculateTotalPriceForUser(email);
    }

    @Override
    public void retrieveExchanges() {
        exchange.retrieveAllExchanges();
    }

    @Override
    public Double getRate(String key) {
        return exchange.getRate(key);
    }
    
    public List<String> getRates(){
        return exchange.getRates();
    }
    
}
