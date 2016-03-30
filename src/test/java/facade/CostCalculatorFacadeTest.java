package facade;

import cost.domain.Category;
import cost.domain.Cost;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import owner.domain.Owner;

import java.util.List;

import static org.junit.Assert.*;

public class CostCalculatorFacadeTest {

    private CostCalculatorFacade facade;

    @Before
    public void setUp(){
        facade=new CostCalculatorFacade();
        Owner owner=new Owner("Arne", "Adriaenssens", "arne.adriaenssens@email.be", "123");
        if(facade.getUserByEmail("arne.adriaenssens@email.be")==null) facade.addUser(owner);
    }

    @Test
    public void test_getAllCosts_gives_correct_list(){
        Owner owner = facade.getUserByEmail("arne.adriaenssens@email.be");
        Cost cost = new Cost(15.0, "Leuven", owner, Category.FREETIME, "Having fun" );
        facade.addCost(cost);
        List<Cost> costs = facade.getAllCosts();
        assertTrue(!costs.isEmpty());
    }


    @Test
    public void test_addCost_adds_valid_cost_toe(){
        Owner owner = facade.getUserByEmail("arne.adriaenssens@email.be");
        Cost cost = new Cost(15.0, "Leuven", owner, Category.FREETIME, "Having fun" );
        facade.addCost(cost);
        assertTrue(facade.getAllCosts().contains(cost));
    }

    @Test
    public void test_addCost_adds_multiple_valid_cost_toe(){
        int size=facade.getAllCosts().size();
        Owner owner = facade.getUserByEmail("arne.adriaenssens@email.be");
        Cost cost1 = new Cost(15.0, "Leuven", owner, Category.FREETIME, "Plezier maken" );
        Cost cost2 = new Cost(15.0, "Leuven", owner, Category.DRINKS, "Arne nog eens trakteren" );
        facade.addCost(cost1);
        facade.addCost(cost2);
        size +=2;
        assertTrue(size == facade.getAllCosts().size());
    }

    @Test
    public void test_deleteCost_deletes_cost(){
        Owner owner = facade.getUserByEmail("arne.adriaenssens@email.be");
        Cost cost = new Cost(15.0,"Leuven", owner, Category.FREETIME, "Afzien" );
        facade.addCost(cost);
        facade.deleteCost(cost);
        assertFalse(facade.getAllCosts().contains(cost));
    }
    
    @Test
    public void test_get_cost_by_id_gives_correct_cost(){
        Owner owner = facade.getUserByEmail("arne.adriaenssens@email.be");
        Cost cost = new Cost(15.0,"Leuven", owner, Category.FREETIME, "Afzien" );
        facade.addCost(cost);
        Cost test = facade.getCostById(cost.getId());
        assertEquals(test, cost);
    }

    @Test
    public void test_getAllUsers_gives_correct_list(){
        List<Owner> users = facade.getAllUsers();
        assertTrue(!users.isEmpty());
    }

    @Test
    public void test_getUserByEmail_gives_correct_user(){
        Owner owner = new Owner("Arne", "Adriaenssens", "arne.adriaenssens@email.be", "123");
        Owner vergelijk = facade.getUserByEmail("arne.adriaenssens@email.be");
        assertTrue(vergelijk.equals(owner));
    }

    @Test
    public void test_addUser_adds_valid_user(){
        Owner owner = new Owner("Arne", "Adriaenssens", "arne.adriaenssens@email.be", "123");
        assertTrue(facade.getAllUsers().contains(owner));
    }

    @Test
    public void test_deleteUser_works_correct(){
        int rand = (int) Math.ceil(Math.random()*1000);
        Owner owner = new Owner("Arne", "Adriaenssens", "jposke"+rand+".adriaenssens@email.be", "123");
        facade.addUser(owner);
        facade.deleteUser(owner);
        assertFalse(facade.getAllUsers().contains(owner));
    }

    @After
    public void tearDown(){
        facade=null;
    }
}
