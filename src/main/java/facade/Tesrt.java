/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import owner.domain.Owner;

/**
 *
 * @author arnea
 */
public class Tesrt {
    public static void main(String[] args){
        CostCalculator fac = new CostCalculatorFacade();
        Owner owner = fac.getUserByEmail("wouter.adriaens@email.be");
        
    }
}
