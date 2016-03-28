/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import cost.domain.Category;
import cost.domain.Cost;
import owner.domain.Owner;

/**
 *
 * @author arnea
 */
public class test {
    public static void main(String[] args){
        CostCalculatorFacade facade = new CostCalculatorFacade();
        Owner owner = facade.getUserByEmail("arne.adriaenssens@email.be");
        Cost cost = new Cost(48, "Leuven", owner, Category.FREETIME, "genieten");
        facade.addCost(cost);
    }
}
