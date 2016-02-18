package cost.repository;

import cost.Cost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Arne on 17/02/2016.
 */
public class CostRepositoryFake implements CostRepository{

    private static Map<Long, Cost> costs;
    private long lastId;

    public CostRepositoryFake(){
        costs=new HashMap<Long, Cost>();
        lastId=0;
    }

    public Map<Long, Cost> getCosts() {
        return costs;
    }

    public List<Cost> getAllCosts() {
        return new ArrayList<Cost>(getCosts().values());
    }

    public void addCost(Cost cost) {
        if(cost==null)throw new IllegalArgumentException("Cost can't be empty");
        this.getCosts().put(cost.getId(), cost);
        lastId++;
    }

    public void deleteCost(Cost cost) {
        if(cost==null)throw new IllegalArgumentException("Cost can't be empty");
        this.getCosts().remove(cost);
    }
}
