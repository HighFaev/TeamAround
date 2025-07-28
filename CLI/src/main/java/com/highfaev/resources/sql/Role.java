package com.highfaev.resources.sql;

import java.sql.PreparedStatement;
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
public class Role implements BasicSqlClassInterface<Role>{
    private int roleId;
    private String name;

    public void fillInsertParameters(PreparedStatement preparedStatement)
    {
        try {
            preparedStatement.setString(1, this.name);
        } catch (SQLException e) {
            System.out.println("CANNT INSERT DATA INTO STATEMENT " + e.getMessage());
        }
    }
    public void mapFromResultSet(ResultSet resultSet)
    {
        try {
            this.roleId = resultSet.getInt("role_id");
            this.name = resultSet.getString("name");
        }
        catch(SQLException e)
        {
            System.out.println("CANNT PARSE DATA IN users" + e.getMessage());
        }
    }
    public Role create(String[] args) throws IllegalArgumentException{
        if(args.length <= 1)
        {
            throw new IllegalArgumentException("Wrong amount of arguments" + args.length + ". Use --help");
        }
        RoleBuilder builder = Role.builder()
            .name(args[1]);
        return builder.build();
    }
}
