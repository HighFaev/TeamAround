package com.highfaev.resources.helpers;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.highfaev.resources.sql.BasicSqlInterface;

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
    public static void runSqlScript(Connection connection, String SqlScript)
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
    public static <InsertClass extends BasicSqlInterface> void insertData(Connection connection, String SqlScript, InsertClass insertClass)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(SqlScript);
            insertClass.fillStatement(preparedStatement);
            preparedStatement.execute();
        }
        catch (SQLException e)
        {
            System.out.println("CANNT INSERT DATA. SCRIPT:\n" + SqlScript + "\nERROR CODE:\n" + e.getMessage());
        }
    }
}
