/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author chaseowens
 */
public class DaoImpl implements Dao {

    HashMap<String, Item> items = new HashMap<>();
    PrintWriter write;
    public static final String ITEM_INVENTORY = "inventory.txt";
    public static final String DELIMETER = "::";

    @Override
    public void updateInventory(HashMap<String, Item> currentItems) throws VendingMachinePersistenceError {
        try {
            write = new PrintWriter(new FileWriter(ITEM_INVENTORY));
        } catch (IOException e) {
            throw new VendingMachinePersistenceError("The inventory could not be updated, please contact manager.", e);
        }


        Collection<Item> itemsOnList = items.values();
        itemsOnList.stream().forEach(item -> {
            write.println(
                    item.getName()+ DELIMETER
                    + item.getPrice().toString() + DELIMETER
                    + item.getInventoryCount()
            );
            write.flush();
        });

        write.close();
    }
    
    @Override
    public void updateItem(String selection) throws GetEntryError, VendingMachinePersistenceError {
        Item item= getItem(selection);
        item.setInventoryCount(item.getInventoryCount() - 1);
        updateInventory(items);
    }

    @Override
    public void getInventory() throws VendingMachinePersistenceError {
        Scanner read;

        try {
            read = new Scanner(new BufferedReader(new FileReader(ITEM_INVENTORY)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceError("Inventory Error", e);
        }

        while (read.hasNextLine()) {
            String currentLine = read.nextLine();
            String currentTokens[];

            currentTokens = currentLine.split(DELIMETER);

            String name = currentTokens[0];
            BigDecimal price = new BigDecimal(currentTokens[1]);
            int inventoryCount = Integer.parseInt(currentTokens[2]);

            Item item = new Item(name, price, inventoryCount);
            items.put(name, item);
        }
        read.close();
    }

    @Override
    public Collection<Item> getItems() throws VendingMachinePersistenceError {
        items.clear();
        getInventory();
        Collection<Item> currentInventory = items.values();
        return currentInventory;
    }

    @Override
    public Item getItem(String selection) throws GetEntryError {
        Item item = items.get(selection);
        return item;
    }

}
