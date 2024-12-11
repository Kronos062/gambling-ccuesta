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
            String indexParam = request.getParameter("index");
            int index = -1;
            if (indexParam != null) {
                index = Integer.parseInt(indexParam);
            }

            ServletContext context = getServletContext();
            List<Apuesta> apuestas = (List<Apuesta>) context.getAttribute("apuestas");

            if (apuestas != null && index >= 0 && index < apuestas.size()) {
                Apuesta apuesta = apuestas.get(index);
                String nombre = apuesta.getNombre();
                String partido = apuesta.getPartido();
                String fecha = apuesta.getFecha();
                String resultado = apuesta.getResultado();
                double dinero = apuesta.getDinero();
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
            <input type="hidden" name="index" value="<%= index%>"/>
            <input type="submit" value="Modificar"/>
        </form>
        <%
        } else {
        %>
        <p>Error: La apuesta está mal formateada.</p>
        <a href="apuestasServlet">Volver a la lista de apuestas</a>
        <%
            }
        %>
    </body>
</html>
