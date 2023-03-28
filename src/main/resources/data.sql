INSERT INTO BUILDING(NAME) values ('GRYFFINDOR');
INSERT INTO BUILDING(NAME) values ('HUFFLEPUFF');
INSERT INTO BUILDING(NAME) values ('RAVENCLAW');
INSERT INTO BUILDING(NAME) values ('SLYTHERIN');


INSERT INTO PICTURE(NAME, BUILDING_ID) values ('Lady with a dog', 1);
INSERT INTO PICTURE(NAME, BUILDING_ID) values ('The life of Dumbledore', 1);
INSERT INTO PICTURE(NAME, BUILDING_ID) values ('The old man', 1);
INSERT INTO PICTURE(NAME, BUILDING_ID) values ('Hogwarts', 2);
INSERT INTO PICTURE(NAME, BUILDING_ID) values ('Rats and snakes', 2);
INSERT INTO PICTURE(NAME, BUILDING_ID) values ('A magician from Egypt', 2);


INSERT INTO ROOM(BUILDING_ID, HOUSE_TYPE, PLACES) values (1, 'GRYFFINDOR', 4);
INSERT INTO ROOM(BUILDING_ID, HOUSE_TYPE, PLACES) values (1, 'GRYFFINDOR', 4);
INSERT INTO ROOM(BUILDING_ID, HOUSE_TYPE, PLACES) values (2, 'HUFFLEPUFF', 4);
INSERT INTO ROOM(BUILDING_ID, HOUSE_TYPE, PLACES) values (2, 'HUFFLEPUFF', 4);
INSERT INTO ROOM(BUILDING_ID, HOUSE_TYPE, PLACES) values (3, 'RAVENCLAW', 4);
INSERT INTO ROOM(BUILDING_ID, HOUSE_TYPE, PLACES) values (3, 'RAVENCLAW', 4);
INSERT INTO ROOM(BUILDING_ID, HOUSE_TYPE, PLACES) values (4, 'SLYTHERIN', 4);
INSERT INTO ROOM(BUILDING_ID, HOUSE_TYPE, PLACES) values (4, 'SLYTHERIN', 4);


INSERT INTO STUDENT(NAME, HOUSE_TYPE, PET_TYPE) values ('Hermione Granger', 'GRYFFINDOR', 'CAT');
INSERT INTO STUDENT(NAME, HOUSE_TYPE, PET_TYPE) values ('Ron Weasley', 'GRYFFINDOR', 'RAT');
INSERT INTO STUDENT(NAME, HOUSE_TYPE, PET_TYPE) values ('Neville Longbottom', 'GRYFFINDOR', 'NONE');
INSERT INTO STUDENT(NAME, HOUSE_TYPE, PET_TYPE) values ('Justin Finch-Fletchley', 'HUFFLEPUFF', 'OWL');
INSERT INTO STUDENT(NAME, HOUSE_TYPE, PET_TYPE) values ('Hannah Abbott', 'HUFFLEPUFF', 'CAT');
INSERT INTO STUDENT(NAME, HOUSE_TYPE, PET_TYPE) values ('Susan Bones', 'HUFFLEPUFF', 'OWL');
INSERT INTO STUDENT(NAME, HOUSE_TYPE, PET_TYPE) values ('Terry Boot', 'RAVENCLAW', 'NONE');
INSERT INTO STUDENT(NAME, HOUSE_TYPE, PET_TYPE) values ('Padma Patil', 'RAVENCLAW', 'CAT');
INSERT INTO STUDENT(NAME, HOUSE_TYPE, PET_TYPE) values ('Michael Corner', 'RAVENCLAW', 'OWL');
INSERT INTO STUDENT(NAME, HOUSE_TYPE, PET_TYPE) values ('Draco Malfoy', 'SLYTHERIN', 'NONE');
INSERT INTO STUDENT(NAME, HOUSE_TYPE, PET_TYPE) values ('Pansy Parkinson', 'SLYTHERIN', 'OWL');
INSERT INTO STUDENT(NAME, HOUSE_TYPE, PET_TYPE) values ('Gregory Goyle', 'SLYTHERIN', 'RAT');

INSERT INTO RESIDENT(STUDENT_ID, ROOM_ID) values (1,1);
INSERT INTO RESIDENT(STUDENT_ID, ROOM_ID) values (2,2);
INSERT INTO RESIDENT(STUDENT_ID, ROOM_ID) values (3,2);
INSERT INTO RESIDENT(STUDENT_ID, ROOM_ID) values (4,3);
INSERT INTO RESIDENT(STUDENT_ID, ROOM_ID) values (5,4);
INSERT INTO RESIDENT(STUDENT_ID, ROOM_ID) values (6,4);
INSERT INTO RESIDENT(STUDENT_ID, ROOM_ID) values (7,5);
INSERT INTO RESIDENT(STUDENT_ID, ROOM_ID) values (8,6);
INSERT INTO RESIDENT(STUDENT_ID, ROOM_ID) values (9,5);
INSERT INTO RESIDENT(STUDENT_ID, ROOM_ID) values (10,7);
INSERT INTO RESIDENT(STUDENT_ID, ROOM_ID) values (11,8);
INSERT INTO RESIDENT(STUDENT_ID, ROOM_ID) values (12,7);

INSERT INTO RECIPE(NAME) values ('Polyjuice Potion');
INSERT INTO RECIPE(NAME) values ('Felix Felicis');
INSERT INTO RECIPE(NAME) values ('Elixir of Life');
INSERT INTO RECIPE(NAME) values ('Beautification Potion');
INSERT INTO RECIPE(NAME) values ('Dogbane Potion');

INSERT INTO ingredients(NAME, RECIPE_ID) values ('FLUXWEED', 1);
INSERT INTO ingredients(NAME, RECIPE_ID) values ('KNOTGRASS', 1);
INSERT INTO ingredients(NAME, RECIPE_ID) values ('LACEWING_FLIES', 1);
INSERT INTO ingredients(NAME, RECIPE_ID) values ('LEECHES', 1);
INSERT INTO ingredients(NAME, RECIPE_ID) values ('HORN_OF_BICORN', 1);

INSERT INTO ingredients(NAME, RECIPE_ID) values ('HORN_OF_BICORN', 2);
INSERT INTO ingredients(NAME, RECIPE_ID) values ('BOOMSLANG_SKIN', 2);
INSERT INTO ingredients(NAME, RECIPE_ID) values ('RAT_HAIR', 2);
INSERT INTO ingredients(NAME, RECIPE_ID) values ('KNOTGRASS', 2);
INSERT INTO ingredients(NAME, RECIPE_ID) values ('LEECHES', 2);

INSERT INTO ingredients(NAME, RECIPE_ID) values ('PHILOSOPHERS_STONE', 3);
INSERT INTO ingredients(NAME, RECIPE_ID) values ('HORN_OF_BICORN', 3);
INSERT INTO ingredients(NAME, RECIPE_ID) values ('CATERPILLAR', 3);
INSERT INTO ingredients(NAME, RECIPE_ID) values ('KNOTGRASS', 3);
INSERT INTO ingredients(NAME, RECIPE_ID) values ('BOOMSLANG_SKIN', 3);

INSERT INTO ingredients(NAME, RECIPE_ID) values ('UNICORN_HAIR', 4);
INSERT INTO ingredients(NAME, RECIPE_ID) values ('FAIRY_WINGS', 4);
INSERT INTO ingredients(NAME, RECIPE_ID) values ('ROSE_PETALS', 4);
INSERT INTO ingredients(NAME, RECIPE_ID) values ('MORNING_DEW', 4);
INSERT INTO ingredients(NAME, RECIPE_ID) values ('GINGER_ROOTS', 4);

INSERT INTO ingredients(NAME, RECIPE_ID) values ('ANJELICA', 5);
INSERT INTO ingredients(NAME, RECIPE_ID) values ('EYEBALL', 5);
INSERT INTO ingredients(NAME, RECIPE_ID) values ('SLOTH_BRAIN', 5);
INSERT INTO ingredients(NAME, RECIPE_ID) values ('WITCH_MUMMY', 5);
INSERT INTO ingredients(NAME, RECIPE_ID) values ('LEECHES', 5);

INSERT INTO studentrecipes(STUDENT_ID, RECIPE_ID) values (1, 2);

insert into wands(wood_type, color)
values ('wand type1', 'black'),
       ('wand type2', 'white');

insert into teachers(name, subject, is_witch, age, wand_id)
values ('teacher name1', 'subject1', true, 30, 1),
       ('teacher name2', 'subejct2', false, 100, 2);

insert into spells(name, usage, power, is_banned)
values ('spell1', 'usage1', 10, true),
       ('spell2', 'usage2', 50, false);

insert into wand_spell(wand_id, spell_id)
values (1,1), (1,2), (2,1);











