/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import cost.domain.Cost;
import java.util.List;
import owner.domain.Owner;

/**
 *
 * @author Arne
 */
public interface CostCalculator {

    public List<Cost> getAllCosts();
    public void addCost(Cost cost);
    public void deleteCost(Cost cost);
    public Cost getCostById(long id);
    public void updateCost(Cost changedCost);
    public List<Cost> getCostsByEmail(String email);
    public int calculateAmountOfCostsForUser(String email);
    public double calculateTotalPriceForUser(String email);

    public List<Owner> getAllUsers();
    public Owner getUserByEmail(String email);
    public void addUser(Owner user);
    public void deleteUser(Owner owner);
    public void updateUser(Owner owner);
    
    public void retrieveExchanges();
    public Double getRate(String key);
    public List<String> getRates();
}
