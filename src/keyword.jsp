<%-- 
 @author: Manasi Bharde(msb4977), Prajakta Gaydhani(pag3862), Virtee Parekh(vvp2639)
 FileName: keyword.jsp
 Version: Java 1.8/ 11/25/2017
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <h1>Keyword wise news</h1>
    <body>
        <form name="queryData" action="newsList.jsp?type=3" method = POST>
        <input type="text" name="keyword" value="" />
        <input type="submit" value="Get News" name="submit"/>
    </form>
    </body>
</html>
