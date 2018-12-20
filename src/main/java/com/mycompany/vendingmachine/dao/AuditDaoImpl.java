/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.Item;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

/**
 *
 * @author chaseowens
 */
@Component
public class AuditDaoImpl implements AuditDao {
    public static final String AUDIT_FILE = "audit.txt";
    PrintWriter write;
    
    @Override
    public void writeAuditEntry(Item item, String report) throws VendingMachinePersistenceError {
        
        
        try {
            write = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new VendingMachinePersistenceError("Could not find audit file", e);
        }
        
        LocalDateTime datestamp = LocalDateTime.now();
        LocalTime timestamp = LocalTime.now();
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("hh:mm");
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        write.println(datestamp.format(formatter) + " : " + timestamp.format(tf) + " : " + item.getName() + " : " + item.getInventoryCount() + " : " + report);
        write.flush();
        write.close();
    }
    
    @Override
    public void writeAuditEntryWithString(String report) throws VendingMachinePersistenceError {
        
        
        try {
            write = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new VendingMachinePersistenceError("Could not find audit file", e);
        }
        
        LocalDateTime datestamp = LocalDateTime.now();
        LocalTime timestamp = LocalTime.now();
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("hh:mm");
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        write.println(datestamp.format(formatter) + " : " + timestamp.format(tf) + " : " + report);
        write.flush();
        write.close();
    }
}
