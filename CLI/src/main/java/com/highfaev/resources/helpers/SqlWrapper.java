package com.highfaev.resources.helpers;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.highfaev.resources.sql.*;

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
            insertClass.fillInsertStatement(preparedStatement);
            preparedStatement.execute();
        }
        catch (SQLException e)
        {
            System.out.println("CANNT INSERT DATA. SCRIPT:\n" + SqlScript + "\nERROR CODE:\n" + e.getMessage());
        }
    }
    
    public static <OutputClass extends BasicSqlInterface> Table<OutputClass> getData(Connection connection, String SqlScript, Class<OutputClass> outputClass)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(SqlScript);
            ResultSet resultSet = preparedStatement.executeQuery();
            Table<OutputClass> table = new Table<OutputClass>(); 
            while(resultSet.next())
            {
                OutputClass newRow = outputClass.getDeclaredConstructor().newInstance();
                newRow.parseOutputData(resultSet);
                table.addRow(newRow); 
            }
            return table;
        }
        catch(Exception e)
        {
            System.out.println("CANNT GET DATA. SCRIPT:\n" + SqlScript + "\nERROR:\n" + e.getLocalizedMessage());
            return null;
        }
        
    }
}
