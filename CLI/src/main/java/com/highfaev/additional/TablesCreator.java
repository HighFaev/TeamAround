package com.highfaev.additional;

import java.sql.Connection;

import com.highfaev.resources.helpers.SqlWrapper;
import com.highfaev.resources.sql.SqlScripts;

public class TablesCreator {
    public static void createUsersTable(Connection connection)
    {
        SqlWrapper.runSqlScript(connection, SqlScripts.createUsersTable);
    }
}
