package com.highfaev.resources.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReader {
    
    public static String readFile(String path)
    {
        try
        {
            return Files.readString(Path.of(path));
        }
        catch(IOException e)
        {
            System.out.println("ERROR WHILE TRYING TO READ FILE: " + e.getMessage());
            return null;
        }
    } 
    public static String readFileFromResources(String path)
    {
        try (InputStream is = FileReader.class.getClassLoader().getResourceAsStream(path))
        {
            if (is == null)
            {
                System.out.println("RESOURCES DIDNT FOUND: " + path);
                return null;
            }
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e){
            System.out.println("ERROR READING RESOURCES: " + e.getMessage());
            return null;
        }
    }
    
}
