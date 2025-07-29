CREATE TABLE users_tree (
    parent_user_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    PRIMARY KEY (parent_user_id, user_id),
    FOREIGN KEY (parent_user_id) REFERENCES users(user_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);