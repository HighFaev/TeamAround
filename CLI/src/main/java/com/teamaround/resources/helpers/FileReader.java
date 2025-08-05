package com.teamaround.resources.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FileReader {
    public static String readFileFromResources(String path)
    {
        try (InputStream is = FileReader.class.getClassLoader().getResourceAsStream(path))
        {
            if (is == null)
            {
                System.out.println("RESOURCES NOT FOUND: " + path);
                return null;
            }
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e){
            System.out.println("ERROR READING RESOURCES: " + e.getMessage());
            return null;
        }
    }
    
}
