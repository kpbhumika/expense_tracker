-- Delete existing data to avoid conflicts
DELETE FROM expense;
DELETE FROM category;
DELETE FROM "user";

-- Insert data into the user table
INSERT INTO "user" (name, email) VALUES ('Emily', 'emily@example.com');
INSERT INTO "user" (name, email) VALUES ('Michael', 'michael@example.com');
INSERT INTO "user" (name, email) VALUES ('Sophia', 'sophia@example.com');

-- Insert data into the category table
INSERT INTO category (name) VALUES ('Groceries');
INSERT INTO category (name) VALUES ('Entertainment');
INSERT INTO category (name) VALUES ('Utilities');

-- Insert data into the expense table
INSERT INTO expense (expense_date, description, category_id, user_id, amount)
VALUES ('2019-06-17', 'Weekly Grocery Shopping', 1, 1, 50);
INSERT INTO expense (expense_date, description, category_id, user_id, amount)
VALUES ('2019-06-18', 'Movie Night Out', 2, 2, 30);
INSERT INTO expense (expense_date, description, category_id, user_id, amount)
VALUES ('2019-06-20', 'Electricity Bill', 3, 3, 150);
