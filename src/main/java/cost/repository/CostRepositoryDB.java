/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cost.repository;

import cost.domain.Cost;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author arnea
 */
public class CostRepositoryDB implements CostRepository {

    private final String name;

    public CostRepositoryDB(String name) {
        this.name = name;
    }

    @Override
    public List<Cost> getAllCosts() {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory(name);
        EntityManager manager = managerFactory.createEntityManager();
        try {
            manager.getTransaction().begin();
            Query query = manager.createQuery("select s from Cost s");
            List<Cost> costs = query.getResultList();
            manager.close();
            managerFactory.close();
            return costs;
        } catch (Exception e) {
            manager.close();
            managerFactory.close();
            throw new DbCostException("Something went wrong when getting all costs");
        }
    }

    @Override
    public void addCost(Cost cost) {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory(name);
        EntityManager manager = managerFactory.createEntityManager();
        try {
            if (cost == null) {
                throw new IllegalArgumentException();
            }
            manager.getTransaction().begin();
            manager.persist(cost);
            manager.flush();
            manager.getTransaction().commit();
            manager.close();
            managerFactory.close();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            manager.close();
            managerFactory.close();
            System.out.println(e.getMessage());
            throw new DbCostException("Something went wrong when adding a cost");
        }
    }

    @Override
    public void deleteCost(Cost cost) {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory(name);
        EntityManager manager = managerFactory.createEntityManager();
        try {
            if (cost == null || !this.getAllCosts().contains(cost)) {
                throw new IllegalArgumentException();
            }
            manager.getTransaction().begin();
            cost = manager.find(Cost.class, cost.getId());
            manager.remove(cost);
            manager.getTransaction().commit();
            manager.close();
            managerFactory.close();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            manager.close();
            managerFactory.close();
            System.out.println(e.getMessage());
            throw new DbCostException("Something went wrong when deleting a cost");
        }
    }

    @Override
    public Cost getCostById(long id) {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory(name);
        EntityManager manager = managerFactory.createEntityManager();
        try {
            manager.getTransaction().begin();
            Cost cost = manager.find(cost.domain.Cost.class, id);
            manager.getTransaction().commit();
            manager.close();
            managerFactory.close();
            return cost;
        } catch (Exception e) {
            manager.getTransaction().rollback();
            manager.close();
            managerFactory.close();
            throw new DbCostException("Something went wrong when retreiving a cost");
        }
    }

    @Override
    public void updateCost(Cost changedCost) {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory(name);
        EntityManager manager = managerFactory.createEntityManager();
        try {
            manager.getTransaction().begin();
            Cost cost = this.getCostById(changedCost.getId());
            cost = changedCost;
            manager.flush();
            manager.getTransaction().commit();
            manager.close();
            managerFactory.close();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            manager.close();
            managerFactory.close();
            throw new DbCostException("Something went wrong when updating a cost");
        }
    }

    @Override
    public List<Cost> getCostsByEmail(String email) {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory(name);
        EntityManager manager = managerFactory.createEntityManager();
        try {
            manager.getTransaction().begin();
            Query query = manager.createQuery("select s from Cost s where s.owner.email=:mail");
            query.setParameter("mail", email);
            List<Cost> costs = query.getResultList();
            manager.close();
            managerFactory.close();
            return costs;
        } catch (Exception e) {
            manager.close();
            managerFactory.close();
            throw new DbCostException("Something went wrong when showing all cost");
        }
    }

}
