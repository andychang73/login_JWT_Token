  create table if not exists user(
	id INTEGER AUTO_INCREMENT,
    username VARCHAR(20) NOT NULL,
    password VARCHAR(50) NOT NULL,
    roles VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);