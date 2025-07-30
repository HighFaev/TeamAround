package com.highfaev.resources.helpers;

import java.sql.Connection;

import com.highfaev.resources.settings.Settings;
import com.highfaev.resources.sql.SqlScripts;
import com.highfaev.resources.sql.joined_sql_classes.RelationNicknames;
import com.highfaev.resources.sql.joined_sql_classes.UserRole;
import com.highfaev.resources.sql.real_sql_classes.Relation;
import com.highfaev.resources.sql.real_sql_classes.Role;
import com.highfaev.resources.sql.real_sql_classes.User;

import lombok.Getter;
import lombok.Setter;

public class CliCommandHandler {
    @Getter @Setter private Connection connection;
    @Getter @Setter private Settings settings;
    public CliCommandHandler()
    {
        this.settings = JsonWrapper.mapFromJson(FileReader.readFileFromResources("settings/settings.json"), Settings.class);
        this.connection = SqlWrapper.connectToDatabase(this.settings.getDatabaseUrl(), this.settings.getUsername(), this.settings.getPassword());
    }
    public void addUser(String[] args)
    {
        SqlWrapper.insertData(this.connection, SqlScripts.insertToUsers, new User().create(args));
    }
    public void createDB(String[] args)
    {
        SqlWrapper.createDB(this.connection);
    }
    public void getUsers(String[] args)
    {
        SqlWrapper.getData(this.connection, SqlScripts.getAllUsers, User.class).printTable();
    }
    public void getUserRoles(String[] args)
    {
        SqlWrapper.getData(this.connection, SqlScripts.getUserRoles, CreateParametrsArray.createParametrsArray(args) , UserRole.class).printTable();
    }
    public void addRole(String[] args)
    {
        SqlWrapper.insertData(this.connection, SqlScripts.insertToRoles, new Role().create(args));
    }
    public void getRoles(String[] args)
    {
        SqlWrapper.getData(this.connection, SqlScripts.getAllRoles, Role.class).printTable();
    }
    public void addRelation(String[] args)
    {
        SqlWrapper.insertData(this.connection, SqlScripts.insertToRelation, new Relation().create(args));
    }
    public void getRelations(String[] args)
    {
        SqlWrapper.getData(this.connection, SqlScripts.getRelations, CreateParametrsArray.createParametrsArray(args), RelationNicknames.class).printTable();
    }
    public void printInfo()
    {
        System.out.println("""
                           create-db //Use to create database on your postgresql server.
                           add-user //Use to add user. Enter nickname, first name, last name, email, telegram(optional), bio(optional) in separate lines
                           get-users //Use to get all data from users table
                           add-role //Use to add role. Enter name
                           get-roles //Use to get all roles.
                           get-user-roles //Use to get roles for user. Enter user_id
                           get-relations //Use to get relations for user. Enter user_id
                           """);
    }
}
