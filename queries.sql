/* creating table queries */
CREATE TABLE Facilities
(
    facility_id   INT          NOT NULL AUTO_INCREMENT,
    facility_name VARCHAR(255) NOT NULL,
    school        VARCHAR(255) NOT NULL,
    PRIMARY KEY (facility_id)
);

CREATE TABLE Users
(
    user_id VARCHAR(31)  NOT NULL,
    name    VARCHAR(20)  NOT NULL,
    phone   CHAR(11)     NOT NULL,
    email   VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE Rentals
(
    rental_id INT         NOT NULL AUTO_INCREMENT,
    facility  INT         NOT NULL,
    starttime DATETIME    NOT NULL,
    endtime   DATETIME    NOT NULL,
    user_id   VARCHAR(31) NOT NULL,
    PRIMARY KEY (rental_id),
    FOREIGN KEY (facility) REFERENCES Facilities (facility_id),
    FOREIGN KEY (user_id) REFERENCES Users (user_id)
);

/* Rentals API */
/* Find by Person */
SELECT r.rental_id, r.facility, r.starttime, r.endtime, r.user_id,
       u.name AS user_name, f.facility_name
FROM Rentals r JOIN Users u ON r.user_id = u.user_id
    JOIN Facilities f ON r.facility = f.facility_id
WHERE r.user_id = ?
    AND r.starttime <= ?
    AND r.endtime >= ?;

/* Find by Facility */
SELECT r.rental_id,
       r.facility,
       r.starttime,
       r.endtime,
       r.user_id,
       u.name AS user_name,
       f.facility_name
FROM Rentals r
         JOIN Users u ON r.user_id = u.user_id
         JOIN Facilities f ON r.facility = f.facility_id
WHERE r.facility = 'facility_id'
  AND r.starttime <= 'endtime_value'
  AND r.endtime >= 'starttime_value';

/* Create Rental */
INSERT INTO Rentals (rental_id, facility, starttime, endtime, user_id) VALUES (?, ?, ?, ?, ?);