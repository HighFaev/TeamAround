package com.highfaev;

import java.sql.Connection;

import com.highfaev.resources.helpers.SqlWrapper;
import com.highfaev.resources.sql.*;

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
                            "get-users //Use to get all data from users table" );
    }
    private void addUser(String[] args) throws Exception
    {
        SqlWrapper.insertData(this.connection, SqlScripts.insertToUsers, User.create(args));
    }
    private void createDB(String[] args)
    {
        SqlWrapper.runSqlScript(this.connection, SqlScripts.createUsersTable);
    }
    private void getUsers(String[] args)
    {
        SqlWrapper.getData(connection, SqlScripts.getAllUsers, User.class).printTable();
    }
}