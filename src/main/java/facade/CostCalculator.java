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
        try {
            this.costRepository=new CostRepositoryFactory().createCostRepository(RepositoryTypes.FAKE);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            this.userRepository=new UserRepositoryFactory().createUserRepository(RepositoryTypes.FAKE);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public CostRepository getCostRepository() {
        return costRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
