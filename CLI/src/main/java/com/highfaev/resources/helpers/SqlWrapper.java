package com.highfaev.resources.helpers;

import java.sql.Connection;
import java.sql.SQLException;

import com.highfaev.resources.sql.*;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import lombok.Cleanup;

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
    public static void runSqlScript(Connection connection, String sqlScript)
    {
        try
        {
            connection.createStatement().execute(sqlScript);
        }
        catch(SQLException e)
        {
            System.out.println("CANNT RUN SQL SCRIPT:\n" + sqlScript + "\nERROR CODE:\n" + e.getMessage());
        }
        return;
    }
    public static <InsertClass extends BasicSqlClassInterface> void insertData(Connection connection, String sqlScript, InsertClass insertClass)
    {
        try
        {
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sqlScript);
            insertClass.fillInsertParameters(preparedStatement);
            preparedStatement.execute();
        }
        catch (SQLException e)
        {
            System.out.println("CANNT INSERT DATA. SCRIPT:\n" + sqlScript + "\nERROR CODE:\n" + e.getMessage());
        }
    }
    
    public static <OutputClass extends BasicSqlClassInterface> Table<OutputClass> getData(Connection connection, String sqlScript, Class<OutputClass> outputClass)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlScript);
            ResultSet resultSet = preparedStatement.executeQuery();
            Table<OutputClass> table = new Table<OutputClass>(); 
            
            while(resultSet.next())
            {
                OutputClass newRow = outputClass.getDeclaredConstructor().newInstance();
                newRow.mapFromResultSet(resultSet);
                table.addRow(newRow); 
            }
            return table;
        }
        catch(Exception e)
        {
            System.out.println("CANNT GET DATA. SCRIPT:\n" + sqlScript + "\nERROR:\n" + e.getStackTrace());
            return null;
        }
        
    }
}
