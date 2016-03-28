
package cost.repository;

import common.RepositoryTypes;
/**
 * Created by Arne on 11/02/2016.
 */
public class CostRepositoryFactory {

    public CostRepository createCostRepository(RepositoryTypes type){
        CostRepository costRepository=null;
        if(type.equals(RepositoryTypes.DB)) costRepository=new CostRepositoryDB("CostCalculatorPU");
        else if(type.equals(RepositoryTypes.FAKE)) costRepository=new CostRepositoryFake();
        return costRepository;
    }

}
