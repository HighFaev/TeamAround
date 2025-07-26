package com.highfaev.resources.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface BasicSqlInterface {
    public void fillInsertStatement(PreparedStatement preparedStatement);
    public void parseOutputData(ResultSet resultSet);
    public void printData();
}
