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
        Owner owner = new Owner("Arne", "Adriaenssens", "arne.adriaenssens@email.be", "123");
        Cost cost = new Cost(15.0, "Leuven", owner, Category.FREETIME, "Having fun" );
        if(!facade.getAllUsers().contains(owner)) facade.addUser(owner);
        facade.addCost(cost);
    }

    @Test
    public void test_getAllCosts_gives_correct_list(){
        List<Cost> costs = facade.getAllCosts();
        assertTrue(!costs.isEmpty());
    }


    @Test
    public void test_addCost_adds_valid_cost_toe(){
        Owner owner=new Owner("Arne", "Adriaenssens", "arne.adriaenssens@email.be", "123");
        Cost cost = new Cost(15.0, "Leuven", owner, Category.FREETIME, "Having fun" );
        facade.addCost(cost);

        assertTrue(facade.getAllCosts().contains(cost));
    }

    @Test
    public void test_addCost_adds_multiple_valid_cost_toe(){
        int size  = facade.getAllCosts().size();
        Owner owner=new Owner("Arne", "Adriaenssens", "arne.adriaenssens@email.be", "123");
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
        Owner test = facade.getUserByEmail("arne.adriaenssens@email.be");
        System.out.println("test:" + test);
        System.out.println(owner);
        System.out.println(test.equals(owner));
        assertTrue(facade.getAllUsers().contains(owner));
    }

    @Test
    public void test_deleteUser_works_correct(){
        int rand = (int) Math.ceil(Math.random()*100);
        Owner owner = new Owner("Arne", "Adriaenssens", "jposke"+rand+".adriaenssens@email.be", "123");
        facade.addUser(owner);
        facade.deleteUser(owner);
        assertFalse(facade.getAllUsers().contains(owner));
    }

    @After
    public void tearDown(){
        facade.getCostRepository().closeConnection();
        facade=null;
    }
}
