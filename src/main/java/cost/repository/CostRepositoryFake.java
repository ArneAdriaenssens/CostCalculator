package cost.repository;

import cost.Cost;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Arne on 17/02/2016.
 */
public class CostRepositoryFake implements CostRepository{

    private static Map<Long, Cost> costs;

    public CostRepositoryFake(){
        costs=new HashMap<Long, Cost>();
    }

    public Map<Long, Cost> getCosts() {
        return costs;
    }

    public List<Cost> getAllCosts() {
        return (List<Cost>) getCosts().values();
    }

    public void addCost(Cost cost) {
        if(cost==null)throw new IllegalArgumentException("Cost can't be empty");
        this.getCosts().put(cost.getId(), cost);
    }

    public void deleteCost(Cost cost) {
        if(cost==null)throw new IllegalArgumentException("Cost can't be empty");
        this.getCosts().remove(cost);
    }
}
