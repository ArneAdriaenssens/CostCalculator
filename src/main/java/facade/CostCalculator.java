package facade;

import cost.repository.CostRepository;
import factory.CostRepositoryFactory;
import factory.RepositoryTypes;
import factory.UserRepositoryFactory;
import user.repository.UserRepository;

/**
 * Created by Arne on 17/02/2016.
 */
public class CostCalculator {

    private CostRepository costRepository;
    private UserRepository userRepository;

    public CostCalculator(){
        CostRepositoryFactory costFactory=new CostRepositoryFactory();
        UserRepositoryFactory userFactory=new UserRepositoryFactory();
        try {
            this.costRepository=costFactory.createCostRepository(RepositoryTypes.FAKE);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            this.userRepository=userFactory.createUserRepository(RepositoryTypes.FAKE);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }



}
