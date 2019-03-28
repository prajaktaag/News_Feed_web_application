package com.mycompany.hbasenews;

/***
 * @author: Manasi Bharde(msb4977), Prajakta Gaydhani(pag3862), Virtee Parekh(vvp2639)
 * FileName: NewsFeed.java
 * Version: Java 1.8/ 11/03/2017
 */

import java.util.ArrayList;
import java.util.List;

/**
 * This class creates news feeds
 */

public class NewsFeed {

    final String title;
    final String link;
    final String category;
    final String pubDate;


    final List<NewsFeedMessage> newsEntry = new ArrayList<NewsFeedMessage>();

    public String getPubDate() {
        return pubDate;
    }

    //constructor for the class
    public NewsFeed(String title, String link, String category, String pub_date) {
        this.title = title;
        this.link = link;

        this.category = category;
        this.pubDate = pub_date;
    }

    /**
     * This function gets every news feed
     * @return: each news entry
     */
    public List<NewsFeedMessage> getMessages() {
        return newsEntry;
    }

    /**
     * This function gets the title of the news feed
     * @return: title of the news
     */
    public String getTitle() {

        return title;
    }

    /**
     * This function gets the link of the news feed
     * @return: link of the news
     */
    public String getLink() {

        return link;
    }

    /**
     * This function gets the category of the news feed
     * @return: category of the news
     */
    public String getCategory() {

        return category;
    }

    @Override
    public String toString() {

        return "NewsFeed [title= " + title + "category=" +category+ "link= " + link+ "date="+ pubDate + "]";
    }

}