<%-- 
    Document   : formulario.jsp
    Created on : Nov 27, 2024, 11:34:57 AM
    Author     : ubuntu
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="com.cristian.gambling.ccuesta.Apuesta"%>
<!DOCTYPE html>
<html lang="ca">
    <head>
        <title>Apuestas</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div><h1><%= request.getParameter("id") != null ? "Modificar Apuesta" : "Haz tu apuesta"%></h1></div>
        <form action="apuestasServlet" method="post">
            <%
                String idParam = request.getParameter("id");
                if (idParam != null) {
                    int id = Integer.parseInt(idParam);
                    Apuesta apuesta = null;
                    ServletContext context = getServletContext();
                    List<Apuesta> apuestas = (List<Apuesta>) context.getAttribute("apuestas");
                    if (apuestas != null) {
                        for (Apuesta a : apuestas) {
                            if (a.getId() == id) {
                                apuesta = a;
                                break;
                            }
                        }
                    }
                    if (apuesta != null) {
            %>
            <input type="hidden" name="id" value="<%= apuesta.getId()%>"/>
            Nombre:
            <input type="text" name="nombre" value="<%= apuesta.getNombre()%>" required/>
            <br>
            Partido:
            <input type="text" name="partido" value="<%= apuesta.getPartido()%>" required/>
            <br>
            Fecha:
            <input type="text" name="fecha" value="<%= apuesta.getFecha()%>" required/>
            <br>
            Resultado:
            <input type="text" name="resultado" value="<%= apuesta.getResultado()%>" required/>
            <br>
            Dinero:
            <input type="number" name="dinero" value="<%= apuesta.getDinero()%>" required min="1"/>
            <br>
            <input type="submit" value="Modificar"/>
            <%
                } else {
                    out.println("<p>Error: Apuesta no encontrada.</p>");
                }
            } else {
            %>
            Nombre:
            <input type="text" name="nombre" required/>
            <br>
            Partido:
            <input type="text" name="partido" required/>
            <br>
            Fecha:
            <input type="text" name="fecha" required/>
            <br>
            Resultado:
            <input type="text" name="resultado" required/>
            <br>
            Dinero:
            <input type="number" name="dinero" required min="1"/>
            <br>
            <input type="submit" value="Enviar"/>
            <%
                }
            %>
        </form>
        <br>
        <a href="listaApuestas.jsp">Volver a la lista de apuestas</a>
    </body>
</html>