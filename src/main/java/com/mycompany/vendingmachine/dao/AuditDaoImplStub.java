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
public class AuditDaoImplStub implements AuditDao{

    @Override
    public void writeAuditEntry(Item item, String report) throws VendingMachinePersistenceError {
        // Do nothing
    }

    @Override
    public void writeAuditEntryWithString(String report) throws VendingMachinePersistenceError {
        // Do nothing
    }
    
}
