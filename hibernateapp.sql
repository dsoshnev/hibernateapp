DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), cost numeric(6, 2));
INSERT INTO products (title, cost) VALUES
('Product1', 10),
('Product2', 20),
('Product3', 30);