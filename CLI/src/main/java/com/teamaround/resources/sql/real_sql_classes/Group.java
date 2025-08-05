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
public class Group implements BasicSqlClassInterface<Group>, RealSqlClassInterface{
    private int groupId;
    private String name;
    @Builder.Default private String description = null;

    @Override
    public Group create(String[] args) throws IllegalArgumentException{
        if(args.length <= 1)
        {
            throw new IllegalArgumentException("Wrong amount of arguments for Group creation. Use --help");
        }
        GroupBuilder builder = Group.builder()
            .name(args[1]);
        if(args.length >= 2)
        {
            builder.description(args[2]);
        }
        return builder.build();
    }

    @Override
    public void fillInsertParameters(PreparedStatement preparedStatement)
    {
        try {
            preparedStatement.setString(1, this.name);
            preparedStatement.setString(2, this.description);
        } catch (SQLException e) {
            System.out.println("CAN'T INSERT DATA INTO STATEMENT " + e.getMessage());
        }
    }

    @Override
    public void mapFromResultSet(ResultSet resultSet)
    {
        try {
            this.groupId = resultSet.getInt("group_id");
            this.name = resultSet.getString("name");
            this.description = resultSet.getString("description");
        }
        catch(SQLException e)
        {
            System.out.println("CAN'T PARSE DATA IN Group" + e.getMessage());
        }

    }
}
