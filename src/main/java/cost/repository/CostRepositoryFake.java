package cost.repository;

import cost.domain.Cost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Arne on 17/02/2016.
 */
public class CostRepositoryFake implements CostRepository{

    private static Map<Long, Cost> costs;
    private static long lastId;

    public CostRepositoryFake(){
        costs=new HashMap<>();
        lastId=0;
    }

    public Map<Long, Cost> getCosts() {
        return costs;
    }

    @Override
    public List<Cost> getAllCosts() {
        return new ArrayList<>(getCosts().values());
    }

    @Override
    public void addCost(Cost cost) {
        if(cost==null)throw new IllegalArgumentException("Cost can't be empty");
        cost.setId(lastId);
        this.getCosts().put(cost.getId(), cost);
        lastId++;
    }

    @Override
    public void deleteCost(Cost cost) {
        if(cost==null)throw new IllegalArgumentException("Cost can't be empty");
        this.getCosts().remove(cost.getId());
    }

    @Override
    public Cost getCostById(long id) {
        if(id<0) throw new DbCostException("Id can't be below 0.");
        return this.getCosts().get(id);
    }

    @Override
    public void updateCost(Cost changedCost) {
        if(this.getCosts().get(changedCost.getId())==null){
            throw new DbCostException("Cost does not exist");
        }
        this.getCosts().replace(changedCost.getId(), changedCost);
    }

    @Override
    public List<Cost> getCostsByEmail(String email) {
        List<Cost> costs = new ArrayList<>();
        for(Cost current:this.getCosts().values()){
            if(current.getOwner().getEmail().equals(email)) costs.add(current);
        }
        return costs;
    }
    
    @Override
    public int calculateAmountOfCostsForUser(String email) {
        return this.getCostsByEmail(email).size();
    }

    @Override
    public double calculateTotalPriceForUser(String email) {
        List<Cost> allCosts = this.getCostsByEmail(email);
        double total = 0;
        for(Cost current:allCosts){
            total+=current.getPrice();
        }
        return total;
    }
    
}
