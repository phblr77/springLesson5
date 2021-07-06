BEGIN;

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products
(
    id    bigserial PRIMARY KEY,
    title varchar(255),
    price int
);
INSERT INTO products (title, price)
VALUES ('product 1', 100);
INSERT INTO products (title, price)
VALUES ('product 2', 100);
INSERT INTO products (title, price)
VALUES ('product 3', 100);
INSERT INTO products (title, price)
VALUES ('product 4', 100);
INSERT INTO products (title, price)
VALUES ('product 5', 100);
INSERT INTO products (title, price)
VALUES ('product 6', 100);
INSERT INTO products (title, price)
VALUES ('product 7', 100);
INSERT INTO products (title, price)
VALUES ('product 8', 100);
INSERT INTO products (title, price)
VALUES ('product 9', 100);
INSERT INTO products (title, price)
VALUES ('product 10', 100);

COMMIT;