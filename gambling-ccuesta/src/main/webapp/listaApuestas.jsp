<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="javax.servlet.ServletContext"%>
<%@page import="com.cristian.gambling.ccuesta.Apuesta"%>
<!DOCTYPE html>
<html lang="ca">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Lista de Apuestas</title>
    </head>
    <body>
        <div>
            <h1>Lista de Apuestas</h1>
        </div>
        
        <!-- Formulario de búsqueda -->
        <form action="listaApuestas.jsp" method="get">
            <label for="filtroNombre">Filtrar por nombre:</label>
            <input type="text" id="filtroNombre" name="filtroNombre" value="${param.filtroNombre}">
            <input type="submit" value="Filtrar">
        </form>
        
        <%
            ServletContext context = getServletContext();
            List<Apuesta> apuestas = (List<Apuesta>) context.getAttribute("apuestas");
            String filtroNombre = request.getParameter("filtroNombre");
            
            if (apuestas != null && !apuestas.isEmpty()) {
                if (filtroNombre != null && !filtroNombre.isEmpty()) {
                    apuestas = apuestas.stream()
                        .filter(a -> a.getNombre().toLowerCase().contains(filtroNombre.toLowerCase()))
                        .collect(Collectors.toList());
                }
                
                if (!apuestas.isEmpty()) {
        %>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Partido</th>
                    <th>Fecha</th>
                    <th>Resultado</th>
                    <th>Dinero</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Apuesta apuesta : apuestas) {
                %>
                <tr>
                    <td><%= apuesta.getId()%></td>
                    <td><%= apuesta.getNombre()%></td>
                    <td><%= apuesta.getPartido()%></td>
                    <td><%= apuesta.getFecha()%></td>
                    <td><%= apuesta.getResultado()%></td>
                    <td><%= apuesta.getDinero()%></td>
                    <td>
                        <a href="modificar.jsp?id=<%= apuesta.getId()%>">Modificar</a>
                        <a href="apuestasServlet?action=delete&id=<%= apuesta.getId()%>" style="color: red; margin-left: 10px;">Eliminar</a>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
                } else {
        %>
        <p>No se encontraron apuestas con el nombre especificado.</p>
        <%
                }
            } else {
        %>
        <p>No hay apuestas disponibles.</p>
        <%
            }
        %>
        <a href="formulario.jsp">Volver al inicio</a>
    </body>
</html>
