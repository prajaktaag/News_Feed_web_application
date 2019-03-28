package com.mycompany.hbasenews;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author manasi
 */
public class News{
    
    public String title;
    public String url;
    public  String date;

    public News(String title, String url, String date){
        this.title = title;
        this.date = date;
        this.url = url;
    }

}
