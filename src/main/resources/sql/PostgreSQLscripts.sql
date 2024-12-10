CREATE EXTENSION IF NOT EXISTS citext;

SELECT * FROM pg_available_extensions WHERE name = 'citext';
CREATE TABLE users (
                       username CITEXT NOT NULL PRIMARY KEY,
                       password CITEXT NOT NULL,
                       enabled BOOLEAN NOT NULL
);

CREATE TABLE authorities (
                             username CITEXT NOT NULL,
                             authority CITEXT NOT NULL,
                             CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users (username)
);

CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);

-- our own user table
create table customer (id SERIAL PRIMARY KEY,
                       email VARCHAR NOT NULL,
                       pwd VARCHAR NOT NULL,
                       role VARCHAR NOT NULL);

insert into customer (email,pwd,role) values ('happy@gmail.com','{noop}App@Test123!','read');
insert into customer (email,pwd,role) values ('admin@gmail.com','{bcrypt}admin@Test123!','admin');