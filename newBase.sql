BEGIN;

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), cost numeric(6, 2));
INSERT INTO products (title, cost) VALUES
('pen', 2),
('apple', 10),
('book', 100),
('cat', 500),
('satans favorite scarf', 666);

COMMIT;