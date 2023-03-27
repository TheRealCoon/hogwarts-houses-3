DROP TABLE IF EXISTS building CASCADE;
DROP SEQUENCE IF EXISTS building_id_seq;
CREATE TABLE building
(
    id              long identity NOT NULL PRIMARY KEY,
    number_of_rooms integer
);

DROP TABLE IF EXISTS picture CASCADE;
DROP SEQUENCE IF EXISTS picture_id_seq;
CREATE TABLE picture
(
    id          long identity NOT NULL PRIMARY KEY,
    name        varchar,
    building_id long
);

DROP TABLE IF EXISTS room CASCADE;
DROP SEQUENCE IF EXISTS room_id_seq;
CREATE TABLE room
(
    id             long identity NOT NULL PRIMARY KEY,
    room_number    integer       NOT NULL,
    capacity       integer       NOT NULL,
    number_of_beds integer       NOT NULL,
    has_empty_beds boolean,
    building_id    long          NOT NULL
);

DROP TABLE IF EXISTS student CASCADE;
DROP SEQUENCE IF EXISTS student_id_seq;
CREATE TABLE student
(
    id         long identity PRIMARY KEY                                 NOT NULL,
    name       varchar,
    house_type ENUM ('GRYFFINDOR', 'HUFFLEPUFF','RAVENCLAW','SLYTHERIN') NOT NULL,
    pet_type   ENUM ('CAT','RAT','OWL','NONE')
);


DROP TABLE IF EXISTS resident CASCADE;
DROP SEQUENCE IF EXISTS resident_id_seq;
CREATE TABLE resident
(
    id         long identity NOT NULL PRIMARY KEY,
    student_id long,
    room_id    long
);

DROP TABLE IF EXISTS recipe CASCADE;
DROP SEQUENCE IF EXISTS recipe_id_seq;
CREATE TABLE recipe
(
    id   long identity PRIMARY KEY NOT NULL,
    name varchar
);

DROP TABLE IF EXISTS recipe_ingredient CASCADE;
DROP SEQUENCE IF EXISTS recipe_ingredient_id_seq;
CREATE TABLE recipe_ingredient
(
    id              long identity PRIMARY KEY NOT NULL,
    recipe_id       long,
    ingredient_name varchar
);

DROP TABLE IF EXISTS student_recipe CASCADE;
DROP SEQUENCE IF EXISTS student_recipe_id_seq;
CREATE TABLE student_recipe
(
    id         long identity PRIMARY KEY NOT NULL,
    student_id long,
    recipe_id  long
);

ALTER TABLE room
    ADD FOREIGN KEY (building_id)
        REFERENCES building (id)
        ON DELETE CASCADE;

ALTER TABLE picture
    ADD FOREIGN KEY (building_id)
        REFERENCES building (id)
        ON DELETE CASCADE;

ALTER TABLE resident
    ADD FOREIGN KEY (student_id)
        REFERENCES student (id)
        ON DELETE CASCADE;

ALTER TABLE resident
    ADD FOREIGN KEY (room_id)
        REFERENCES room (id)
        ON DELETE CASCADE;

ALTER TABLE recipe_ingredient
    ADD FOREIGN KEY (recipe_id)
        REFERENCES recipe (id)
        ON DELETE CASCADE;

ALTER TABLE student_recipe
    ADD FOREIGN KEY (recipe_id)
        REFERENCES recipe (id)
        ON DELETE CASCADE;

ALTER TABLE student_recipe
    ADD FOREIGN KEY (student_id)
        REFERENCES student(id)
        ON DELETE CASCADE;
