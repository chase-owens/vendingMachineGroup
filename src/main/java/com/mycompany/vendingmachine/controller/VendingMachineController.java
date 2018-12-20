/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.controller;

import com.mycompany.vendingmachine.dao.GetEntryError;
import com.mycompany.vendingmachine.dao.GettingMoneyError;
import com.mycompany.vendingmachine.dao.InsufficientFundsError;
import com.mycompany.vendingmachine.dao.OutOfStockException;
import com.mycompany.vendingmachine.dao.VendingMachinePersistenceError;
import com.mycompany.vendingmachine.dto.ChangeMaker;
import com.mycompany.vendingmachine.dto.Item;
import com.mycompany.vendingmachine.service.VMService;
import com.mycompany.vendingmachine.view.View;
import java.math.BigDecimal;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author chaseowens
 */
@Controller
public class VendingMachineController {

    @Autowired
    View view;
    @Autowired
    VMService service;
    boolean keepGoing = true;
    BigDecimal moneyEntered, change;
    String selection;

    public VendingMachineController(View view, VMService service) {
        this.view = view;
        this.service = service;
    }

    public void run() throws InsufficientFundsError, OutOfStockException, VendingMachinePersistenceError, GettingMoneyError, GetEntryError {

        while (keepGoing) {
            try {
                while (keepGoing) {
                    displayItems();
                    getMoney();
                    getSelection();
                    processTransaction();
                    checkIfMakeAnotherTransaction();
                }
                exitGracefully();

            } catch (InsufficientFundsError | OutOfStockException | VendingMachinePersistenceError | GettingMoneyError | GetEntryError e) {
                handleError(e);
            }
        }

    }

    private void displayItems() throws VendingMachinePersistenceError, GetEntryError {
        // Get all items to vend
        Collection<Item> items = service.getItems();

        // Display items
        view.displayItems(items);
    }

    private void getMoney() throws GettingMoneyError, VendingMachinePersistenceError, GetEntryError {

        boolean validCash = false;
        BigDecimal cashEntered = null;
        String cash = null;
        // Ask user to enter money

        while (!validCash) {
            try {
                cash = view.getMoney();
                cashEntered = service.checkMoney(cash);
                validCash = true;
            } catch (GettingMoneyError e) {
                handleError(e);
            }
        }

        view.displayMoneyEntered(cash);
        moneyEntered = cashEntered;
    }

    private void getSelection() throws InsufficientFundsError, OutOfStockException, VendingMachinePersistenceError, GetEntryError {
        boolean validSelection = false;
        do {
            try {
                selection = view.selectItem();
                change = processTransaction(moneyEntered, selection);
                validSelection = true;
            } catch (GetEntryError e) {
                handleError(e);
            }
        } while (!validSelection);
    }

    private BigDecimal processTransaction(BigDecimal money, String selection) throws InsufficientFundsError, OutOfStockException, VendingMachinePersistenceError, GetEntryError {
        // Process Transaction, Update Inventory

        BigDecimal changeMade = service.processTransaction(money, selection);
        Item item = null;

        try {
            item = service.getItem(selection);
        } catch (GetEntryError e) {
            handleError(e);
        }

        service.auditFile(item.toString() + " : " + "successful");

        return changeMade;
    }

    private void exitGracefully() {
        view.exitGracefully();
    }

    private void processTransaction() {
        view.displayChangeOwed(change);

        ChangeMaker changeOwed = service.makeChange(change);

        view.giveChange(changeOwed);

        moneyEntered = BigDecimal.ZERO;
    }

    private void giveRefund() {
        view.giveRefund(moneyEntered);

        ChangeMaker changeOwed = service.makeChange(moneyEntered);

        view.giveChange(changeOwed);

        moneyEntered = BigDecimal.ZERO;
    }

    private void checkIfMakeAnotherTransaction() {
        String decision;
        boolean validEntry = false;
        while (!validEntry) {

            // Make another transaction?
            decision = view.checkIfMakeAnotherTransaction();

            // Check answer for no
            if (decision.startsWith("n")) {
                keepGoing = false;
                validEntry = true;
            } else if (decision.startsWith("y")) {
                validEntry = true;
            } else {
                view.explainKeepGoingRules();
            }
        }

    }

    private void handleError(Exception e) throws VendingMachinePersistenceError, GetEntryError {
        view.displayErrorMessage(e);
        if (e.getMessage().equals("Insufficient funds") || e.getMessage().equals("Out of stock")) {
            Item item = service.getItem(selection);
            service.auditFile(item.toString() + " : " + e.getMessage());
            giveRefund();
        }
    }

}
