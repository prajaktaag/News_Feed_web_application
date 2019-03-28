package com.mycompany.hbasenews;

/***
 * @author: Manasi Bharde(msb4977), Prajakta Gaydhani(pag3862), Virtee Parekh(vvp2639)
 * FileName: InsertNewsFeed.java
 * Version: Java 1.8/ 11/03/2017
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * This function insert the neews feeds into the table newsfeeds
 */
public class InsertNewsFeed {

    public void insertRecords() {
        String localhost = "127.0.0.1";
        String port =  "2181";
        Configuration config_details = HBaseConfiguration.create();
        config_details.set("hbase.zookeeper.quorum", localhost);
        config_details.set("hbase.zookeeper.property.clientPort", port);

        String tableName = "NewsFeeds";

        Connection conn = null;
        Table table = null;

        try {
            conn = ConnectionFactory.createConnection(config_details);
            table = conn.getTable(TableName.valueOf(tableName));
            int rowKey = 0;
            for(String columnFamily: ColumnFamily.columnFamilies) {
                // reads data from google news api
                RSSFeedParser RSSparser = new RSSFeedParser(
                        "https://news.google.com/news/rss/headlines/section/topic/"+columnFamily.toUpperCase()+"?ned=us&hl=en&gl=US");
                NewsFeed feed = RSSparser.readFeed();
                String[][] newsData = new String[feed.getMessages().size()][5];
                int i = 0;
                // stores data obtained from google news into a 2d string
                for (NewsFeedMessage message : feed.getMessages()) {
                    newsData[i][0] = rowKey + " ";
                    newsData[i][1] = message.category;
                    newsData[i][2] = message.title;
                    newsData[i][3] = message.link;
                    newsData[i][4] = message.pubDate;
                    rowKey++;
                    i = i + 1;
                }

                for (i = 0; i < newsData.length; i++) {
                    //insert the data into tables.

                    Put news = new Put(Bytes.toBytes(newsData[i][0]));
                    news.addColumn(Bytes.toBytes(newsData[i][1].toUpperCase()), Bytes.toBytes("title"), Bytes.toBytes(newsData[i][2]));
                    news.addColumn(Bytes.toBytes(newsData[i][1].toUpperCase()), Bytes.toBytes("link"), Bytes.toBytes(newsData[i][3]));
                    news.addColumn(Bytes.toBytes(newsData[i][1].toUpperCase()), Bytes.toBytes("date"), Bytes.toBytes(newsData[i][4]));
                    news.addColumn(Bytes.toBytes(newsData[i][1].toUpperCase()), Bytes.toBytes("source"), Bytes.toBytes(getDomainName(newsData[i][3])));
                    table.put(news);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (table != null) {
                    table.close();
                }

                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static String getDomainName(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String domain = uri.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }
}
