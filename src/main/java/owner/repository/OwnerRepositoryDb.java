package owner.repository;

import owner.domain.Owner;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class OwnerRepositoryDb implements OwnerRepository {

    private final String name;
    private EntityManagerFactory managerFactory;
    private EntityManager manager;

    public OwnerRepositoryDb(String name) {
        this.name=name;
    }

    @Override
    public List<Owner> getAllUsers() {
        openConnection();
        try {
            manager.getTransaction().begin();
            Query query = manager.createQuery("select s from Owner s");
            List<Owner> owners = query.getResultList();
            return owners;
        } catch (Exception e) {
            throw new DbOwnerException("Something went wrong when getting all users");
        } finally{
            closeConnection();
        }
    }

    @Override
    public Owner getUserByEmail(String email) {
        openConnection();
        try {
            manager.getTransaction().begin();
            if (email == null || email.equals("")) {
                throw new IllegalArgumentException();
            }
            Owner owner = manager.find(owner.domain.Owner.class, email);
            manager.getTransaction().commit();
            return owner;
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println(e);
            throw new DbOwnerException("Something went wrong when retrieving a user");
        } finally{
            closeConnection();
        }
    }

    @Override
    public void addUser(Owner user) {
        openConnection();
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
        } finally{
            closeConnection();
        }
    }

    @Override
    public void deleteUser(Owner owner) {
        openConnection();
        try {
            manager.getTransaction().begin();
            if (owner == null) {
                throw new IllegalArgumentException("owner is empty");
            }
            owner = manager.find(Owner.class, owner.getEmail());
            manager.remove(owner);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new DbOwnerException("Something went wrong when deleting a user");
        } finally{
            closeConnection();
        }
    }
    
    public void openConnection(){
        managerFactory = Persistence.createEntityManagerFactory(name);
        manager = managerFactory.createEntityManager();
    }
    
    public void closeConnection(){
        manager.close();
        managerFactory.close();
    }
}
