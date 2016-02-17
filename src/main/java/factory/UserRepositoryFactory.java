package factory;

import user.User;
import user.repository.UserRepository;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Arne on 17/02/2016.
 */
public class UserRepositoryFactory {

    public UserRepository createUserRepository(RepositoryTypes type) throws ClassNotFoundException, NoSuchMethodException {
        Class<UserRepository> klasse = (Class<UserRepository>) Class.forName("UserRepository"+type.getValue());
        Constructor<UserRepository> construct = klasse.getConstructor();
        UserRepository userRepository=null;
        try {
            userRepository =  construct.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return userRepository;
    }
}
