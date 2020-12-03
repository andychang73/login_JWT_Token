create table if not exists user(
	id integer NOT NULL,
    name VARCHAR(20) NOT NULL,
    password VARCHAR(50) NOT NULL,
    roles VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);