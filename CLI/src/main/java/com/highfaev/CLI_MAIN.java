package com.highfaev;

import java.sql.Connection;

import com.highfaev.resources.helpers.SqlWrapper;
import com.highfaev.resources.sql.*;
import com.highfaev.resources.helpers.CreateParametrsArray;

public class CLI_MAIN {
    private String databaseUrl = "jdbc:postgresql://localhost:5432/teamAround";
    private String username = "postgres";
    private String password = "1111";
    private Connection connection;
    public CLI_MAIN()
    {
        this.connection = SqlWrapper.connectToDatabase(this.databaseUrl, this.username, this.password);
        try {
            SqlScripts.initialize();
        } catch (Exception e) {
            System.out.println("Error while trying to initialize SqlScripts! " + e.getMessage());
        }
    }
    public static void main(String[] args)
    {
        if(args.length == 0) {
            System.out.println("Hi from teamAround team! Use --help argument to get info");
            return;
        }

        CLI_MAIN cliMain = new CLI_MAIN();
        switch (args[0]) {
            case "add-user":
                try {
                    cliMain.addUser(args);
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "create-db":
                cliMain.createDB(args);
                break;
            case "get-users":
                cliMain.getUsers(args);
                break;
            case "add-role":
                cliMain.addRole(args);
                break;
            case "get-roles":
                cliMain.getRoles(args);
                break;    
            case "get-user-roles":
                cliMain.getUserRoles(args);
                break;
            case "--help":
                cliMain.printInfo();
                break;
            default:
                System.out.println("Unknown first parametr. use --help argument to get info");
                break;
        }
    }
    private void printInfo()
    {
        System.out.println("add-user //Use to add user. Enter nickname, first name, last name, email, telegram(optional), bio(optional) in separate lines\n"+
                            "create-db //Use to create database on your postgresql server.\n"+
                            "get-users //Use to get all data from users table\n"+
                            "add-role //Use to add role. Enter name");
    }
    private void addUser(String[] args) throws Exception
    {
        SqlWrapper.insertData(this.connection, SqlScripts.insertToUsers, new User().create(args));
    }
    private void createDB(String[] args)
    {
        SqlWrapper.createDB(connection);
    }
    private void getUsers(String[] args)
    {
        SqlWrapper.getData(connection, SqlScripts.getAllUsers, User.class).printTable();
    }
    private void getUserRoles(String[] args)
    {
        SqlWrapper.getData(connection, SqlScripts.getUserRoles, CreateParametrsArray.createParametrsArray(args) , UserRole.class).printTable();
    }
    private void addRole(String[] args)
    {
        SqlWrapper.insertData(connection, SqlScripts.insertToRoles, new Role().create(args));
    }
    private void getRoles(String[] args)
    {
        SqlWrapper.getData(connection, SqlScripts.getAllRoles, Role.class).printTable();
    }
}