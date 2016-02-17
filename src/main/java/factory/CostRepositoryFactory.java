
package factory;

import cost.repository.CostRepository;
import user.repository.UserRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * Created by Arne on 11/02/2016.
 */
public class CostRepositoryFactory {

    public CostRepository createCostRepository(RepositoryTypes type) throws ClassNotFoundException, NoSuchMethodException {
        Class<CostRepository> klasse = (Class<CostRepository>) Class.forName("CostRepository"+type.getValue());
        Constructor<CostRepository> construct = klasse.getConstructor();
        CostRepository costRepository=null;
        try {
            costRepository =  construct.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return costRepository;
    }

}
