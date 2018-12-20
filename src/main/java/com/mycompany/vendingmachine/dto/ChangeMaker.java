/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author chaseowens
 */
public final class ChangeMaker {
    int one = 0, quarter = 0, dime = 0, nickel = 0, penny = 0;
    
    public ChangeMaker(BigDecimal change) {
        makeChange(change);
    }
    
    private void makeChange(BigDecimal change) {
        
        BigDecimal bDollar = BigDecimal.ONE, bQuarter = new BigDecimal(".25"), bDime = new BigDecimal(".10"), bNickel = new BigDecimal(".05"), bPenny = new BigDecimal(".01");
        BigDecimal changeTotal = BigDecimal.ZERO;
        
        
        BigDecimal[] getDollars = change.divideAndRemainder(bDollar);
        BigDecimal[] getQuarters = getDollars[1].divideAndRemainder(bQuarter);
        BigDecimal[] getDimes = getQuarters[1].divideAndRemainder(bDime);
        BigDecimal[] getNickels = getDimes[1].divideAndRemainder(bNickel);
        BigDecimal[] getPennies = getNickels[1].divideAndRemainder(bPenny);
        
        this.one = getDollars[0].intValueExact();
        this.quarter = getQuarters[0].intValueExact();
        this.dime = getDimes[0].intValueExact();
        this.nickel = getNickels[0].intValueExact();
        this.penny = getPennies[0].intValueExact();
        
        if (getPennies[1].compareTo(new BigDecimal(".005")) >= 0) {
            this.penny += 1;
        }
        
        if (( (quarter * 0.25) + (dime * 0.10) + (nickel * 0.05) + (penny * 0.01) ) == 1) {
            this.one += 1;
            this.quarter = 0;
            this.dime = 0;
            this.nickel = 0;
            this.penny = 0;
        }
        
        
        
    }

    public int getOne() {
        return one;
    }

    public int getQuarter() {
        return quarter;
    }

    public int getDime() {
        return dime;
    }

    public int getNickel() {
        return nickel;
    }

    public int getPenny() {
        return penny;
    }
    
}
