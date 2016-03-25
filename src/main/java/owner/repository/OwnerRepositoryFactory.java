package owner.repository;

import common.RepositoryTypes;

/**
 * Created by Arne on 17/02/2016.
 */
public class OwnerRepositoryFactory {

    public OwnerRepository createUserRepository(RepositoryTypes type) {
        OwnerRepository userRepo = null;
        if(type.equals(RepositoryTypes.FAKE)) userRepo = OwnerRepositoryFake.getInstance();
        else if (type.equals(RepositoryTypes.DB)) userRepo=new OwnerRepositoryDb();
        return userRepo;
    }
}
