package com.highfaev.additional;

import java.sql.Connection;
import com.highfaev.resources.sql.SqlScripts;
import com.highfaev.resources.helpers.SqlWrapper;

public class TablesCreator {
    public static void createUsersTable(Connection connection)
    {
        String sqlScript = SqlScripts.createUsersTable;
        SqlWrapper.runSqlScript(connection, sqlScript);
    }
}
