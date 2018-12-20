/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.Item;

/**
 *
 * @author chaseowens
 */
public interface AuditDao {
    public void writeAuditEntry(Item item, String report) throws VendingMachinePersistenceError;
    
    public void writeAuditEntryWithString(String report) throws VendingMachinePersistenceError;
}
