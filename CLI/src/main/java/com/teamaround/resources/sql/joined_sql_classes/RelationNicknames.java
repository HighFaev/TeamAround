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
public class RelationNicknames implements BasicSqlClassInterface<RelationNicknames>{
    String parentNickname;
    String childrenNickname;

    @Override
    public RelationNicknames create(String[] args) throws IllegalArgumentException
    {
        if (args.length <= 2)
        {
            throw new IllegalArgumentException("Wrong amount of arguments for UserChildren creation. Use --help");
        }
        RelationNicknamesBuilder userChildrenBuilder = new RelationNicknamesBuilder()
            .parentNickname(args[1])
            .childrenNickname(args[2]);
        
        return userChildrenBuilder.build();
    }

    @Override
    public void mapFromResultSet(ResultSet resultSet)
    {
        try {
            this.parentNickname = resultSet.getString("parent_nickname");
            this.childrenNickname = resultSet.getString("children_nickname");
        }
        catch(SQLException e)
        {
            System.out.println("CAN'T PARSE DATA IN role" + e.getMessage());
        }
    }
}
