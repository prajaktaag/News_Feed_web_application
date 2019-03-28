<%-- 
 @author: Manasi Bharde(msb4977), Prajakta Gaydhani(pag3862), Virtee Parekh(vvp2639)
 FileName: newsList.jsp
 Version: Java 1.8/ 11/25/2017
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.hbasenews.*"%>
<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>News</title>
    </head>
    <h1>
        <b> <i> News Application using HBase </i> </b>
    </h1>
    <body>
        
    <%
        QueryData queryData = new QueryData();
        List<News> newsList = null;
        switch(Integer.parseInt(request.getParameter("type"))){
            case 1:{
                String category = request.getParameter("category");
                newsList = queryData.getNews(category.toUpperCase());
                break;
            }
            case 2:{
                String category = request.getParameter("category");
                String source = request.getParameter("source");
                newsList = queryData.getRecordsFiltered(category.toUpperCase(),"source",source);
                break;
            }
            case 3:{
                String keyword = request.getParameter("keyword");
                newsList = queryData.getByKeyword(keyword);
                break;
            }
        }
        if(newsList.size() == 0){%>
            <h2>No news to display</h2>
        <% }else {
        %>
            <table border="1" cellpadding="1">
            <thead>
                <tr>
                    <th>NEWS </th><th>Date </th>
                </tr>
            </thead>
            <tbody>
                <% for(News news: newsList){%>
                  <TR>
                      
                        <TD>
                        <a href="<%=news.url%>">
                        <%=news.title%></a>
                        </TD>
                      <TD>
                      <%=news.date%>
                      </TD>
                  </TR>
                <% }  %>
            </tbody>
      </table>
            <%
            }
            %>
    </body>
