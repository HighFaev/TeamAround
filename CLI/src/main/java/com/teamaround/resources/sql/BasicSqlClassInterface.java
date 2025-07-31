package com.teamaround.resources.sql;

import java.sql.ResultSet;

public interface BasicSqlClassInterface<T> {
    public void mapFromResultSet(ResultSet resultSet);
    public T create(String[] args) throws IllegalArgumentException;
}
