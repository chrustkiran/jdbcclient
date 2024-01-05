DROP TABLE IF EXISTS t_user;

CREATE TABLE t_user (
    id VARCHAR(50) NOT NULL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50)
);

INSERT INTO t_user VALUES (1, 'chris', 'sathiya');