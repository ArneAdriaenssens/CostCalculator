
package cost.repository;

import java.lang.reflect.Constructor;
import java.util.Properties;

/**
 * Created by Arne on 11/02/2016.
 */
public class RepositoryFactory {

    public CostRepository createCostRepository(RepositoryTypes type) throws ClassNotFoundException, NoSuchMethodException {
        Class clas = Class.forName("CostRepository"+type);
        Constructor construct = clas.getConstructor();
        Properties properties = new Properties();
        properties.getProperty()
    }

}
