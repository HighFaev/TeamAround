package com.highfaev.resources.sql;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;


public class SqlScripts {
    public static String createUsersTable;
    public static String createRolesTable;
    public static String createUsersRolesTable;
    public static String createUsersTreeTable;
    public static String insertToUsers;
    public static String getAllUsers;
    public static String insertToRoles;
    public static String getUsersRoles;
    public static void initialize() throws Exception
    {
        Field[] fields = SqlScripts.class.getDeclaredFields();
        for(Field field: fields)
        {
            String pathToFile = "sql/scripts/" + field.getName() + ".sql";
            InputStream in = SqlScripts.class.getClassLoader().getResourceAsStream(pathToFile);

            if (in == null) {
                System.out.println("Cannt find file: " + pathToFile);
                continue;
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                List<String> lines = reader.lines().collect(Collectors.toList());
                String script = String.join(" ", lines);
                field.set(null, script);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
