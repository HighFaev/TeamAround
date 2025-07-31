package com.teamaround.resources.helpers;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import com.teamaround.resources.sql.BasicSqlClassInterface;
import com.teamaround.resources.sql.RealSqlClassInterface;
import com.teamaround.resources.sql.SqlScripts;
import com.teamaround.resources.sql.Table;

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
            System.out.println("CAN'T CONNECT TO DB!!!" + e.getMessage());

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
            System.out.println("CAN'T RUN SQL SCRIPT:\n" + sqlScript + "\nERROR CODE:\n" + e.getMessage());
        }
    }
    
    public static ResultSet runSqlScript(Connection connection, String sqlScript, ArrayList<Object> parameters)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlScript);
            for(int index = 1; index <= parameters.size(); index++)
            {
                preparedStatement.setObject(index, parameters.get(index - 1));
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;
        }
        catch(SQLException e)
        {
            System.out.println("CAN'T RUN SQL SCRIPT:\n" + sqlScript + "\nERROR CODE:\n" + e.getMessage());
            return null;
        }
    }
    
    public static void createDB(Connection connection)
    {
        SqlWrapper.runSqlScript(connection, SqlScripts.createUsersTable);
        SqlWrapper.runSqlScript(connection, SqlScripts.createRolesTable);
        SqlWrapper.runSqlScript(connection, SqlScripts.createRelationTable);
        SqlWrapper.runSqlScript(connection, SqlScripts.createUsersRolesTable);
    }
    
    public static <InsertClass extends BasicSqlClassInterface<InsertClass> & RealSqlClassInterface> void insertData(Connection connection, String sqlScript, InsertClass insertClass)
    {
        try
        {
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sqlScript);
            insertClass.fillInsertParameters(preparedStatement);
            preparedStatement.execute();
        }
        catch (SQLException e)
        {
            System.out.println("CAN'T INSERT DATA. SCRIPT:\n" + sqlScript + "\nERROR CODE:\n" + e.getMessage());
        }
    }
    
    public static <OutputClass extends BasicSqlClassInterface<OutputClass>> Table<OutputClass> getData(Connection connection, String sqlScript, Class<OutputClass> outputClass)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlScript);
            ResultSet resultSet = preparedStatement.executeQuery();
            Table<OutputClass> table = new Table<>(); 
            
            while(resultSet.next())
            {
                OutputClass newRow = outputClass.getDeclaredConstructor().newInstance();
                newRow.mapFromResultSet(resultSet);
                table.addRow(newRow); 
            }
            return table;
        }
        catch(IllegalAccessException | NoSuchMethodException | InstantiationException | InvocationTargetException | SQLException e)
        {
            System.out.println("CAN'T GET DATA. SCRIPT:\n" + sqlScript + "\nERROR:\n" + Arrays.toString(e.getStackTrace()));
            return null;
        }
        
    }

    public static <OutputClass extends BasicSqlClassInterface<OutputClass>> Table<OutputClass> getData(Connection connection, String sqlScript, ArrayList<Object> parameters, Class<OutputClass> outputClass)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlScript);
            for(int index = 0; index < parameters.size(); index++)
            {
                preparedStatement.setObject(index + 1, parameters.get(index));
            }
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            Table<OutputClass> table = new Table<>(); 

            
            while(resultSet.next())
            {
                OutputClass newRow = outputClass.getDeclaredConstructor().newInstance();
                newRow.mapFromResultSet(resultSet);
                table.addRow(newRow);
            }
            return table;
        }
        catch(IllegalAccessException | NoSuchMethodException | InstantiationException | InvocationTargetException | SQLException  e)
        {
            System.out.println("CAN'T GET DATA. SCRIPT:\n" + sqlScript + "\nERROR:\n" + Arrays.toString(e.getStackTrace()));
            return null;
        }
        
    }
}
