<%-- 
    Document   : apuestasGanadoras
    Created on : Dec 18, 2024, 1:33:08 PM
    Author     : ubuntu
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.cristian.gambling.ccuesta.Apuesta" %>
<!DOCTYPE html>
<html>
<head>
    <title>Apuestas Ganadoras</title>
</head>
<body>
    <h1>Apuestas Ganadoras</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Partido</th>
            <th>Fecha</th>
            <th>Resultado</th>
            <th>Dinero</th>
            <th>Competición</th>
        </tr>
        <%
        List<Apuesta> apuestas = (List<Apuesta>) application.getAttribute("apuestas");
        if (apuestas != null) {
            for (Apuesta apuesta : apuestas) {
                if (apuesta.isApuestaGanadora()) {
        %>
        <tr>
            <td><%= apuesta.getId() %></td>
            <td><%= apuesta.getNombre() %></td>
            <td><%= apuesta.getPartido() %></td>
            <td><%= apuesta.getFecha() %></td>
            <td><%= apuesta.getResultado() %></td>
            <td><%= apuesta.getDinero() %></td>
            <td><%= apuesta.getCompeticion() %></td>
        </tr>
        <%
                }
            }
        }
        %>
    </table>
    <a href="listaApuestas.jsp">Volver a la lista completa</a>
</body>
</html>

