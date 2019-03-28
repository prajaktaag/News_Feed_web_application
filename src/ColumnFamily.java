/** 
 @author: Manasi Bharde(msb4977), Prajakta Gaydhani(pag3862), Virtee Parekh(vvp2639)
 FileName: columnFamily.java
 Version: Java 1.8/ 11/25/2017
**/


package com.mycompany.hbasenews;

public class ColumnFamily {
    public static String[] columnFamilies = new String[6];
    
    static {
        ColumnFamily.columnFamilies[0] = "World";
        ColumnFamily.columnFamilies[1] = "Business";
        ColumnFamily.columnFamilies[2] = "technology";
        ColumnFamily.columnFamilies[3] = "Entertainment";
        ColumnFamily.columnFamilies[4] = "Sports";
        ColumnFamily.columnFamilies[5] = "Science";
    }
}
