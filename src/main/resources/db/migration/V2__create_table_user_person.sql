CREATE TABLE IF NOT EXISTS user_person (
    id serial PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone_number VARCHAR(15),
    birth_date DATE,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    created_date DATE DEFAULT CURRENT_DATE,
    type_user VARCHAR(20) NOT NULL
);
