/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.dao.AuditDao;
import com.mycompany.vendingmachine.dao.Dao;
import com.mycompany.vendingmachine.dao.GetEntryError;
import com.mycompany.vendingmachine.dao.GettingMoneyError;
import com.mycompany.vendingmachine.dao.InsufficientFundsError;
import com.mycompany.vendingmachine.dao.OutOfStockException;
import com.mycompany.vendingmachine.dao.VendingMachinePersistenceError;
import com.mycompany.vendingmachine.dto.ChangeMaker;
import com.mycompany.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chaseowens
 */
@Service
public class ServiceImpl implements VMService {

    @Autowired
    Dao dao;
    @Autowired
    AuditDao audit;

    public ServiceImpl(Dao injectedDao, AuditDao injectedAudit) {
        this.dao = injectedDao;
        this.audit = injectedAudit;
    }

    @Override
    public Collection<Item> getItems() throws VendingMachinePersistenceError, GetEntryError {
        Collection<Item> items = dao.getItems();
        if (items.isEmpty()) {
            throw new VendingMachinePersistenceError("There are no items to vend");
        }
        return items;
    }

    @Override
    public BigDecimal processTransaction(BigDecimal money, String selection) throws InsufficientFundsError, OutOfStockException, VendingMachinePersistenceError, GetEntryError {
        Item item = dao.getItem(selection);
        int inventoryCount = item.getInventoryCount();
        BigDecimal change;
        
        if (item.getInventoryCount() < 1) {
            throw new OutOfStockException("Out of stock");
        } else if (money.compareTo(item.getPrice()) < 0) {
            throw new InsufficientFundsError("Insufficient funds");
        } else {
            change = money.subtract(item.getPrice());
            dao.updateItem(selection);
        }
        return change;
    }

    @Override
    public ChangeMaker makeChange(BigDecimal change) {
        ChangeMaker changeOwed = new ChangeMaker(change);
        return changeOwed;
    }

    @Override
    public void auditFile(String report) throws VendingMachinePersistenceError {
        //audit.writeAuditEntry(selection, report);
    }

    @Override
    public Item getItem(String selection)  throws GetEntryError{
        
        Item item = null;
        try {
            item = dao.getItem(selection);
        } catch (NullPointerException e) {
            throw new GetEntryError("Please enter the item as it appears in the display.");
        }
        return item;
    }

    @Override
    public BigDecimal checkMoney(String cash) throws GettingMoneyError {
        BigDecimal money;

        try {
            money = new BigDecimal(cash);
        } catch (IllegalArgumentException e) {
            throw new GettingMoneyError("Please enter a real number without any letters or commas", e);
        }

        if (money.compareTo(BigDecimal.ZERO) < 1) {
            throw new GettingMoneyError("Try that again and I will call the police and send them a photo of you...");
        }
        if (money.compareTo(new BigDecimal("2125000000")) > 0) {
            throw new GettingMoneyError("I see you big baller... That's so such money we don't know what to do with it. Please enter less than 2,125,000,000");
        }
        return money;
    }
}
