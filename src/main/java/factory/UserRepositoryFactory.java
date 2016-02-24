package factory;

import user.User;
import user.repository.UserRepository;
import user.repository.UserRepositoryDb;
import user.repository.UserRepositoryFake;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Arne on 17/02/2016.
 */
public class UserRepositoryFactory {

    public UserRepository createUserRepository(RepositoryTypes type) {
        UserRepository userRepo = null;
        if(type.equals(RepositoryTypes.FAKE)) userRepo = new UserRepositoryFake();
        else if (type.equals(RepositoryTypes.DB)) userRepo=new UserRepositoryDb();
        return userRepo;
    }
}
