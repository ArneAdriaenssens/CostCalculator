import facade.CostCalculator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import user.User;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTest {

    private CostCalculator facade;

    @Before
    public void setUp(){
        facade=new CostCalculator();
    }

    @Test
    public void test_creation_facade_makes_userrepository(){
        assertTrue(facade.getUserRepository()!=null);
    }

    @Test
    public void test_get_all_users_gives_correct_list(){
        User owner=new User("Arne", "Adriaenssens", "arne.adriaenssens@email.be", "123");
        facade.addUser(owner);
        List<User> users = facade.getAllUsers();
        assertTrue(users.size()==1);
    }

    @Test
    public void test_get_user_by_email_gives_correct_user(){
        User owner = new User("Arne", "Adriaenssens", "arne.adriaenssens@email.be", "123");
        facade.addUser(owner);
        User vergelijk = facade.getUserByEmail("arne.adriaenssens@email.be");
        assertTrue(vergelijk.equals(owner));
    }

    @Test
    public void test_add_user_adds_valid_user(){
        User owner = new User("Arne", "Adriaenssens", "arne.adriaenssens@email.be", "123");
        facade.addUser(owner);
        assertTrue(facade.getAllUsers().contains(owner));
    }

    @Test
    public void test_delete_user_works_correct(){
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
