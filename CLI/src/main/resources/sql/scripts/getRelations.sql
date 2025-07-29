SELECT 
    parent.nickname AS parent_nickname, 
    child.nickname AS children_nickname
FROM relations
INNER JOIN users AS parent ON relations.parent_user_id = parent.user_id 
INNER JOIN users AS child ON relations.children_user_id = child.user_id
WHERE parent_user_id = ?;