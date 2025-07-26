package com.highfaev.resources.sql;

import java.util.ArrayList;

public class Table<TableClass extends BasicSqlInterface> {
    private ArrayList<TableClass> table = new ArrayList<TableClass>();
    public void setTable(ArrayList<TableClass> table)
    {
        this.table = table;
    }
    public void addRow(TableClass newRow)
    {
        this.table.add(newRow);
    }
    public ArrayList<TableClass> gettable()
    {
        return this.table;
    }
    public void printTable()
    {
        for(TableClass user: table)
        {
            user.printData();
        }
    } 
}
