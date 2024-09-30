/* creating table queries */
# CREATE TABLE Facilities
# (
#     facility_id   INT          NOT NULL AUTO_INCREMENT,
#     facility_name VARCHAR(255) NOT NULL,
#     school        VARCHAR(255) NOT NULL,
#     PRIMARY KEY (facility_id)
# );
#
# CREATE TABLE Users
# (
#     user_id VARCHAR(31)  NOT NULL,
#     name    VARCHAR(20)  NOT NULL,
#     phone   CHAR(11)     NOT NULL,
#     email   VARCHAR(255) NOT NULL,
#     school    VARCHAR(20)  NOT NULL,
#     position    VARCHAR(20)  NOT NULL,
#     major    VARCHAR(20)  NOT NULL,
#     PRIMARY KEY (user_id)
# );

CREATE TABLE Rentals
(
    rental_id     INT          NOT NULL AUTO_INCREMENT,
    start         DATETIME     NOT NULL,
    end           DATETIME     NOT NULL,
    user_id       VARCHAR(255) NOT NULL,
    user_name     VARCHAR(255) NOT NULL,
    facility_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (rental_id)
);
