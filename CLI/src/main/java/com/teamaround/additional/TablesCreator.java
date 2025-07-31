package com.teamaround.additional;

import java.sql.Connection;

import com.teamaround.resources.helpers.SqlWrapper;
import com.teamaround.resources.sql.SqlScripts;

public class TablesCreator {
    public static void createUsersTable(Connection connection)
    {
        SqlWrapper.runSqlScript(connection, SqlScripts.createUsersTable);
    }
}
