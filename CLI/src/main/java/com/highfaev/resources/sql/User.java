package com.highfaev.resources.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User implements BasicSqlInterface{
    private int user_id;
    private String nickname;
    private String first_name;
    private String last_name;
    private String email;
    private String telegram;
    private String bio;

    public User()
    {
        //Empty constructor
    }
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

    public void printData()
    {
        System.out.printf("USER_DATA: user_id:%s nickname:%s first_name:%s last_name:%s email:%s telegram:%s bio:%s\n",
                            this.user_id, this.nickname, this.first_name, this.last_name, this.email, this.telegram, this.bio);
    }

    public void fillInsertStatement(PreparedStatement preparedStatement)
    {
        try {
            preparedStatement.setString(1, this.nickname);
            preparedStatement.setString(2, this.first_name);
            preparedStatement.setString(3, this.last_name);
            preparedStatement.setString(4, this.email);
            preparedStatement.setString(5, this.telegram);
            preparedStatement.setString(6, this.bio);
        } catch (SQLException e) {
            System.out.println("CANNT INSERT DATA INTO STATEMENT " + e.getMessage());
        }
    }

    public void parseOutputData(ResultSet resultSet)
    {
        try {
            this.user_id = Integer.parseInt(resultSet.getString("user_id"));
            this.nickname = resultSet.getString("nickname");
            this.first_name = resultSet.getString("first_name");
            this.last_name = resultSet.getString("last_name");
            this.email = resultSet.getString("email");
            this.telegram = resultSet.getString("telegram");
            this.bio = resultSet.getString("bio");
        }
        catch(SQLException e)
        {
            System.out.println("CANNT PARSE DATA IN users" + e.getMessage());
        }

    }

    public int getUserId()
    {
        return this.user_id;
    }
    public String getNickname()
    {
        return this.nickname;
    }
    public String getFirstName()
    {
        return this.first_name;
    }
    public String getLastName()
    {
        return this.last_name;
    }
    public String getEmail()
    {
        return this.email;
    }
    public String getTelegram()
    {
        return this.telegram;
    }
    public String getBio()
    {
        return this.bio;
    }
}
