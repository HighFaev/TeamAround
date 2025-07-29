SELECT users.nickname, roles.name FROM users_roles
INNER JOIN users ON users_roles.user_id = users.user_id
INNER JOIN roles ON users_roles.role_id = roles.role_id
WHERE users.user_id = ?;