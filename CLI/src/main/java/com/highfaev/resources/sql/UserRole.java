package com.highfaev.resources.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

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
    public UserRole create(String[] args) throws IllegalArgumentException
    {
        if(args.length <= 2)
        {
            throw new IllegalArgumentException("Wrong amount of arguments for User creation. Use --help");
        }
        UserRoleBuilder builder = UserRole.builder()
            .nickname(args[1])
            .roleName(args[2]);
        return builder.build();
    }
    public void mapFromResultSet(ResultSet resultSet)
    {
        try {
            this.nickname = resultSet.getString("nickname");
            this.roleName = resultSet.getString("name");
        }
        catch(SQLException e)
        {
            System.out.println("CANNT PARSE DATA IN users" + e.getMessage());
        }

    }
}
