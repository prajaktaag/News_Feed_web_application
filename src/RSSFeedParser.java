package com.mycompany.hbasenews;

/***
 * @author: Manasi Bharde(msb4977), Prajakta Gaydhani(pag3862), Virtee Parekh(vvp2639)
 * @reference: http://www.vogella.com/tutorials/RSSFeed/article.html
 * FileName: RSSFeedParser.java
 * Version: Java 1.8/ 11/03/2017
 */


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

/**
 * This program parses the XML file of the news feeds
 * Reference: http://www.vogella.com/tutorials/RSSFeed/article.html
 */
public class RSSFeedParser {
    static final String TITLE = "title";
    static final String CATEGORY = "category";
    static final String LINK = "link";
    static final String ITEM = "item";
    static final String PUBDATE = "pubDate";


    final URL url;

    public RSSFeedParser(String feedUrl) {
        try {
            this.url = new URL(feedUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public NewsFeed readFeed() {
        NewsFeed feed = null;
        try {
            boolean isFeedHeader = true;
            // Set header values intial to the empty string
            String category = "";
            String title = "";
            String link = "";
            String pubDate = "";

            // First create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = read();
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    String localPart = event.asStartElement().getName()
                            .getLocalPart();
                    switch (localPart) {
                        //root tag of the XML file
                        case ITEM:
                            if (isFeedHeader) {
                                isFeedHeader = false;
                                feed = new NewsFeed(title, link, category, pubDate);
                            }
                            event = eventReader.nextEvent();
                            break;
                        case TITLE:
                            title = getCharacterData(event, eventReader);
                            break;
                        case CATEGORY:
                            category = getCharacterData(event, eventReader);
                            break;
                        case LINK:
                            link = getCharacterData(event, eventReader);
                            break;
                        case PUBDATE:
                            pubDate = getCharacterData(event, eventReader);
                            break;
                    }
                } else if (event.isEndElement()) {
                    if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
                        NewsFeedMessage message = new NewsFeedMessage();
                        message.setCategory(category);
                        message.setLink(link);
                        message.setTitle(title);
                        message.setPubDate(pubDate);
                        feed.getMessages().add(message);
                        event = eventReader.nextEvent();
                        continue;
                    }
                }
            }
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
        return feed;
    }

    private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
            throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }

    private InputStream read() {
        try {
            return url.openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}