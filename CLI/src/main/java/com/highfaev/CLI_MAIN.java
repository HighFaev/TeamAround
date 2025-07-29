package com.highfaev;

import java.sql.Connection;

import com.highfaev.resources.helpers.SqlWrapper;
import com.highfaev.resources.sql.*;
import com.highfaev.resources.sql.joined_sql_classes.RelationNicknames;
import com.highfaev.resources.sql.joined_sql_classes.UserRole;
import com.highfaev.resources.sql.real_sql_classes.Relation;
import com.highfaev.resources.sql.real_sql_classes.Role;
import com.highfaev.resources.sql.real_sql_classes.User;
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
            case "create-db":
                cliMain.createDB(args);
                break;
            case "add-user":
                cliMain.addUser(args);
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
            case "add-relation":
                cliMain.addRelation(args);
                break;
            case "get-relations":
                cliMain.getRelations(args);
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
        System.out.println("create-db //Use to create database on your postgresql server.\n"+
                            "add-user //Use to add user. Enter nickname, first name, last name, email, telegram(optional), bio(optional) in separate lines\n"+                   
                            "get-users //Use to get all data from users table\n"+
                            "add-role //Use to add role. Enter name\n"+
                            "get-roles //Use to get all roles.\n"+
                            "get-user-roles //Use to get roles for user. Enter user_id\n"+
                            "get-relations //Use to get relations for user. Enter user_id\n");
    }
    private void addUser(String[] args)
    {
        SqlWrapper.insertData(this.connection, SqlScripts.insertToUsers, new User().create(args));
    }
    private void createDB(String[] args)
    {
        SqlWrapper.createDB(this.connection);
    }
    private void getUsers(String[] args)
    {
        SqlWrapper.getData(this.connection, SqlScripts.getAllUsers, User.class).printTable();
    }
    private void getUserRoles(String[] args)
    {
        SqlWrapper.getData(this.connection, SqlScripts.getUserRoles, CreateParametrsArray.createParametrsArray(args) , UserRole.class).printTable();
    }
    private void addRole(String[] args)
    {
        SqlWrapper.insertData(this.connection, SqlScripts.insertToRoles, new Role().create(args));
    }
    private void getRoles(String[] args)
    {
        SqlWrapper.getData(this.connection, SqlScripts.getAllRoles, Role.class).printTable();
    }
    private void addRelation(String[] args)
    {
        SqlWrapper.insertData(this.connection, SqlScripts.insertToRelation, new Relation().create(args));
    }
    private void getRelations(String[] args)
    {
        SqlWrapper.getData(this.connection, SqlScripts.getRelations, CreateParametrsArray.createParametrsArray(args), RelationNicknames.class).printTable();
    }
}