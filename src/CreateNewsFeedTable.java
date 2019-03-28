package com.mycompany.hbasenews;

/***
 * @author: Manasi Bharde(msb4977), Prajakta Gaydhani(pag3862), Virtee Parekh(vvp2639)
 * FileName: CreateNewsFeedTable.java
 * Version: Java 1.8/ 11/03/2017
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

/**
 * This program creates the table named NewsFeed.
 */
public class CreateNewsFeedTable {

    
    public static void main(String[] args) {
        /**
         * Main entry of the program
         */
        CreateNewsFeedTable table = new CreateNewsFeedTable();
        //call to create table method
        table.createNewsDataTable();
        InsertNewsFeed insert_data = new InsertNewsFeed();
        //call to insert function
        insert_data.insertRecords();
    }

    public void createNewsDataTable() {
        String localhost = "127.0.0.1";
        String port =  "2181";
        Configuration config_details = HBaseConfiguration.create();
        config_details.set("hbase.zookeeper.quorum", localhost);
        config_details.set("hbase.zookeeper.property.clientPort", port);

        Connection conn = null;
        Admin admin_user = null;

        try {
            conn = ConnectionFactory.createConnection(config_details);
            admin_user = conn.getAdmin();
            String tableName = "NewsFeeds";
            if (!admin_user.isTableAvailable(TableName.valueOf(tableName))) {
                HTableDescriptor hbase_table_descpt = new HTableDescriptor(String.valueOf(TableName.valueOf(tableName)));

                for(String columnFamily: ColumnFamily.columnFamilies){
                    System.out.println(columnFamily);
                    hbase_table_descpt.addFamily(new HColumnDescriptor(columnFamily.toUpperCase()));
                }
                admin_user.createTable(hbase_table_descpt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (admin_user != null) {
                    admin_user.close();
                }
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}



/*
import java.io.IOException;

import org.apache.hadoop.hbase.*;

import org.apache.hadoop.conf.Configuration;

public class tablecreate {

public static void main(String[] args) throws IOException {

Configuration c = HBaseConfiguration.create(); // Instantiate configuration class

HBaseAdmin ad = new HBaseAdmin(c);      // Instantiate HbaseAdmin class

// Instantiate table descriptor class

HTableDescriptor tdescriptor = new TableDescriptor(TableName.valueOf("student"));

tdescriptor.addFamily(new HColumnDescriptor("id"));  // Add column families to tdescriptor

 ad.createTable(tdescriptor);   // Execute table using ad

System.out.println(" Table is created "); // It will print “Table is created”

}

}*/

