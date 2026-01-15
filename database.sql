
CREATE DATABASE library_db;

-
\c library_db;


CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    author VARCHAR(100) NOT NULL,
    isbn VARCHAR(20),
    publication_year INT,
    genre VARCHAR(50)
);


CREATE TABLE emprunts (
    id SERIAL PRIMARY KEY,
    membre_id INT NOT NULL,
    livre_id INT NOT NULL,
    date_emprunt DATE NOT NULL,
    date_retour DATE
);
