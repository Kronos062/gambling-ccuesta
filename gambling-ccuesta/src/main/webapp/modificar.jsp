<%-- 
    Document   : modificar.jsp
    Created on : Dec 4, 2024, 1:43:04 PM
    Author     : ubuntu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ca">
    <head>
        <meta http-equiv="Content-Type" name="viewport" content="text/html; charset=UTF-8">
        <title>Modificar Apuesta</title>
    </head>
    <body>
        <div><h1>Modificar Apuesta</h1></div>
        <%
            String indexParam = request.getParameter("index");
            int index = Integer.parseInt(indexParam);
        %>  
    </body>
</html>
