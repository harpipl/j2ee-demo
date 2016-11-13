insert into PERSON (ID, VERSION, FIRST_NAME, LAST_NAME) values (1, 0, 'Will', 'Smith');
insert into PERSON (ID, VERSION, FIRST_NAME, LAST_NAME) values (2, 0, 'Bill', 'Murray');

insert into ADDRESS (ID, VERSION, CITY, POSTAL_CODE, STREET, TYPE, PERSON_ID)  values (3, 0, 'New York', '10022', 'Park Ave', 'PERMANENT', 1);

insert into HIBERNATE_SEQUENCES (SEQUENCE_NAME, NEXT_VAL) values ('default', 4);