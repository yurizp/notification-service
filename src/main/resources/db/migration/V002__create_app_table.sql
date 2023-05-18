CREATE TABLE app (
                     id bigint(20) NOT NULL AUTO_INCREMENT,
                     name varchar(100) NOT NULL,
                     token varchar(100) NOT NULL UNIQUE,
                     user_id bigint(100) NOT NULL,
                     PRIMARY KEY (id),
                     FOREIGN KEY (user_id) REFERENCES user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

