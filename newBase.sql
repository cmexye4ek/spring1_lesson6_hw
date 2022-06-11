BEGIN;

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id serial PRIMARY KEY, title VARCHAR(255), cost numeric(6, 2));
INSERT INTO products (title, cost) VALUES
('pen', 2),
('apple', 10),
('book', 100),
('cat', 500),
('satans favorite scarf', 666);

DROP TABLE IF EXISTS consumers CASCADE;
CREATE TABLE consumers (id serial PRIMARY KEY, name VARCHAR(255));
INSERT INTO consumers (name) VALUES
('Ivan'),
('Petr'),
('Andrey');

DROP TABLE IF EXISTS orders CASCADE;
CREATE TABLE orders (id serial PRIMARY KEY, consumer_id integer REFERENCES consumers (id), product_id integer REFERENCES products (id), costOnOrderDate integer);
INSERT INTO orders (consumer_id, product_id, costOnOrderDate) VALUES
(1, 1, 2),
(1, 3, 100),
(1, 5, 666),
(2, 4, 500),
(2, 2, 10),
(3, 2, 10),
(3, 1, 2),
(3, 3, 100),
(3, 4, 500);

COMMIT;