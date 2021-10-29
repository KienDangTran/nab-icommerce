DROP TABLE IF EXISTS PUBLIC.PRODUCT;
CREATE TABLE IF NOT EXISTS PUBLIC.PRODUCT (
    id       LONG AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(250) NOT NULL,
    price    NUMERIC(30, 2) DEFAULT 0,
    branch   VARCHAR(250),
    colour   VARCHAR(50),
    quantity INT            DEFAULT 0
);