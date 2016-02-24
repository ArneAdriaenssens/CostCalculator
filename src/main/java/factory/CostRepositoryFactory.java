
package factory;

import cost.repository.CostRepository;
import cost.repository.CostRepositoryFake;
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

    public CostRepository createCostRepository(RepositoryTypes type){
        CostRepository costRepository=null;
        if(type.equals(RepositoryTypes.DB)) costRepository=new CostRepositoryFake();
        else if(type.equals(RepositoryTypes.FAKE)) costRepository=new CostRepositoryFake();
        return costRepository;
    }

}
