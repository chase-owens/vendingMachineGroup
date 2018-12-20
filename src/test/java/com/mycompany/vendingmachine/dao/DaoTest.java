/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.TestApplicationConfiguration;
import com.mycompany.vendingmachine.dto.Item;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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
public class DaoTest {
    
    @Autowired
    Dao dao;
    //  = new DaoImpl()

//    public DaoTest() {
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        dao = ctx.getBean("daoImpl", DaoImpl.class);
//    }

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
     * Test of getItems method, of class Dao.
     *
     * @throws com.mycompany.vendingmachine.dao.VendingMachinePersistenceError
     */
    @Test
    public void testGetItems() throws VendingMachinePersistenceError, GetEntryError {
        int inventorySize = 2;
        assertEquals(inventorySize, dao.getItems().size());
    }

    @Test
    public void getInventory() throws VendingMachinePersistenceError {
        //Does nothing
    }

    @Test
    public void testGetItem() throws VendingMachinePersistenceError, GetEntryError {

        // test is an exact replica of Item with name Trip to Mars in inventory
        Item test = new Item("Trip to Mars", new BigDecimal(150000), 0);
        dao.getInventory();
        assertEquals(test, dao.getItem(test.getName()));
    }
    @Test
    public void updateInventoryAndUpdateItem() throws VendingMachinePersistenceError, InsufficientFundsError, OutOfStockException, GetEntryError {

        // Get a number of unicorn inventoryCount form database
        dao.getInventory();
        Item uBefore = dao.getItem("Unicorn");
        int uCountBefore = uBefore.getInventoryCount();

        // Call updateInventory through
        dao.updateItem("Unicorn");

        // get another number of unicorn inventoryCount form inventory after updateing inventory
        Item uAfter = dao.getItem("Unicorn");
        int uCountAfter = uAfter.getInventoryCount();

        // the test 
        assertNotEquals(uCountBefore, uCountAfter);

    }

    

}
