import cost.Cost;
import cost.FreeTimeCost;
import facade.CostCalculator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import user.User;

import java.util.List;

import static org.junit.Assert.*;

public class CostTest {

    private CostCalculator facade;

    @Before
    public void setUp(){
        facade=new CostCalculator();
    }

    @Test
    public void test_creation_facade_makes_costrepository(){
        assertTrue(facade.getCostRepository()!=null);
    }

    @Test
    public void test_get_all_cost_gives_correct_list(){
        List<Cost> costs = facade.getAllCosts();
        assertTrue(costs!=null);
    }


    @Test
    public void test_add_cost_adds_valid_cost_toe(){
        User owner=new User("Arne", "Adriaenssens", "arne.adriaenssens@email.be", "123");
        Cost cost = new FreeTimeCost(15.0, owner, "Leuven" );
        facade.addCost(cost);
        assertTrue(facade.getCostRepository().getAllCosts().contains(cost));
    }

    @Test
    public void test_delete_cost_deletes_cost(){
        User owner=new User("Arne", "Adriaenssens", "arne.adriaenssens@email.be", "123");
        Cost cost = new FreeTimeCost(15.0, owner, "Leuven" );
        facade.addCost(cost);
        facade.deleteCost(cost);
        assertFalse(facade.getCostRepository().getAllCosts().contains(cost));
    }

    @After
    public void tearDown(){
        facade=null;
    }
}
