package com.teamaround.resources.helpers;

import java.util.ArrayList;

public class CreateParametersArray {
    public static ArrayList<Object> createParametersArray(String[] args)
    {
        ArrayList<Object> parameters = new ArrayList<>();
        for(int index = 1; index < args.length; index++)
        {
            parameters.add(
                checkIfInteger(args[index]) ? Integer.valueOf(args[index]) : args[index]);
        }
        return parameters;
    }

    public static boolean checkIfInteger(String s)
    {
        try {
            Integer.valueOf(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
