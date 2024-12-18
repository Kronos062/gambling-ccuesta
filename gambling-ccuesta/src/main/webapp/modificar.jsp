<%-- 
    Document   : modificar.jsp
    Created on : Dec 4, 2024, 1:43:04 PM
    Author     : ubuntu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="javax.servlet.ServletContext"%>
<%@page import="com.cristian.gambling.ccuesta.Apuesta"%>
<!DOCTYPE html>
<html lang="ca">
    <head>
        <meta http-equiv="Content-Type" name="viewport" content="text/html; charset=UTF-8">
        <title>Modificar Apuesta</title>
    </head>
    <body>
        <div><h1>Modificar Apuesta</h1></div>
        <%
            String idParam = request.getParameter("id");
            int id = -1;
            if (idParam != null) {
                try {
                    id = Integer.parseInt(idParam);
                } catch (NumberFormatException e) {
                    out.println("<p>Error: ID inválido.</p>");
                    return;
                }
            }

            ServletContext context = getServletContext();
            List<Apuesta> apuestas = (List<Apuesta>) context.getAttribute("apuestas");
            if (apuestas != null) {
                final int finalId = id;
                Apuesta apuesta = apuestas.stream().filter(a -> a.getId() == finalId).findFirst().orElse(null);
                if (apuestas != null) {
                    String nombre = apuesta.getNombre();
                    String partido = apuesta.getPartido();
                    String fecha = apuesta.getFecha();
                    String resultado = apuesta.getResultado();
                    double dinero = apuesta.getDinero();
                    String competicion = request.getParameter("competicion");
                    boolean apuestaGanadora = Boolean.parseBoolean(request.getParameter("apuestaGanadora"));
        %>  
        <form action="apuestasServlet" method="post">
            Nombre:
            <input type="text" name="nombre" value="<%= nombre%>" required/>
            <br>
            Partido:
            <input type="text" name="partido" value="<%= partido%>" required/>
            <br>
            Fecha:
            <input type="text" name="fecha" value="<%= fecha%>" required/>
            <br>
            Resultado:
            <input type="text" name="resultado" value="<%= resultado%>" required/>
            <br>
            Dinero:
            <input type="text" name="dinero" value="<%= dinero%>" required/>
            <br>
            Competición:
            <input type="text" name="competicion" value="<%= competicion%>" required/>
            <br>
            Apuesta Ganadora:
            <input type="checkbox" name="apuestaGanadora" value="true" <%= apuesta.isApuestaGanadora() ? "checked" : ""%>/>
            <br>
            <input type="hidden" name="id" value="<%= id%>"/>
            <input type="submit" value="Modificar"/>
        </form>
        <%
        } else {
        %>
        <p>Error: La apuesta no se encontró.</p>
        <a href="apuestasServlet">Volver a la lista de apuestas</a>
        <%    }
        } else {
        %>
        <p>Error: La lista de apuestas es nula.</p>
        <a href="apuestasServlet">Volver a la lista de apuestas</a>
        <%    }
        %>
    </body>
</html>
