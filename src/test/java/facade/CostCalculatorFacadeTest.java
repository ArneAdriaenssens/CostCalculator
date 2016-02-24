package facade;

import cost.domain.Cost;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import user.domain.User;

import java.util.List;

import static org.junit.Assert.*;

public class CostCalculatorFacadeTest {

    private CostCalculatorFacade facade;

    @Before
    public void setUp(){
        facade=new CostCalculatorFacade("FAKE");
    }

    @Test
    public void test_getAllCosts_gives_correct_list(){
        List<Cost> costs = facade.getAllCosts();
        assertTrue(costs!=null);
    }


    @Test
    public void test_addCost_adds_valid_cost_toe(){
        User owner=new User("Arne", "Adriaenssens", "arne.adriaenssens@email.be", "123");
        Cost cost = new Cost(15.0, owner, "Leuven", "FREETIME" );
        facade.addCost(cost);

        assertTrue(facade.getAllCosts().contains(cost));
    }

    @Test
    public void test_addCost_adds_multiple_valid_cost_toe(){
        User owner=new User("Arne", "Adriaenssens", "arne.adriaenssens@email.be", "123");
        Cost cost1 = new Cost(15.0, owner, "Leuven", "FREETIME" );
        Cost cost2 = new Cost(15.0, owner, "Leuven", "DRINKS" );
        facade.addCost(cost1);
        facade.addCost(cost2);
        assertTrue(facade.getAllCosts().size()==2);
    }

    @Test
    public void test_deleteCost_deletes_cost(){
        User owner=new User("Arne", "Adriaenssens", "arne.adriaenssens@email.be", "123");
        Cost cost = new Cost(15.0, owner, "Leuven", "FREETIME" );
        facade.addCost(cost);
        facade.deleteCost(cost);
        assertFalse(facade.getAllCosts().contains(cost));
    }

    @Test
    public void test_getAllUsers_gives_correct_list(){
        User owner=new User("Arne", "Adriaenssens", "arne.adriaenssens@email.be", "123");
        facade.addUser(owner);
        List<User> users = facade.getAllUsers();
        assertTrue(users.size()==1);
    }

    @Test
    public void test_getUserByEmail_gives_correct_user(){
        User owner = new User("Arne", "Adriaenssens", "arne.adriaenssens@email.be", "123");
        facade.addUser(owner);
        User vergelijk = facade.getUserByEmail("arne.adriaenssens@email.be");
        assertTrue(vergelijk.equals(owner));
    }

    @Test
    public void test_addUser_adds_valid_user(){
        User owner = new User("Arne", "Adriaenssens", "arne.adriaenssens@email.be", "123");
        facade.addUser(owner);
        assertTrue(facade.getAllUsers().contains(owner));
    }

    @Test
    public void test_deleteUser_works_correct(){
        User owner = new User("Arne", "Adriaenssens", "arne.adriaenssens@email.be", "123");
        facade.addUser(owner);
        facade.deleteUser("arne.adriaenssens@email.be");
        assertFalse(facade.getAllUsers().contains(owner));
    }

    @After
    public void tearDown(){
        facade=null;
    }
}
