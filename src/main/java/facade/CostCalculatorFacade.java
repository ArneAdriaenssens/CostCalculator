package facade;

import cost.domain.Cost;
import cost.repository.CostRepository;
import cost.repository.CostRepositoryFactory;
import common.RepositoryTypes;
import owner.repository.OwnerRepositoryFactory;
import owner.domain.Owner;

import java.util.List;
import owner.repository.OwnerRepository;
import exchangeservice.WebServiceExchangeRate;

/**
 * Created by Arne on 11/02/2016.
 */
public class CostCalculatorFacade implements CostCalculator {

    private CostRepository costRepository;
    private OwnerRepository userRepository;
    private final WebServiceExchangeRate exchange;

    public CostCalculatorFacade(RepositoryTypes type) {
        this.costRepository = new CostRepositoryFactory().createCostRepository(type);
        this.userRepository = new OwnerRepositoryFactory().createUserRepository(type);
        this.exchange = new WebServiceExchangeRate();
    }
    
    private OwnerRepository getUserRepository(){
        return userRepository;
    }
    
     private CostRepository getCostRepository(){
        return costRepository;
    }

    @Override
    public List<Cost> getAllCosts() {
        List<Cost> returnList = null;
        returnList = this.getCostRepository().getAllCosts();
        return returnList;
    }

    @Override
    public void addCost(Cost cost) {
        this.getCostRepository().addCost(cost);
        Owner owner = cost.getOwner();
        owner.addCosts(cost);
    }

    @Override
    public void deleteCost(Cost cost) {
        this.getCostRepository().deleteCost(cost);
    }

    @Override
    public List<Owner> getAllUsers() {
        List<Owner> userReturn = null;
        userReturn = this.getUserRepository().getAllUsers();
        return userReturn;
    }

    @Override
    public Owner getUserByEmail(String email) {
        Owner user = null;
        user = this.getUserRepository().getUserByEmail(email);
        return user;
    }

    @Override
    public void addUser(Owner user) {
        this.getUserRepository().addUser(user);
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

    public List<String> getRates() {
        return exchange.getRates();
    }

    @Override
    public void updateUser(Owner owner) {
        this.getUserRepository().updateUser(owner);
    }

}
