package com.highfaev.resources.sql;

public class User {
    private int user_id;
    private String nickname;
    private String first_name;
    private String last_name;
    private String email;
    private String telegram;
    private String bio;

    public User(int user_id, String nickname, String first_name, String last_name, String email, String telegram, String bio)
    {
        this.user_id = user_id;
        this.nickname = nickname;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.telegram = telegram;
        this.bio = bio;
    }
}
