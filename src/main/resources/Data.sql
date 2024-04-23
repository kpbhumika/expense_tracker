-- Delete existing data to avoid conflicts
delete from expense;
delete from category;
delete from "user";

-- Insert data into the user table
insert into "user" (id, name, email) values (1, 'Emily', 'emily@example.com');
insert into "user" (id, name, email) values (2, 'Michael', 'michael@example.com');
insert into "user" (id, name, email) values (3, 'Sophia', 'sophia@example.com');

-- Insert data into the category table
insert into category (id, name) values (1, 'Groceries');
insert into category (id, name) values (2, 'Entertainment');
insert into category (id, name) values (3, 'Utilities');

-- Insert data into the expense table
-- Insert data into the expense table
insert into expense (id, expense_date, description, category_id, user_id)
values (1, '2019-06-17T12:00:00.000Z', 'Weekly Grocery Shopping', 1, 1);
insert into expense (id, expense_date, description, category_id, user_id)
values (2, '2019-06-18T20:00:00.000Z','Movie Night Out', 2, 2);
insert into expense (id, expense_date, description, category_id, user_id)
values (3, '2019-06-20T10:00:00.000Z', 'Electricity Bill', 3, 3);
