package com.highfaev.resources.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface BasicSqlClassInterface {
    public void fillInsertParameters(PreparedStatement preparedStatement);
    public void mapFromResultSet(ResultSet resultSet);
}
