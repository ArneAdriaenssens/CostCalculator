/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

/**
 *
 * @author Arne
 */
public class FacadeException extends Exception {
    public FacadeException(String message){
        super(message);
    }
    
    public FacadeException(String message, Exception e){
        super(message, e);
    }
}
