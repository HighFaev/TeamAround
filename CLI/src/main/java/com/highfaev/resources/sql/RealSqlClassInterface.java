package com.highfaev.resources.sql;

import java.sql.PreparedStatement;

public interface RealSqlClassInterface {
    public void fillInsertParameters(PreparedStatement preparedStatement);
}
