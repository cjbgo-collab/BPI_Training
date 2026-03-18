DELETE FROM user_roles;
DELETE FROM roles;
DELETE FROM users;

INSERT INTO users(username, password, enabled) VALUES
('dev_1', 'dev1', true),
('dev_2', 'dev2', true),
('mgr_1', 'dev3', true);

INSERT INTO roles(name) VALUES
('ROLE_USER'),
('ROLE_MANAGER');

INSERT INTO user_roles(user_id, role_id) VALUES
(1, 1),  -- dev_1 → ROLE_USER
(2, 1),  -- dev_2 → ROLE_USER
(3, 2);  -- mgr_1 → ROLE_MANAGER
