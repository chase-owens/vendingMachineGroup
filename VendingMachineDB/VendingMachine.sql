DROP DATABASE IF EXISTS VendingMachineDB;

CREATE DATABASE IF NOT EXISTS VendingMachineDB;

USE VendingMachineDB;

CREATE TABLE VendingMachine(
	title varChar(30),
    price decimal,
    inventoryCount int
);

INSERT INTO VendingMachine( `name`, price, inventoryCount) VALUES 
	("A Wish", 200000, 3),
    ("Trip to the Past", 50000, 8),
    ("Voodoo Doll", 50000, 19),
    ("Trip to Mars", 150000, 0),
    ("Unicorn", 75000, 50),
    ("A Dream", 100000, 8),
    ("The Meaning of Life", 125000, 9),
    ("Trip to the Future", 125000, 9),
    ("Ride on a Rainbow", 50000, 90);
