DROP DATABASE IF EXISTS TestVendingMachineDB;

CREATE DATABASE IF NOT EXISTS TestVendingMachineDB;

USE TestVendingMachineDB;

CREATE TABLE VendingMachine(
	title varChar(30),
    price decimal,
    inventoryCount int
);

INSERT INTO VendingMachine( title, price, inventoryCount) VALUES 
	("Trip to Mars", 150000, 0),
    ("Unicorn", 75000, 50);