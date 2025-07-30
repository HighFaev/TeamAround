package com.highfaev.resources.helpers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class JsonWrapper {
    public static <T> T mapFromJson(String json, Class<T> clazz)
    {
        Gson gson = new Gson();
        try
        {
            return gson.fromJson(json, clazz);
        }
        catch(JsonSyntaxException e)
        {
            System.out.println("ERROR WHILE TRYING TO PARSE FROM JSON + " + e.getMessage());
            return null;
        }
        
    }
    public static <T> String mapToJson(T obj)
    {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
}
