import facade.CostCalculator;
import org.junit.Before;
import org.junit.Test;
import user.User;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Arne on 19/02/2016.
 */
public class UserTest {

    private CostCalculator facade;

    @Before
    public void setUp(){
        facade=new CostCalculator();
    }

    @Test
    public void test_creatie_facade_maakt_userrepository(){
        assertTrue(facade.getUserRepository()!=null);
    }

    @Test
    public void test_get_all_users_geeft_correcte_lijst(){
        User owner=new User("Arne", "Adriaenssens", "arne.adriaenssens@email.be", "123");
        facade.addUser(owner);
        List<User> users = facade.getAllUsers();
        assertTrue(users.size()==1);
    }

    @Test
    public void test_get_user_by_email_geeft_juiste_user(){
        User owner = new User("Arne", "Adriaenssens", "arne.adriaenssens@email.be", "123");
        facade.addUser(owner);
        User vergelijk = facade.getUserByEmail("arne.adriaenssens@email.be");
        assertTrue(vergelijk.equals(owner));
    }

    @Test
    public void test_add_user_voegt_geldige_user_toe(){
        User owner = new User("Arne", "Adriaenssens", "arne.adriaenssens@email.be", "123");
        facade.addUser(owner);
        assertTrue(facade.getAllUsers().contains(owner));
    }

    @Test
    public void test_delete_user_werkt_correct(){
        User owner = new User("Arne", "Adriaenssens", "arne.adriaenssens@email.be", "123");
        facade.addUser(owner);
        facade.deleteUser("arne.adriaenssens@email.be");
        assertFalse(facade.getAllUsers().contains(owner));
    }
}
