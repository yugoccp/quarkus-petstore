DROP TABLE IF EXISTS pets;

CREATE TABLE pets (
                          id bigint auto_increment,
                          name varchar(255),
                          age numeric,
                          type varchar(255),
                          gender varchar(255),
                          primary key (id)
);

INSERT INTO pets (name, age, type, gender) VALUES ('Stella', 10, 'dog', 'female');
INSERT INTO pets (name, age, type, gender) VALUES ('Thor', 6, 'dog', 'male');
INSERT INTO pets (name, age, type, gender) VALUES ('Smeagol', 4, 'cat', 'female');