/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.Item;
import com.mycompany.vendingmachine.dto.ItemMapper;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chaseowens
 */
@Repository
public class VendingMachineDaoImplSql implements Dao {

    @Autowired
    private JdbcTemplate jdbc;

    public VendingMachineDaoImplSql() {

    }

    @Override
    public void getInventory() throws VendingMachinePersistenceError {
        // Does nothing
    }

    @Override
    public void updateInventory(HashMap<String, Item> currentItems) throws VendingMachinePersistenceError {
        // Does nothing
    }

    @Override
    public Collection<Item> getItems() throws VendingMachinePersistenceError, GetEntryError {
        List<Item> itemsL;
        
        try {
            itemsL = jdbc.query("SELECT * FROM VendingMachine WHERE inventoryCount > 0", new ItemMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new GetEntryError("Please enter the item as it appears in the display.");
        }
        
        Collection<Item> itemsC = itemsL;
        return itemsC;
    }

    @Override
    public Item getItem(String selection) throws GetEntryError {
        Item item;
        try {
            item = jdbc.queryForObject("SELECT * FROM VendingMachine Where title = ?", new ItemMapper(), selection);
        } catch (EmptyResultDataAccessException e) {
            throw new GetEntryError("Please enter the item as it appears in the display.");
        }
        return item;
    }

    @Override
    public void updateItem(String selection) throws GetEntryError, VendingMachinePersistenceError {
        Item item = getItem(selection);
        int newCount = item.getInventoryCount() - 1;
        jdbc.update("UPDATE VendingMachine SET inventoryCount = ? WHERE title = ?", newCount, selection);
    }

}
