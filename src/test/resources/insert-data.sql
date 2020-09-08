insert into salespeople (ID, CPF, NAME, SALARY) values (1, '00000000001', 'Pedro', 5000);
insert into salespeople (ID, CPF, NAME, SALARY) values (20, '00000000001', 'Pedro', 5000);
insert into salespeople (ID, CPF, NAME, SALARY) values (2, '00000000002', 'José', 5500);
insert into salespeople (ID, CPF, NAME, SALARY) values (19, '00000000002', 'José', 5500);
insert into salespeople (ID, CPF, NAME, SALARY) values (3, '00000000003', 'Maria', 1000);
insert into salespeople (ID, CPF, NAME, SALARY) values (18, '00000000003', 'Maria', 1000);
insert into salespeople (ID, CPF, NAME, SALARY) values (4, '00000000004', 'Margarida', 1000);
insert into salespeople (ID, CPF, NAME, SALARY) values (17, '00000000004', 'Margarida', 1000);

insert into clients (ID, CPF, NAME, BUSINESS_SITE) values (5, '00000000005', 'Sarah', 'CENTRO');
insert into clients (ID, CPF, NAME, BUSINESS_SITE) values (24, '00000000005', 'Sarah', 'CENTRO');
insert into clients (ID, CPF, NAME, BUSINESS_SITE) values (6, '00000000006', 'Maria Alice', 'CENTRO');
insert into clients (ID, CPF, NAME, BUSINESS_SITE) values (23, '00000000006', 'Maria Alice', 'CENTRO');
insert into clients (ID, CPF, NAME, BUSINESS_SITE) values (7, '00000000007', 'Alane', 'CENTRO');
insert into clients (ID, CPF, NAME, BUSINESS_SITE) values (22, '00000000007', 'Alane', 'CENTRO');
insert into clients (ID, CPF, NAME, BUSINESS_SITE) values (8, '00000000008', 'Raphaelle', 'CENTRO');
insert into clients (ID, CPF, NAME, BUSINESS_SITE) values (21, '00000000008', 'Raphaelle', 'CENTRO');

insert into sales (id, sale_id, silesman_name, amount) values (9, 4, 'Pedro', 80.0);
insert into sales (id, sale_id, silesman_name, amount) values (10, 3, 'José', 50.0);
insert into sales (id, sale_id, silesman_name, amount) values (11, 2, 'Maria', 150.0);
insert into sales (id, sale_id, silesman_name, amount) values (12, 1, 'Margarida', 10.0);

insert into sales_items (id, sale_id, item_id, quantity, price) values (13, 4, 5, 1, 30.0);
insert into sales_items (id, sale_id, item_id, quantity, price) values (14, 3, 6, 1, 50.0);
insert into sales_items (id, sale_id, item_id, quantity, price) values (15, 2, 7, 1, 150.0);
insert into sales_items (id, sale_id, item_id, quantity, price) values (16, 1, 8, 1, 10.0);
