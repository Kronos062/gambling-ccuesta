<%-- 
    Document   : filtroAvanzado
    Created on : Dec 18, 2024, 1:16:35 PM
    Author     : ubuntu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.cristian.gambling.ccuesta.Apuesta"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Filtro por rango de dinero y usuario</title>
    </head>
    <body>
        <h1>Filtro por rango de dinero y usuario</h1>
        <form action="apuestasServlet" method="get">
            <input type="hidden" name="action" value="filtroAvanzado">
            <label for="usuario">Usuario:</label>
            <input type="text" id="usuario" name="usuario" value="${param.usuario}"><br><br>
            <label for="minApuesta">Apuesta minima:</label>
            <input type="number" id="minApuesta" name="minApuesta" min="0" value="${param.minApuesta}"><br><br>
            <label for="maxApuesta">Apuesta maxima:</label>
            <input type="number" id="maxApuesta" name="maxApuesta" min="0" value="${param.maxApuesta}"><br><br>
            <input type="submit" value="Filtrar">
        </form>

        <%
            List<Apuesta> apuestasFiltradas = (List<Apuesta>) request.getAttribute("apuestasFiltradas");
            if (apuestasFiltradas != null && !apuestasFiltradas.isEmpty()) {
        %>
        <h2>Resultados del filtro:</h2>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Partido</th>
                <th>Fecha</th>
                <th>Resultado</th>
                <th>Dinero</th>
                <th>Competición</th>
                <th>Apuesta Ganadora</th>
            </tr>
            <% for (Apuesta apuesta : apuestasFiltradas) {%>
            <tr>
                <td><%= apuesta.getId()%></td>
                <td><%= apuesta.getNombre()%></td>
                <td><%= apuesta.getPartido()%></td>
                <td><%= apuesta.getFecha()%></td>
                <td><%= apuesta.getResultado()%></td>
                <td><%= apuesta.getDinero()%></td>
                <td><%= apuesta.getCompeticion()%></td>
                <td><%= apuesta.isApuestaGanadora() ? "Sí" : "No"%></td>
            </tr>
            <% } %>
        </table>
        <% } else if (request.getParameter("action") != null) { %>
        <p>No se encontraron apuestas que coincidan con los criterios de búsqueda.</p>
        <% }%>

        <br>
        <a href="listaApuestas.jsp">Volver a la lista de apuestas</a>
    </body>
</html>

