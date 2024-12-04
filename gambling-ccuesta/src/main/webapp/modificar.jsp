<%-- 
    Document   : modificar.jsp
    Created on : Dec 4, 2024, 1:43:04 PM
    Author     : ubuntu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
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

            ServletContext context = getServletContext();
            List<String> apuestas = (List<String>) context.getAttribute("apuestas");

            if (index >= 0 && index < apuestas.size()) {
                String apuesta = apuestas.get(index);
                String[] datosApuesta = apuesta.replaceAll("[\\[\\]]", "").split("\\]\\[");
                String nombre = datosApuesta[0];
                String partido = datosApuesta[1];
                String fecha = datosApuesta[2];
                String resultado = datosApuesta[3];
                String dinero = datosApuesta[4];
        %>  
        } else {
        <%    
            <p>Índice de apuesta no válido.</p>
            <a href="apuestasServlet"> Volver a la lista de apuestas</a >
        %>
        <%
            }
        %>
    </body>
</html>
