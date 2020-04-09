INSERT into limitation (type, min_age, require_no_criminal, require_no_illness)
values ('alcohol', 18, 0, 0),
       ('handgun', 21, 1, 1),
       ('shotgun', 18, 1, 1);

INSERT INTO users (id, date_of_birth, mental_illness, criminal)
VALUES ('Pit', '1998-04-07', 0, 0);
INSERT INTO users (id, date_of_birth, mental_illness, criminal)
VALUES ('Harry', '2000-04-07', 0, 0);
INSERT INTO users (id, date_of_birth, mental_illness, criminal)
VALUES ('Tim', '2004-04-07', 0, 0);
INSERT INTO users (id, date_of_birth, mental_illness, criminal)
VALUES ('John', '1984-11-27', 1, 0);