CREATE TABLE user (
                      id bigint(20) NOT NULL AUTO_INCREMENT,
                      email varchar(100) NOT NULL UNIQUE ,
                      password varchar(100) NOT NULL,
                      name varchar(100) NOT NULL,
                      phone_number varchar(100),
                      company_name varchar(100),
                      company_address varchar(100),
                      PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;