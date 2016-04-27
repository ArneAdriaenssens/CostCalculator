package facade;

import common.RepositoryTypes;
import cost.domain.Category;
import cost.domain.Cost;
import org.junit.After;
import org.junit.Test;
import owner.domain.Owner;

import java.util.List;

import static org.junit.Assert.*;

public class CostCalculatorFacadeTest {

    private CostCalculatorFacade facade;
    private Owner owner;
    private Cost cost;
    
    public CostCalculatorFacadeTest(){
        facade = new CostCalculatorFacade(RepositoryTypes.DB);
        owner = new Owner("Arne", "Adriaenssens", "arne9.adriaenssens@email.be", "123");
        cost = new Cost(15.0, "Leuven", owner, Category.FREETIME, "Having fun");
    }

    @Test
    public void test_getAllCosts_gives_correct_list() {
        facade.addUser(owner);
        facade.addCost(cost);
        List<Cost> costs = facade.getAllCosts();
        facade.deleteUser(owner);
        assertTrue(!costs.isEmpty());
    }

    @Test
    public void test_addCost_adds_valid_cost_toe() {
        facade.addUser(owner);
        facade.addCost(cost);
        boolean bevat = facade.getAllCosts().contains(cost);
        facade.deleteUser(owner);
        assertTrue(bevat);
    }

    @Test
    public void test_addCost_adds_multiple_valid_cost_toe() {
        facade.addUser(owner);
        int size = facade.getAllCosts().size();
        Cost cost1 = new Cost(15.0, "Leuven", owner, Category.FREETIME, "Plezier maken");
        Cost cost2 = new Cost(15.0, "Leuven", owner, Category.DRINKS, "Arne nog eens trakteren");
        facade.addCost(cost1);
        facade.addCost(cost2);
        size += 2;
        int sizeList = facade.getAllCosts().size();
        facade.deleteCost(cost1);
        facade.deleteCost(cost2);
        facade.deleteUser(owner);
        assertTrue(size == sizeList);
    }

    @Test
    public void test_deleteCost_deletes_cost() {
        facade.addUser(owner);
        facade.addCost(cost);
        facade.deleteUser(owner);
        assertFalse(facade.getAllCosts().contains(cost));
    }

    @Test
    public void test_get_cost_by_id_gives_correct_cost() {
        facade.addUser(owner);
        facade.addCost(cost);
        Cost test = facade.getCostById(cost.getId());
        facade.deleteUser(owner);
        assertEquals(test, cost);
    }

    @Test
    public void test_getAllUsers_gives_correct_list() {
        facade.addUser(owner);
        List<Owner> users = facade.getAllUsers();
        facade.deleteUser(owner);
        assertTrue(!users.isEmpty());
    }

    @Test
    public void test_getUserByEmail_gives_correct_user() {
        facade.addUser(owner);
        Owner vergelijk = facade.getUserByEmail("arne9.adriaenssens@email.be");
        facade.deleteUser(owner);
        assertTrue(vergelijk.equals(owner));
    }

    @Test
    public void test_addUser_adds_valid_user() {
        facade.addUser(owner);
        boolean bevat = facade.getAllUsers().contains(owner);
        facade.deleteUser(owner);
        assertTrue(bevat);
    }
    
    @Test
    public void test_delete_user_deletes_all_costs_of_that_user(){
        facade.addUser(owner);
        facade.addCost(cost);
        int rand = (int) Math.ceil(Math.random() * 1000);
        Owner owner1 = new Owner("Arne", "Adriaenssens", "jposke" + rand + ".adriaenssens@email.be", "123");
        Cost cost1 = new Cost(15.0, "Leuven", owner1, Category.FREETIME, "Plezier maken");
        Cost cost2 = new Cost(15.0, "Leuven", owner1, Category.DRINKS, "Arne nog eens trakteren");
        facade.addUser(owner1);
        facade.addCost(cost1);
        facade.addCost(cost2);
        facade.deleteUser(owner);
        List<Cost> costs = facade.getAllCosts();
        facade.deleteUser(owner1);
        assertFalse(costs.isEmpty());
    }

    @Test
    public void test_deleteUser_works_correct() {
        int rand = (int) Math.ceil(Math.random() * 1000);
        Owner owner1 = new Owner("Arne", "Adriaenssens", "jposke" + rand + ".adriaenssens@email.be", "123");
        facade.addUser(owner1);
        facade.deleteUser(owner1);
        assertFalse(facade.getAllUsers().contains(owner1));
    }

    @After
    public void tearDown() {
        facade = null;
    }
}
