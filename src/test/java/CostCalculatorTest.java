import cost.Cost;
import cost.FreeTimeCost;
import cost.repository.DbCostException;
import facade.CostCalculator;
import org.junit.Before;
import org.junit.Test;
import user.User;
import user.repository.DbUserException;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Arne on 17/02/2016.
 */
public class CostCalculatorTest {

    private CostCalculator facade;

    @Before
    public void setUp(){
        facade=new CostCalculator();
    }

    @Test
    public void test_creatie_facade_maakt_costrepository(){
        assertEquals(true,facade.getCostRepository()!=null);
    }

    @Test
    public void test_creatie_facade_maakt_userrepository(){
        assertEquals(true,facade.getUserRepository()!=null);
    }

    @Test
    public void test_get_all_cost_geeft_correcte_lijst(){
        List<Cost> costs = null;
        boolean test=false;
        costs = facade.getAllCosts();
        test=(costs!=null);
        assert(test);
    }

    @Test
    public void test_get_all_users_geeft_correcte_lijst(){
        List<User> users = null;
        boolean test=false;
        users = facade.getAllUsers();
        test=(users!=null);
        assertTrue(test);
    }

    @Test
    public void test_add_cost_voegt_geldige_cost_toe(){
        User owner=null;
        owner = new User("Arne", "Adriaenssens", "arne.adriaenssens@email.be", "123");
        Cost cost = new FreeTimeCost(15.0, owner, "Leuven" );
        facade.addCost(cost);
        boolean test= false;
        try {
            test = facade.getCostRepository().getAllCosts().contains(cost);
        } catch (DbCostException e) {
            e.printStackTrace();
        }
        assertTrue(test);
    }

    @Test
    public void test_delete_cost_verwijdert_cost(){
        User owner=null;
        owner = new User("Arne", "Adriaenssens", "arne.adriaenssens@email.be", "123");
        Cost cost = new FreeTimeCost(15.0, owner, "Leuven" );
        facade.deleteCost(cost);
        boolean test= true;
        try {
            test = facade.getCostRepository().getAllCosts().contains(cost);
        } catch (DbCostException e) {
            e.printStackTrace();
        }
        assertFalse(test);
    }

    @Test
    public void test_get_user_by_email_geeft_juiste_user(){
        User owner=null;
        owner = new User("Arne", "Adriaenssens", "arne.adriaenssens@email.be", "123");
        facade.addUser(owner);
        User vergelijk = facade.getUserByEmail("arne.adriaenssens@email.be");
        assertTrue(vergelijk.equals(owner));
    }

    @Test
    public void test_add_user_voegt_geldige_user_toe(){
        User owner=null;
        owner = new User("Arne", "Adriaenssens", "arne.adriaenssens@email.be", "123");
        facade.addUser(owner);
        boolean test=facade.getAllUsers().contains(owner);
        assertTrue(test);
    }

    @Test
    public void test_delete_user_werkt_correct(){
        User owner=null;
        owner = new User("Arne", "Adriaenssens", "arne.adriaenssens@email.be", "123");
        facade.addUser(owner);
        facade.deleteUser("arne.adriaenssens@email.be");
        boolean test=facade.getAllUsers().contains(owner);
        assertFalse(test);
    }

}
