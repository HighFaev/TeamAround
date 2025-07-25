package com.highfaev;

import java.sql.Connection;

import com.highfaev.additional.TablesCreator;
import com.highfaev.resources.helpers.SqlWrapper;

public class CLI_MAIN {
    private String databaseUrl = "jdbc:postgresql://localhost:5432/teamAround";
    private String username = "postgres";
    private String password = "1111";
    public static void main(String[] args)
    {
        System.out.println("Hi from teamAround team!");
        CLI_MAIN cliMain = new CLI_MAIN();
        Connection connection = SqlWrapper.connectToDatabase(cliMain.databaseUrl, cliMain.username, cliMain.password);
        TablesCreator.createUsersTable(connection);
    }
}