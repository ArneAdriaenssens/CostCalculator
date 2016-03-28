package cost.repository;

import cost.domain.Cost;

import java.util.List;

/**
 * Created by Arne on 11/02/2016.
 */
public interface CostRepository {

    List<Cost> getAllCosts();
    void addCost(Cost cost);
    void deleteCost(Cost cost);
    Cost getCostById(long id);
    void updateCost(Cost changedCost);
    void closeConnection();

}
