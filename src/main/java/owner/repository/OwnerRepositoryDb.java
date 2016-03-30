package owner.repository;

import cost.domain.Cost;
import owner.domain.Owner;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class OwnerRepositoryDb implements OwnerRepository {

    private final String name;

    public OwnerRepositoryDb(String name) {
        this.name=name;
    }

    @Override
    public List<Owner> getAllUsers() {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory(name);
        EntityManager manager = managerFactory.createEntityManager();
        try {
            manager.getTransaction().begin();
            Query query = manager.createQuery("select s from Owner s");
            List<Owner> owners = query.getResultList();
            manager.close();
            return owners;
        } catch (Exception e) {
            managerFactory.close();
            manager.close();
            throw new DbOwnerException("Something went wrong when getting all users");
        }
    }

    @Override
    public Owner getUserByEmail(String email) {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory(name);
        EntityManager manager = managerFactory.createEntityManager();
        try {
            if (email == null || email.equals("")) {
                throw new IllegalArgumentException();
            }
            manager.getTransaction().begin();
            Owner owner = manager.find(owner.domain.Owner.class, email);
            manager.getTransaction().commit();
            manager.close();
            return owner;
        } catch (Exception e) {
            manager.getTransaction().rollback();
            managerFactory.close();
            manager.close();
            System.out.println(e);
            throw new DbOwnerException("Something went wrong when retrieving a user");
        }
    }

    @Override
    public void addUser(Owner user) {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory(name);
        EntityManager manager = managerFactory.createEntityManager();
        try {
            manager.getTransaction().begin();
            if (user == null) {
                throw new IllegalArgumentException();
            }
            manager.persist(user);
            manager.flush();
            manager.getTransaction().commit();
            manager.close();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            managerFactory.close();
            manager.close();
            System.out.println(e.getMessage());
            throw new DbOwnerException("Something went wrong when adding a user");
        }
    }

    @Override
    public void deleteUser(Owner owner) {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory(name);
        EntityManager manager = managerFactory.createEntityManager();
        try {
            manager.getTransaction().begin();
            if (owner == null) {
                throw new IllegalArgumentException();
            }
            owner = manager.find(Owner.class, owner.getEmail());
            manager.remove(owner);
            manager.getTransaction().commit();
            manager.close();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            managerFactory.close();
            manager.close();
            throw new DbOwnerException("Something went wrong when deleting a user");
        }
    }

    @Override
    public void updateCost(Cost cost) {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory(name);
        EntityManager manager = managerFactory.createEntityManager();
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
            managerFactory.close();
            manager.close();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            managerFactory.close();
            manager.close();
            throw new DbOwnerException("Something went wrong when updating a cost");
        }
    }
}
