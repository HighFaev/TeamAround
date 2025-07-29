package com.highfaev.resources.sql.real_sql_classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.highfaev.resources.sql.BasicSqlClassInterface;
import com.highfaev.resources.sql.RealSqlClassInterface;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements BasicSqlClassInterface<User>, RealSqlClassInterface{
    private int userId;
    private String nickname;
    private String firstName;
    private String lastName;
    private String email;
    @Builder.Default private String telegram = "None";
    @Builder.Default private String bio = "None";

    public User create(String[] args) throws IllegalArgumentException{
        if(args.length <= 4)
        {
            throw new IllegalArgumentException("Wrong amount of arguments for User creation. Use --help");
        }
        UserBuilder builder = User.builder()
            .nickname(args[1])
            .firstName(args[2])
            .lastName(args[3])
            .email(args[4]);
        if(args.length >= 6)
        {
            builder.telegram(args[5]);
        }
        if(args.length >= 7)
        {
            builder.bio(args[6]);
        }
        return builder.build();
    } 

    public void fillInsertParameters(PreparedStatement preparedStatement)
    {
        try {
            preparedStatement.setString(1, this.nickname);
            preparedStatement.setString(2, this.firstName);
            preparedStatement.setString(3, this.lastName);
            preparedStatement.setString(4, this.email);
            preparedStatement.setString(5, this.telegram);
            preparedStatement.setString(6, this.bio);
        } catch (SQLException e) {
            System.out.println("CANNT INSERT DATA INTO STATEMENT " + e.getMessage());
        }
    }

    public void mapFromResultSet(ResultSet resultSet)
    {
        try {
            this.userId = resultSet.getInt("user_id");
            this.nickname = resultSet.getString("nickname");
            this.firstName = resultSet.getString("first_name");
            this.lastName = resultSet.getString("last_name");
            this.email = resultSet.getString("email");
            this.telegram = resultSet.getString("telegram");
            this.bio = resultSet.getString("bio");
        }
        catch(SQLException e)
        {
            System.out.println("CANNT PARSE DATA IN user" + e.getMessage());
        }

    }
}
