INSERT INTO users (email, first_name, last_name, username, password, state, status)
VALUES ('Alexei7a@gmail.com', 'Alexei', 'Murinets', 'leha777', '$2a$10$PPy.J9YdkA.5l4XA.PyvHezC2TAqORsZjrEM8LoejzE0kwLTdqGXO', 'ACTIVE', 'BASIC');
INSERT INTO users (email, first_name, last_name, username, password, state, status)
VALUES ('Alisher@gmail.com', 'Alisher', 'Morgenshtern', 'clown', '$2a$10$PPy.J9YdkA.5l4XA.PyvHezC2TAqORsZjrEM8LoejzE0kwLTdqGXO', 'ACTIVE', 'BASIC');

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (1, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);
