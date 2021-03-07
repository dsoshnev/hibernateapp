DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), cost numeric(6, 2));
INSERT INTO products (title, cost) VALUES
('Product1', 10.10),
('Product2', 20.20),
('Product3', 30.30),
('Product4', 40.40),
('Product5', 50.50),
('Product6', 60.60);