package com.cristian.gambling.ccuesta;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "apuestasServlet", urlPatterns = {"/apuestasServlet"})
public class apuestasServlet extends HttpServlet {

    private ArrayList<Apuesta> apuestas = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        apuestas = (ArrayList<Apuesta>) context.getAttribute("apuestas");
        if (apuestas == null) {
            apuestas = new ArrayList<>();
            context.setAttribute("apuestas", apuestas);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String nombre = request.getParameter("nombre");
        String partido = request.getParameter("partido");
        String fecha = request.getParameter("fecha");
        String resultado = request.getParameter("resultado");
        String dinero = request.getParameter("dinero");

        if (nombre != null && partido != null && fecha != null && resultado != null && dinero != null) {
            double dineroApostado = Double.parseDouble(dinero);
            Apuesta nuevaApuesta = new Apuesta(nombre, partido, fecha, resultado, dineroApostado);
            apuestas.add(nuevaApuesta);
        }

        String filtroNombre = request.getParameter("filtroNombre");
        List<Apuesta> apuestasFiltradas = apuestas;
        if (filtroNombre != null && !filtroNombre.isEmpty()) {
            apuestasFiltradas = apuestas.stream()
                .filter(a -> a.getNombre().toLowerCase().contains(filtroNombre.toLowerCase()))
                .collect(Collectors.toList());
        }

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Detalles de la apuesta</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Detalles de la Apuesta</h1>");
            if (nombre != null) {
                out.println("<p>Nombre: " + nombre + "</p>");
                out.println("<p>Partido: " + partido + "</p>");
                out.println("<p>Fecha: " + fecha + "</p>");
                out.println("<p>Resultado: " + resultado + "</p>");
                out.println("<p>Dinero apostado: " + dinero + "</p>");
            }
            out.println("<h2>Apuestas actuales:</h2>");
            out.println("<form action='apuestasServlet' method='get'>");
            out.println("<label for='filtroNombre'>Filtrar por nombre:</label>");
            out.println("<input type='text' id='filtroNombre' name='filtroNombre' value='" + (filtroNombre != null ? filtroNombre : "") + "'>");
            out.println("<input type='submit' value='Filtrar'>");
            out.println("</form>");
            if (apuestasFiltradas.isEmpty()) {
                out.println("<p>No hay apuestas registradas.</p>");
            } else {
                for (Apuesta apuesta : apuestasFiltradas) {
                    out.println("<p>" + apuesta + " <a href='apuestasServlet?action=delete&id=" + apuesta.getId() + "'>Eliminar</a> <a href='modificar.jsp?id=" + apuesta.getId() + "'>Modificar</a></p>");
                }
            }
            out.println("<a href='formulario.jsp'>Apostar más<a/>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            apuestas.removeIf(apuesta -> apuesta.getId() == id);
            response.sendRedirect("apuestasServlet");
        } else {
            processRequest(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            String nombre = request.getParameter("nombre");
            String partido = request.getParameter("partido");
            String fecha = request.getParameter("fecha");
            String resultado = request.getParameter("resultado");
            String dinero = request.getParameter("dinero");

            for (int i = 0; i < apuestas.size(); i++) {
                if (apuestas.get(i).getId() == id) {
                    double dineroApostado = Double.parseDouble(dinero);
                    Apuesta apuestaModificada = new Apuesta(nombre, partido, fecha, resultado, dineroApostado);
                    apuestas.set(i, apuestaModificada);
                    break;
                }
            }
            response.sendRedirect("apuestasServlet");
        } else {
            String nombre = request.getParameter("nombre");
            String partido = request.getParameter("partido");
            String fecha = request.getParameter("fecha");
            String resultado = request.getParameter("resultado");
            String dinero = request.getParameter("dinero");

            if (nombre != null && partido != null && fecha != null && resultado != null && dinero != null) {
                double dineroApostado = Double.parseDouble(dinero);
                Apuesta nuevaApuesta = new Apuesta(nombre, partido, fecha, resultado, dineroApostado);
                apuestas.add(nuevaApuesta);
            }
            response.sendRedirect("apuestasServlet");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet de el gambling de cristian";
    }
}