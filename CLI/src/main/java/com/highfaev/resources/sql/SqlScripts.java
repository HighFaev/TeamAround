package com.highfaev.resources.sql;

public class SqlScripts {
    public static String createUsersTable = "CREATE TABLE users(" +
                "user_id integer PRIMARY KEY GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 1000000 CACHE 1 )," +
                "nickname character varying(32) NOT NULL," +
                "first_name text NOT NULL," +
                "last_name text NOT NULL," +
                "email text NOT NULL," +
                "telegram character varying(32)," +
                "bio text" +
                ")";
    public static String insertToUsers = "INSERT INTO users (nickname, first_name, last_name, email, telegram, bio) VALUES (?, ?, ?, ?, ?, ?)";
    public static String getAllUsers = "SELECT * FROM users";
}
