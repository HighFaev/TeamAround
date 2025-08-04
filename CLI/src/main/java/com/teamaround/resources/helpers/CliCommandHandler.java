package com.teamaround.resources.helpers;

import java.sql.Connection;

import com.teamaround.resources.settings.Settings;
import com.teamaround.resources.sql.SqlScripts;
import com.teamaround.resources.sql.joined_sql_classes.RelationNicknames;
import com.teamaround.resources.sql.joined_sql_classes.UserRole;
import com.teamaround.resources.sql.real_sql_classes.Relation;
import com.teamaround.resources.sql.real_sql_classes.Role;
import com.teamaround.resources.sql.real_sql_classes.User;

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
        SqlWrapper.getData(this.connection, SqlScripts.getAllUsers, CreateParametersArray.createParametersArray(new String[0]), User.class).printTable();
    }
    public void getUserRoles(String[] args)
    {
        SqlWrapper.getData(this.connection, SqlScripts.getUserRoles, CreateParametersArray.createParametersArray(args) , UserRole.class).printTable();
    }
    public void addRole(String[] args)
    {
        SqlWrapper.insertData(this.connection, SqlScripts.insertToRoles, new Role().create(args));
    }
    public void getRoles(String[] args)
    {
        SqlWrapper.getData(this.connection, SqlScripts.getAllRoles, CreateParametersArray.createParametersArray(new String[0]), Role.class).printTable();
    }
    public void addRelation(String[] args)
    {
        SqlWrapper.insertData(this.connection, SqlScripts.insertToRelation, new Relation().create(args));
    }
    public void getRelations(String[] args)
    {
        SqlWrapper.getData(this.connection, SqlScripts.getRelations, CreateParametersArray.createParametersArray(args), RelationNicknames.class).printTable();
    }
    public void printInfo()
    {
        System.out.println("""
                           create-db //Use to create database on your postgresql server.
                           add-user //Use to add user. Provide nickname, first name, last name, email, telegram(optional), bio(optional).
                           get-users //Use to get all data from users table.
                           add-role //Use to add role. Provide name for the role.
                           get-roles //Use to get all roles.
                           get-user-roles //Use to get roles for user. Provide user id.
                           add-relation //Use to add relation. Provide parent user id and children user id.
                           get-relations //Use to get relations for user. Provide user id.
                           """);
    }
}
