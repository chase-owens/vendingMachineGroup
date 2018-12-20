/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author chaseowens
 */
public class DaoStubImpl implements Dao{
    private final HashMap<String, Item> items = new HashMap<>();
    private final BigDecimal bd = BigDecimal.TEN;
    private final Item onlyItem;
    
    public DaoStubImpl() {
        this.onlyItem = new Item("dummy", bd, 1);
        items.put(onlyItem.getName(), onlyItem);
    }
    
    @Override
    public Collection getItems() {
        Collection<Item> currentInventory = items.values();
        if (currentInventory.size() == 1) {
            return currentInventory;
        } else {
            return null;
        }
        
    }

    @Override
    public void getInventory() throws VendingMachinePersistenceError {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateInventory(HashMap<String, Item> currentItems) throws VendingMachinePersistenceError {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Item getItem(String selection) throws GetEntryError {
        try {
            items.get(selection).equals(onlyItem);
        } catch (NullPointerException e) {
            throw new GetEntryError("Please enter the item as it appears in the display.");
        }
        
        if (items.get(selection).equals(onlyItem)) {
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public void updateItem(String selection) throws GetEntryError, VendingMachinePersistenceError {
        this.onlyItem.setInventoryCount(this.onlyItem.getInventoryCount() - 1);
    }
}
