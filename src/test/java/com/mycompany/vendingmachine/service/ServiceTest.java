/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.dao.GettingMoneyError;
import com.mycompany.vendingmachine.TestApplicationConfiguration;
import com.mycompany.vendingmachine.dao.GetEntryError;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author chaseowens
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class ServiceTest {

    @Autowired
    VMService service;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getItems method, of class Service.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetItems() throws Exception {
        assertEquals(2, service.getItems().size());
    }

    /**
     * Test of processTransaction method, of class Service.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testProcessTransaction() throws Exception {
        assertEquals(BigDecimal.ZERO, service.processTransaction(new BigDecimal("75000"), "Unicorn"));
    }

    /**
     * Test of makeChange method, of class Service.
     */
    @Test
    public void testMakeChange() {
        assertEquals(10, service.makeChange(BigDecimal.TEN).getOne());
    }

    @Test
    public void testGetItem() throws GetEntryError {
        assertEquals("Unicorn", service.getItem("Unicorn").getName());
    }

    @Test
    public void testCheckMoney() throws GettingMoneyError {
        try {
            service.checkMoney("-1");
            fail("Try that again and I will call the police and send them a photo of you...");
        } catch (GettingMoneyError e) {
        }
        
        try {
            service.checkMoney("2125000001");
            fail("I see you big baller... That's so such money we don't know what to do with it. Please enter less than 2,125,000,000");
        } catch (GettingMoneyError e) {
        }
        
        assertNotNull(service.checkMoney("346457"));

    }

}
