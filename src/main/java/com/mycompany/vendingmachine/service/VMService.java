/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.dao.GetEntryError;
import com.mycompany.vendingmachine.dao.GettingMoneyError;
import com.mycompany.vendingmachine.dao.InsufficientFundsError;
import com.mycompany.vendingmachine.dao.OutOfStockException;
import com.mycompany.vendingmachine.dao.VendingMachinePersistenceError;
import com.mycompany.vendingmachine.dto.ChangeMaker;
import com.mycompany.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.Collection;
import org.springframework.stereotype.Service;

/**
 *
 * @author chaseowens
 */
@Service
public interface VMService {

    public Collection<Item> getItems() throws VendingMachinePersistenceError, GetEntryError;

    public BigDecimal processTransaction(BigDecimal $, String selection) throws InsufficientFundsError, OutOfStockException, VendingMachinePersistenceError, GetEntryError ;

    public ChangeMaker makeChange(BigDecimal change);

    public void auditFile(String report) throws VendingMachinePersistenceError;

    public Item getItem(String selection) throws GetEntryError;

    public BigDecimal checkMoney(String cash) throws GettingMoneyError;
    
}
