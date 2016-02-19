import cost.Cost;
import cost.FreeTimeCost;
import facade.CostCalculator;
import org.junit.Before;
import org.junit.Test;
import user.User;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Arne on 17/02/2016.
 */
public class CostTest {

    private CostCalculator facade;

    @Before
    public void setUp(){
        facade=new CostCalculator();
    }

    @Test
    public void test_creatie_facade_maakt_costrepository(){
        assertTrue(facade.getCostRepository()!=null);
    }

    @Test
    public void test_creatie_facade_maakt_userrepository(){
        assertTrue(facade.getUserRepository()!=null);
    }

    @Test
    public void test_get_all_cost_geeft_correcte_lijst(){
        List<Cost> costs = facade.getAllCosts();
        assertTrue(costs!=null);
    }


    @Test
    public void test_add_cost_voegt_geldige_cost_toe(){
        User owner=new User("Arne", "Adriaenssens", "arne.adriaenssens@email.be", "123");
        Cost cost = new FreeTimeCost(15.0, owner, "Leuven" );
        facade.addCost(cost);
        assertTrue(facade.getCostRepository().getAllCosts().contains(cost));
    }

    @Test
    public void test_delete_cost_verwijdert_cost(){
        User owner=new User("Arne", "Adriaenssens", "arne.adriaenssens@email.be", "123");
        Cost cost = new FreeTimeCost(15.0, owner, "Leuven" );
        facade.addCost(cost);
        facade.deleteCost(cost);
        assertFalse(facade.getCostRepository().getAllCosts().contains(cost));
    }
}
