/** 
 @author: Manasi Bharde(msb4977), Prajakta Gaydhani(pag3862), Virtee Parekh(vvp2639)
 FileName: QueryData.java
 Version: Java 1.8/ 11/25/2017
**/

package com.mycompany.hbasenews;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import org.apache.hadoop.hbase.filter.SubstringComparator;

public class QueryData {

    Connection conn = null;
    Table table = null;
    
    public QueryData(){
        init();
    }

    public void init(){
        String localhost = "127.0.0.1";
        String port =  "2181";
        Configuration config_details = HBaseConfiguration.create();
        config_details.set("hbase.zookeeper.quorum", localhost);
        config_details.set("hbase.zookeeper.property.clientPort", port);

        String tableName = "NewsFeeds";



        try {
            conn = ConnectionFactory.createConnection(config_details);
            table = conn.getTable(TableName.valueOf(tableName));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<News> getNews(String category){
        byte[] family = Bytes.toBytes(category.toUpperCase());
        byte[] qual = Bytes.toBytes("title");
        Scan scan = new Scan();
        scan.addColumn(family, qual);
        scan.addColumn(family,Bytes.toBytes("link"));
        scan.addColumn(family,Bytes.toBytes("date"));
        ResultScanner rs = null;
        List<News> news = new ArrayList<>();
        try {
            rs = table.getScanner(scan);
            for (Result r = rs.next(); r != null; r = rs.next()) {
                byte[] valueObj = r.getValue(Bytes.toBytes(category.toUpperCase()), Bytes.toBytes("title"));
                byte[] urlObj = r.getValue(Bytes.toBytes(category.toUpperCase()), Bytes.toBytes("link"));
                byte[] dateObj = r.getValue(Bytes.toBytes(category.toUpperCase()), Bytes.toBytes("date"));
                String title = new String(valueObj);
                String url = new String(urlObj);
                String date = new String(dateObj);
                System.out.println(title);
                News item = new News(title, url, date);
                news.add(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return news;
    }
    
    public Set<String> getSources(){
        Set<String> sources = new HashSet<>();
        for(String category: ColumnFamily.columnFamilies){
            byte[] family = Bytes.toBytes(category.toUpperCase());
            byte[] qual = Bytes.toBytes("source");
            Scan scan = new Scan();
            scan.addColumn(family, qual);
            try {
                ResultScanner rs = table.getScanner(scan);
                for (Result r = rs.next(); r != null; r = rs.next()) {
                    byte[] valueObj = r.getValue(family, qual);
                    String value = new String(valueObj);
                    sources.add(value);
                //    System.out.println(value);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sources;
    }

    public static void main(String[] args){
        System.out.println("Enter category");
        Scanner scanner = new Scanner(System.in);
        String category = scanner.nextLine();
        QueryData query = new QueryData();
        query.init();
        query.getNews(category.toUpperCase());
    }

    public List<News> getRecordsFiltered(String columnFamily, String column, String value){
        SingleColumnValueFilter filter1 = new SingleColumnValueFilter(
                columnFamily.getBytes(),
                column.getBytes(),
                CompareFilter.CompareOp.EQUAL,
                Bytes.toBytes(value)
        );
        Scan scan = new Scan();
        scan.setFilter(filter1);
    //    scan.addColumn(columnFamily.getBytes(), Bytes.toBytes("title"));
    //    scan.addColumn(columnFamily.getBytes(),Bytes.toBytes("link"));
    //    scan.addColumn(columnFamily.getBytes(),Bytes.toBytes("date"));
        //scan.addFamily(columnFamily.getBytes());
        List<News> news = new ArrayList<>();
        System.out.println(columnFamily+" "+value);
        try {
            ResultScanner rs = table.getScanner(scan);
            for (Result r = rs.next(); r != null; r = rs.next()) {
                if(r.containsColumn(Bytes.toBytes(columnFamily), Bytes.toBytes("title"))){
                    byte[] valueObj = r.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes("title"));
                    byte[] urlObj = r.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes("link"));
                    byte[] dateObj = r.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes("date"));
                    String title = new String(valueObj);
                    String url = new String(urlObj);
                    String date = new String(dateObj);
                    System.out.println(title);
                    News item = new News(title, url, date);
                    news.add(item);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return news;
    }

    public List<News> getByKeyword(String keyword){
        List<News> news = new ArrayList<>();
        for(String family: ColumnFamily.columnFamilies) {
            family = family.toUpperCase();
            SubstringComparator comp = new SubstringComparator(keyword);   // looking for 'my value'
            SingleColumnValueFilter filter = new SingleColumnValueFilter(
                    family.getBytes(),
                    "title".getBytes(),
                    CompareFilter.CompareOp.EQUAL,
                    comp
            );
            Scan scan = new Scan();
            scan.setFilter(filter);
            scan.addColumn(family.getBytes(),Bytes.toBytes("title"));
            scan.addColumn(family.getBytes(),Bytes.toBytes("link"));
            scan.addColumn(family.getBytes(),Bytes.toBytes("date"));
            try {
                ResultScanner rs = table.getScanner(scan);
                for (Result r = rs.next(); r != null; r = rs.next()) {
                    if(r.containsColumn(Bytes.toBytes(family), Bytes.toBytes("title"))){
                        byte[] valueObj = r.getValue(Bytes.toBytes(family), Bytes.toBytes("title"));
                        byte[] urlObj = r.getValue(Bytes.toBytes(family), Bytes.toBytes("link"));
                        byte[] dateObj = r.getValue(Bytes.toBytes(family), Bytes.toBytes("date"));
                        String title = new String(valueObj);
                        String url = new String(urlObj);
                        String date = new String(dateObj);
                        System.out.println(title);
                        News item = new News(title, url, date);
                        news.add(item);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return news;
    }
}

