drop table if exists wands cascade;
create table wands(
                      id serial primary key,
                      wood_type varchar(100),
                      color varchar(50)
);

drop table if exists teachers cascade;
create table teachers(
                         id serial primary key,
                         name varchar(100),
                         subject varchar(100),
                         is_witch boolean,
                         age int,
                         wand_id int
);

drop table if exists spells cascade;
create table spells(
                       id serial primary key,
                       name varchar(100),
                       usage varchar(255),
                       power int,
                       is_banned boolean
);

alter table teachers
    add foreign key (wand_id) references wands(id);

drop table if exists wand_spell cascade;
create table wand_spell(
                           id serial primary key,
                           wand_id int,
                           spell_id int
);

alter table wand_spell
    add foreign key (wand_id) references wands(id);
alter table wand_spell
    add foreign key (spell_id) references spells(id);
