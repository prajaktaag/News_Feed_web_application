<%-- 
 @author: Manasi Bharde(msb4977), Prajakta Gaydhani(pag3862), Virtee Parekh(vvp2639)
 FileName: categoryAndSource.jsp
 Version: Java 1.8/ 11/25/2017
--%>

<%@page import="java.util.Set"%>
<%@page import="com.mycompany.hbasenews.QueryData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>News</title>
    </head>
    <body>
        <h1>Category and source wise news</h1>
    </body>
    <form name="queryData" action="newsList.jsp?type=2" method = POST>
        <select name="category">
            <option>World</option>
            <option>Sports</option>
            <option>Entertainment</option>
            <option>Technology</option>
            <option>Business</option>
            <option>Science</option>
        </select>
        <%
            QueryData queryData = new QueryData();
            Set<String> sources = queryData.getSources();
        %>
        <select name="source">
            <%for(String source: sources){%>
            <option><%=source%></option>
            <%}%>
        </select>
        <input type="submit" value="Get News" name="submit"/>
    </form>
</html>
