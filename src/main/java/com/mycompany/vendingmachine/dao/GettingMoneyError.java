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
public class GettingMoneyError extends Exception {
    
    public GettingMoneyError(String message) {
        super(message);
    }
    
    public GettingMoneyError(String message, Throwable cause) {
        super(message, cause);
    }
}
