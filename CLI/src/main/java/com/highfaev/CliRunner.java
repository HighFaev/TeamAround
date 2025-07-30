package com.highfaev;

import com.highfaev.resources.helpers.CliCommandHandler;
import com.highfaev.resources.sql.SqlScripts;

public class CliRunner {
    private final CliCommandHandler cliCommandHandler;

    public CliRunner()
    {
        this.cliCommandHandler = new CliCommandHandler();
        try {
            SqlScripts.initialize();
        } catch (Exception e) {
            System.out.println("Error while trying to initialize SqlScripts! " + e.getMessage());
        }
    }
    public void runCliProgram(String[] args)
    {
        if(args.length == 0) {
            System.out.println("Hi from teamAround team! Use --help argument to get info");
            return;
        }

        switch (args[0]) {
            case "create-db" -> cliCommandHandler.createDB(args);
            case "add-user" -> cliCommandHandler.addUser(args);
            case "get-users" -> cliCommandHandler.getUsers(args);
            case "add-role" -> cliCommandHandler.addRole(args);
            case "get-roles" -> cliCommandHandler.getRoles(args);
            case "get-user-roles" -> cliCommandHandler.getUserRoles(args);
            case "add-relation" -> cliCommandHandler.addRelation(args);
            case "get-relations" -> cliCommandHandler.getRelations(args);
            case "--help" -> cliCommandHandler.printInfo();
            default -> System.out.println("Unknown first parametr. use --help argument to get info");
        }
    }
}
