package com.mycompany.hbasenews;

/***
 * @author: Manasi Bharde(msb4977), Prajakta Gaydhani(pag3862), Virtee Parekh(vvp2639)
 * FileName: NewsFeedMessage.java
 * Version: Java 1.8/ 11/03/2017
 */

/**
 * This class sperates News Feeds fields
 */
public class NewsFeedMessage {

    String title;
    String category;
    String link;
    String pubDate;

    /**
     * This functions gets the title of the News
     * @return: the tile of the news
     */
    public String getTitle() {
        return title;
    }

    /**
     * This functions sets the title of the News
     * @return: none
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This functions gets the link of a particular News
     * @return: the link of the news
     */
    public String getLink() {
        return link;
    }

    /**
     * This functions sets the link of a particular News
     * @return: none
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * This functions gets the category of the News
     * @return: the category of the news
     */
    public String getCategory() {
        return category;
    }

    /**
     * This functions sets the category of the News
     * @return: none
     */
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "FeedMessage [title=" + title + ", category=" + category
                + ", link=" + link +"pubDate"+pubDate+ "]";
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
}