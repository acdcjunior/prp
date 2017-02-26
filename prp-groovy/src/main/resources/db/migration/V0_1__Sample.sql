CREATE TABLE PERSON (
    ID         BIGINT       NOT NULL AUTO_INCREMENT,
    FIRST_NAME VARCHAR(255) NOT NULL,
    LAST_NAME  VARCHAR(255) NOT NULL,
    VERSION    BIGINT       NULL
);

INSERT INTO PERSON (FIRST_NAME, LAST_NAME) VALUES ('Australia', 'Brisbane');
INSERT INTO PERSON (FIRST_NAME, LAST_NAME) VALUES ('Australia', 'Melbourne');
INSERT INTO PERSON (FIRST_NAME, LAST_NAME) VALUES ('Australia', 'Sydney');
INSERT INTO PERSON (FIRST_NAME, LAST_NAME) VALUES ('Canada', 'Montreal');
INSERT INTO PERSON (FIRST_NAME, LAST_NAME) VALUES ('Israel', 'Tel Aviv');
INSERT INTO PERSON (FIRST_NAME, LAST_NAME) VALUES ('Japan', 'Tokyo');
INSERT INTO PERSON (FIRST_NAME, LAST_NAME) VALUES ('Spain', 'Barcelona');
INSERT INTO PERSON (FIRST_NAME, LAST_NAME) VALUES ('Switzerland', 'Neuchatel');
INSERT INTO PERSON (FIRST_NAME, LAST_NAME) VALUES ('UK', 'Bath');