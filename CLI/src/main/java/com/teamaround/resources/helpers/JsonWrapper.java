package com.teamaround.resources.helpers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class JsonWrapper {
    public static Gson gson = new Gson();
    public static <T> T mapFromJson(String json, Class<T> clazz)
    {
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
        return gson.toJson(obj);
    }
}
