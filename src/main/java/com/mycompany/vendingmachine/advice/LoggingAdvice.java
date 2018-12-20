/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.advice;

import com.mycompany.vendingmachine.dao.AuditDao;
import com.mycompany.vendingmachine.dao.VendingMachinePersistenceError;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author chaseowens
 */
public class LoggingAdvice {

    AuditDao auditDao;

    public LoggingAdvice(AuditDao auditDao) {
        this.auditDao = auditDao;
    }

    public void createAuditEntry(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = " ";
        for (Object arg : args) {
            auditEntry += arg;

        }

        try {
            auditDao.writeAuditEntryWithString(auditEntry);
        } catch (VendingMachinePersistenceError e) {
            System.err.println(e.getMessage());
        }
    }
}
