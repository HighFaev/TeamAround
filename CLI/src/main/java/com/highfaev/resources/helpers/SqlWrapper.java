package com.highfaev.resources.helpers;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class SqlWrapper {
    public static Connection connectToDatabase(String url, String name, String password)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url, name, password);
            return connection;
        }
        catch(SQLException e)
        {
            System.out.println("CANNT CONNECT TO DB!!!" + e.getMessage());

            return null;
        }
        
    }
    public static void sqlRunner(Connection connection, String SqlScript)
    {
        try
        {
            connection.createStatement().execute(SqlScript);
        }
        catch(SQLException e)
        {
            System.out.println("CANNT RUN SQL SCRIPT:\n" + SqlScript + "\nERROR CODE:\n" + e.getMessage());
        }
        return;
    }
}
