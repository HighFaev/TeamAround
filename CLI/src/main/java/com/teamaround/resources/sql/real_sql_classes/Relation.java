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
public class Relation implements BasicSqlClassInterface<Relation>, RealSqlClassInterface{
    private int parentUserId;
    private int childrenUserId;
    
    @Override
    public void fillInsertParameters(PreparedStatement preparedStatement)
    {
        try {
            preparedStatement.setInt(1, this.parentUserId);
            preparedStatement.setInt(2, this.childrenUserId);
        } catch (SQLException e) {
            System.out.println("CAN'T INSERT DATA INTO STATEMENT " + e.getMessage());
        }
    }

    @Override
    public void mapFromResultSet(ResultSet resultSet)
    {
        try {
            this.parentUserId = resultSet.getInt("parent_user_id");
            this.childrenUserId = resultSet.getInt("children_user_id");
        }
        catch(SQLException e)
        {
            System.out.println("CAN'T PARSE DATA IN role" + e.getMessage());
        }
    }

    @Override
    public Relation create(String[] args) throws IllegalArgumentException{
        if(args.length <= 1)
        {
            throw new IllegalArgumentException("Wrong amount of arguments" + args.length + ". Use --help");
        }
        RelationBuilder builder = new RelationBuilder()
            .parentUserId(Integer.parseInt(args[1]))
            .childrenUserId(Integer.parseInt(args[2]));
        return builder.build();
    }
}
