CREATE TABLE taco_order
(
    id SERIAL PRIMARY KEY,
    delivery_name VARCHAR(50) NOT NULL,
    delivery_street VARCHAR(50) NOT NULL,
    delivery_city VARCHAR(50) NOT NULL,
    delivery_state VARCHAR(2) NOT NULL,
    delivery_zip VARCHAR(10) NOT NULL,
    cc_number VARCHAR(16) NOT NULL,
    cc_expiration VARCHAR(5) NOT NULL,
    cc_cvv VARCHAR(3) NOT NULL,
    placed_at TIMESTAMP NOT NULL
);

CREATE TABLE taco
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    taco_order_id BIGINT NOT NULL REFERENCES taco_order(id),
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE ingredient
(
    id VARCHAR(4) PRIMARY KEY,
    name VARCHAR(25) NOT NULL,
    type VARCHAR(10) NOT NULL
);