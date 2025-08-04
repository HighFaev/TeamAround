package com.teamaround.resources.sql;

import java.util.ArrayList;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
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
