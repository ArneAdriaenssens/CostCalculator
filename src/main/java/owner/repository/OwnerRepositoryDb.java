package owner.repository;

import cost.domain.Cost;
import owner.domain.Owner;

import java.util.List;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class OwnerRepositoryDb implements OwnerRepository {

    private EntityManagerFactory managerFactory;
    private EntityManager manager;

    public OwnerRepositoryDb(String name) {
        if (managerFactory != null) {
            this.closeConnection();
        }
        managerFactory = Persistence.createEntityManagerFactory(name);
        manager = managerFactory.createEntityManager();
    }

    @PreDestroy
    public void destruct() {
        System.out.println("ZJRIFJGETIJBITFJBMKITNGETHI¨GQER¨?TLFG?");
        managerFactory.close();
    }

    @Override
    public List<Owner> getAllUsers() {
        try {
            Query query = manager.createQuery("select s from Owner s");
            return query.getResultList();
        } catch (Exception e) {
            throw new DbOwnerException("Something went wrong when getting all users");
        }
    }

    @Override
    public Owner getUserByEmail(String email) {
        try {
            manager.getTransaction().begin();
            if (email == null || email.equals("")) {
                throw new IllegalArgumentException();
            }
            Owner owner = manager.find(owner.domain.Owner.class, email);
            return owner;
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println(e);
            throw new DbOwnerException("Something went wrong when retrieving a user");
        }
    }

    @Override
    public void addUser(Owner user) {
        try {
            manager.getTransaction().begin();
            if (user == null) {
                throw new IllegalArgumentException();
            }
            manager.persist(user);
            manager.flush();
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println(e.getMessage());
            throw new DbOwnerException("Something went wrong when adding a user");
        }
    }

    @Override
    public void deleteUser(Owner owner) {
        try {
            manager.getTransaction().begin();
            if (owner == null) {
                throw new IllegalArgumentException();
            }
            manager.remove(owner);
            manager.flush();
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new DbOwnerException("Something went wrong when deleting a user");
        }
    }

    @Override
    public void updateCost(Cost cost) {
        try {
            manager.getTransaction().begin();
            if (cost == null) {
                throw new IllegalArgumentException();
            }
            Owner owner = manager.find(Owner.class, cost.getOwner().getEmail());
            if (owner.getCosts().contains(cost)) {
                owner.getCosts().remove(cost);
                owner.addCosts(cost);
            }
            manager.flush();
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new DbOwnerException("Something went wrong when updating a user");
        }
    }

    @Override
    public void closeConnection() {
        try {
            manager.close();
            managerFactory.close();
        } catch (Exception e) {
            throw new DbOwnerException(e.getMessage());
        }
    }
}
