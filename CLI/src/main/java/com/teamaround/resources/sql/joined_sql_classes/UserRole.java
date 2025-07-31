package com.teamaround.resources.sql.joined_sql_classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.teamaround.resources.sql.BasicSqlClassInterface;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRole implements BasicSqlClassInterface<UserRole>{
    private String nickname;
    private String roleName;
    @Override
    public UserRole create(String[] args) throws IllegalArgumentException
    {
        if(args.length <= 2)
        {
            throw new IllegalArgumentException("Wrong amount of arguments for User creation. Use --help");
        }
        UserRoleBuilder builder;
        builder = UserRole.builder()
            .nickname(args[1])
            .roleName(args[2]);
        return builder.build();
    }
    
    @Override
    public void mapFromResultSet(ResultSet resultSet)
    {
        try {
            this.nickname = resultSet.getString("nickname");
            this.roleName = resultSet.getString("name");
        }
        catch(SQLException e)
        {
            System.out.println("CAN'T PARSE DATA IN users" + e.getMessage());
        }

    }
}
