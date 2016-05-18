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
    private EntityManagerFactory managerFactory;
    private EntityManager manager;

    public CostRepositoryDB(String name) {
        this.name = name;
    }

    @Override
    public List<Cost> getAllCosts() {
        this.openConnection();
        try {
            manager.getTransaction().begin();
            Query query = manager.createQuery("select s from Cost s");
            List<Cost> costs = query.getResultList();
            return costs;
        } catch (Exception e) {
            throw new DbCostException("Something went wrong when getting all costs");
        } finally {
            closeConnection();
        }
    }

    @Override
    public void addCost(Cost cost) {
        this.openConnection();
        try {
            manager.getTransaction().begin();
            if (cost == null) {
                throw new IllegalArgumentException("cost is empty");
            }
            manager.persist(cost);
            manager.flush();
            manager.getTransaction().commit();
            cost.getOwner().addCosts(cost);
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new DbCostException("Something went wrong when adding a cost");
        } finally {
            closeConnection();
        }
    }

    @Override
    public void deleteCost(Cost cost) {
        boolean containsCost = this.getAllCosts().contains(cost);
        this.openConnection();
        try {
            manager.getTransaction().begin();
            if (cost == null || !containsCost) {
                throw new IllegalArgumentException("Cost is empty or not existing");
            }
            cost = manager.find(Cost.class, cost.getId());
            manager.remove(cost);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println(e.getMessage());
            throw new DbCostException("Something went wrong when deleting a cost");
        } finally {
            closeConnection();
        }
    }

    @Override
    public Cost getCostById(long id) {
        this.openConnection();
        try {
            manager.getTransaction().begin();
            Cost cost = manager.find(cost.domain.Cost.class, id);
            manager.getTransaction().commit();
            return cost;
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new DbCostException("Something went wrong when retreiving a cost");
        } finally {
            closeConnection();
        }
    }

    @Override
    public void updateCost(Cost changedCost) {
        this.openConnection();
        try {
            manager.getTransaction().begin();
            if(changedCost==null) throw new IllegalArgumentException("Changed cost can't be empty");
            Cost cost = manager.find(cost.domain.Cost.class, changedCost.getId());
            cost.setPrice(changedCost.getPrice());
            cost.setLocation(changedCost.getLocation());
            cost.setDescription(changedCost.getDescription());
            cost.setCategory(changedCost.getCategory());
            manager.flush();
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new DbCostException("Something went wrong when updating a cost");
        } finally {
            closeConnection();
        }
    }

    @Override
    public List<Cost> getCostsByEmail(String email) {
        this.openConnection();
        try {
            manager.getTransaction().begin();
            Query query = manager.createQuery("select s from Cost s where s.owner.email=:mail");
            query.setParameter("mail", email);
            List<Cost> costs = query.getResultList();
            return costs;
        } catch (Exception e) {
            throw new DbCostException("Something went wrong when showing all cost");
        } finally {
            closeConnection();
        }
    }

    @Override
    public int calculateAmountOfCostsForUser(String email) {
        return this.getCostsByEmail(email).size();
    }

    @Override
    public double calculateTotalPriceForUser(String email) {
        List<Cost> allCosts = this.getCostsByEmail(email);
        double total = 0;
        for (Cost current : allCosts) {
            total += current.getPrice();
        }
        total = total*100;
        total = Math.round(total);
        return total/100;
    }

    public void openConnection() {
        managerFactory = Persistence.createEntityManagerFactory(name);
        manager = managerFactory.createEntityManager();
    }

    public void closeConnection() {
        manager.close();
        managerFactory.close();
    }

}
