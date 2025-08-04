CREATE TABLE IF NOT EXISTS relations (
    parent_user_id INTEGER NOT NULL,
    children_user_id INTEGER NOT NULL,
    PRIMARY KEY (parent_user_id, children_user_id),
    FOREIGN KEY (parent_user_id) REFERENCES users(user_id),
    FOREIGN KEY (children_user_id) REFERENCES users(user_id)
);