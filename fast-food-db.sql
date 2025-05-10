-- Fast Food Java - Schema Setup (Simplified)

CREATE DATABASE IF NOT EXISTS fast_food_java;
USE fast_food_java;

-- Table: accounts
CREATE TABLE IF NOT EXISTS accounts (
                                        id INT NOT NULL AUTO_INCREMENT,
                                        username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    fullName VARCHAR(100) NOT NULL,
    phoneNumber VARCHAR(15),
    role ENUM('ADMIN', 'STAFF') NOT NULL,
    createdAt TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
    );

-- Table: foods
CREATE TABLE IF NOT EXISTS foods (
                                     id INT NOT NULL AUTO_INCREMENT,
                                     name VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    quantity INT DEFAULT 1,
    createdAt TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
    );

-- Table: orders
CREATE TABLE IF NOT EXISTS orders (
                                      id INT NOT NULL AUTO_INCREMENT,
                                      staffId INT NOT NULL,
                                      status ENUM('PENDING','PAID','CANCEL') DEFAULT 'PENDING',
    createdAt TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (staffId) REFERENCES accounts(id)
    );

-- Table: orderItems
CREATE TABLE IF NOT EXISTS orderItems (
                                          id INT NOT NULL AUTO_INCREMENT,
                                          orderId INT NOT NULL,
                                          foodId INT NOT NULL,
                                          quantity INT NOT NULL DEFAULT 1,
                                          unitPrice DECIMAL(10,2) NOT NULL,
    createdAt TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (orderId) REFERENCES orders(id),
    FOREIGN KEY (foodId) REFERENCES foods(id)
    );
ALTER TABLE orders
DROP FOREIGN KEY orders_ibfk_1,
ADD CONSTRAINT fk_orders_staffId
    FOREIGN KEY (staffId) REFERENCES accounts(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE orderItems
DROP FOREIGN KEY orderItems_ibfk_1,
DROP FOREIGN KEY orderItems_ibfk_2,
ADD CONSTRAINT fk_orderItems_orderId
    FOREIGN KEY (orderId) REFERENCES orders(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
                                   ADD CONSTRAINT fk_orderItems_foodId
                                   FOREIGN KEY (foodId) REFERENCES foods(id)
       ON DELETE CASCADE
    ON UPDATE CASCADE;