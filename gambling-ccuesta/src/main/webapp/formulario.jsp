<%-- 
    Document   : formulario.jsp
    Created on : Nov 27, 2024, 11:34:57 AM
    Author     : ubuntu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div>Haz tu apuesta</div>
        <form action="parametros.jsp">
            Nombre:
            <input type="text" name="nombre"/>
            Apuesta:
            <input type="text" name="apuesta"/>
            Resultado:
            <input type="text" name="resultado"/>
            Dinero:
            <input type="text" name="dinero"/>
            <input type="submit" value="Enviar"/>
        </form>
    </body>
</html>
