
import com.mycompany.vendingmachine.controller.VendingMachineController;
import com.mycompany.vendingmachine.dao.GetEntryError;
import com.mycompany.vendingmachine.dao.GettingMoneyError;
import com.mycompany.vendingmachine.dao.InsufficientFundsError;
import com.mycompany.vendingmachine.dao.OutOfStockException;
import com.mycompany.vendingmachine.dao.VendingMachinePersistenceError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chaseowens
 */
@ComponentScan("com.mycompany.vendingmachine")
@SpringBootApplication
public class App implements CommandLineRunner {
    @Autowired
    private VendingMachineController controller;
        
    public static void main(String[] args) throws InsufficientFundsError, OutOfStockException, VendingMachinePersistenceError, GettingMoneyError, GetEntryError {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {  
        controller.run();
    }

}
