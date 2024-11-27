<%-- 
    Document   : formulario.jsp
    Created on : Nov 27, 2024, 11:34:57 AM
    Author     : ubuntu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Apuestas</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div><h1>Haz tu apuesta</h1></div>
        <form action="apuestasServlet" method="post">
            Nombre:
            <input type="text" name="nombre"/>
            <br>
            Partido:
            <input type="text" name="partido"/>
            <br>
            Fecha:
            <input type="text" name="fecha"/>
            <br>
            Resultado:
            <input type="text" name="resultado"/>
            <br>
            Dinero:
            <input type="text" name="dinero"/>
            <br>
            <input type="submit" value="Enviar"/>
        </form>
    </body>
</html>
