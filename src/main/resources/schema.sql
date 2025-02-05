CREATE DATABASE IF NOT EXISTS Istore;
USE Istore;

CREATE TABLE IF NOT EXISTS users (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     email VARCHAR(255) NOT NULL UNIQUE,
                                     password VARCHAR(255) NOT NULL,
                                     pseudo VARCHAR(100) NOT NULL,
                                     role ENUM('superadmin','admin', 'employee') NOT NULL DEFAULT 'employee'
);

CREATE TABLE IF NOT EXISTS whitelist (
                                         id INT AUTO_INCREMENT PRIMARY KEY,
                                         email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS stores (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS employees_stores (
                                                id INT AUTO_INCREMENT PRIMARY KEY,
                                                employee_id INT NOT NULL,
                                                store_id INT NOT NULL,
                                                FOREIGN KEY (employee_id) REFERENCES users(id),
                                                FOREIGN KEY (store_id) REFERENCES stores(id)
);

CREATE TABLE IF NOT EXISTS inventories (
                                           id INT AUTO_INCREMENT PRIMARY KEY,
                                           store_id INT NOT NULL,
                                           FOREIGN KEY (store_id) REFERENCES stores(id)
);

CREATE TABLE IF NOT EXISTS items (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(100) NOT NULL,
                                     price DECIMAL(10, 2) NOT NULL,
                                     stock INT NOT NULL DEFAULT 0,
                                     inventory_id INT NOT NULL,
                                     FOREIGN KEY (inventory_id) REFERENCES inventories(id)
);