CREATE TABLE car (
                     id serial PRIMARY KEY,
                     name VARCHAR(255) NOT NULL,
                     price DECIMAL(10, 2) NOT NULL CHECK (price > 0),
                     year INT NOT NULL CHECK (year >= 1886),
    city VARCHAR(255) NOT NULL,
    placa VARCHAR(12) NOT NULL UNIQUE,
    company VARCHAR(255) NOT NULL
);
