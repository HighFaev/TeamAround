package com.teamaround.resources.sql.real_sql_classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.teamaround.resources.sql.BasicSqlClassInterface;
import com.teamaround.resources.sql.RealSqlClassInterface;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role implements BasicSqlClassInterface<Role>, RealSqlClassInterface{
    private int roleId;
    private String name;

    @Override
    public void fillInsertParameters(PreparedStatement preparedStatement)
    {
        try {
            preparedStatement.setString(1, this.name);
        } catch (SQLException e) {
            System.out.println("CAN'T INSERT DATA INTO STATEMENT " + e.getMessage());
        }
    }

    @Override
    public void mapFromResultSet(ResultSet resultSet)
    {
        try {
            this.roleId = resultSet.getInt("role_id");
            this.name = resultSet.getString("name");
        }
        catch(SQLException e)
        {
            System.out.println("CAN'T PARSE DATA IN role" + e.getMessage());
        }
    }

    @Override
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
