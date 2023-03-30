insert into wands(wood_type, color)
values ('wand type1', 'black'),
       ('wand type2', 'white');

insert into teachers(name, subject, is_witch, age, wand_id)
values ('test_teacher_1', 'subject1', true, 30, 1),
       ('test_teacher_2', 'subejct2', false, 100, 2);

insert into spells(name, usage, power, is_banned)
values ('test_spell1', 'usage1', 10, true),
       ('test_spell2', 'usage2', 50, false);

insert into wand_spell(wand_id, spell_id)
values (1,1), (1,2), (2,1);











