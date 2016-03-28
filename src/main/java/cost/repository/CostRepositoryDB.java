/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cost.repository;

import cost.domain.Cost;
import java.util.List;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author arnea
 */
public class CostRepositoryDB implements CostRepository {

    private EntityManagerFactory managerFactory;
    private EntityManager manager;

    public CostRepositoryDB(String name) {
        System.out.println("DIE SHIT:" + name);
        managerFactory = Persistence.createEntityManagerFactory(name);
        manager = managerFactory.createEntityManager();
    }

    @PreDestroy
    public void destruct() {
        System.out.println("ZJRIFJGETIJBITFJBMKITNGETHI¨GQER¨?TLFG?BNTLHFFZEIPGFRHEDGR");
        managerFactory.close();
    }

    @Override
    public List<Cost> getAllCosts() {
        try{
            Query query = manager.createQuery("select s from Cost s");
            return query.getResultList();
        } catch(Exception e){
            throw new DbCostException("Something went wrong when adding a cost");
        }
    }

    @Override
    public void addCost(Cost cost) {
        try{
            if(cost==null) throw new IllegalArgumentException();
            manager.getTransaction().begin();
            manager.persist(cost);
            manager.flush();
            manager.getTransaction().commit();
        } catch(Exception e){
            manager.getTransaction().rollback();
            System.out.println(e.getMessage());
            throw new DbCostException("Something went wrong when adding a cost");
        }
    }

    @Override
    public void deleteCost(Cost cost) {
        try{
            if(cost==null||this.getCostById(cost.getId())==null) throw new IllegalArgumentException();
            manager.getTransaction().begin();
            manager.remove(cost);
            manager.flush();
            manager.getTransaction().commit();
        } catch(Exception e){
            manager.getTransaction().rollback();
            throw new DbCostException("Something went wrong when adding a cost");
        }
    }

    @Override
    public Cost getCostById(long id) {
        try{
            manager.getTransaction().begin();
            Cost cost = manager.find(Cost.class, id);
            manager.getTransaction().commit();
            return cost;
        } catch(Exception e){
            manager.getTransaction().rollback();
            throw new DbCostException("Something went wrong when adding a cost");
        }
    }

    @Override
    public void updateCost(Cost changedCost) {
        try{
            Cost cost = this.getCostById(changedCost.getId());
            manager.getTransaction().begin();
            cost = changedCost;
            manager.flush();
            manager.getTransaction().commit();
        } catch(Exception e){
            manager.getTransaction().rollback();
            throw new DbCostException("Something went wrong when adding a cost");
        }
    }

    
    @Override
    public void closeConnection() {
        try {
            manager.close();
            managerFactory.close();
        } catch(Exception e){
            throw new DbCostException("Closing the connection went wrong");
        }
    }

}
