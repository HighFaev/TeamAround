package com.highfaev.resources.sql;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

public class Table<T extends BasicSqlClassInterface<T>> {
    @Setter @Getter private ArrayList<T> table = new ArrayList<T>();
    public void addRow(T newRow)
    {
        this.table.add(newRow);
    }
    public void printTable()
    {
        for(T user: table)
        {
            System.out.println(user);
        }
    } 
}
