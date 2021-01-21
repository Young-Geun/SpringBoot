CREATE TABLE guestbook (
    id number(10,0) NOT NULL,
    message varchar2(255) NOT NULL,
    name varchar2(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE SEQUENCE id_seq START WITH 1 INCREMENT BY 50;