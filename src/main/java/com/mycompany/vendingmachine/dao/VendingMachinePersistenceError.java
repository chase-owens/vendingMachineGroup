/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

/**
 *
 * @author chaseowens
 */
public class VendingMachinePersistenceError extends Exception {
    public VendingMachinePersistenceError(String message, Throwable cause) {
        super(message, cause);
    }
    public VendingMachinePersistenceError(String message) {
        super(message);
    }
}
