package com.highfaev.resources.sql;

import java.sql.PreparedStatement;

public interface BasicSqlInterface {
    public void fillStatement(PreparedStatement preparedStatement);
}
