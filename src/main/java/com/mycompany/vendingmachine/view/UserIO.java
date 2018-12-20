/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.view;

import com.mycompany.vendingmachine.dao.DataPersistenceError;
import com.mycompany.vendingmachine.dao.GettingMoneyError;
import java.math.BigDecimal;

/**
 *
 * @author chaseowens
 */
public interface UserIO {
    void print(String message);

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    int readInt(String prompt) throws DataPersistenceError;

    int readInt(String prompt, int min, int max) throws DataPersistenceError;

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);

    String readString(String prompt);
    
    BigDecimal readBigDecimal(String prompt) throws GettingMoneyError;
}
