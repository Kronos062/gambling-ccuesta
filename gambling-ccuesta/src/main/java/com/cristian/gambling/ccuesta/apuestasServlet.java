/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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

/**
 *
 * @author ubuntu
 */
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

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String nombre = request.getParameter("nombre");
        String partido = request.getParameter("partido");
        String fecha = request.getParameter("fecha");
        String resultado = request.getParameter("resultado");
        String dinero = request.getParameter("dinero");
        String competicion = request.getParameter("competicion");
        boolean apuestaGanadora = Boolean.parseBoolean(request.getParameter("apuestaGanadora"));

        if (nombre != null && partido != null && fecha != null && resultado != null && dinero != null) {
            double dineroApostado = Double.parseDouble(dinero);
            Apuesta nuevaApuesta = new Apuesta(nombre, partido, fecha, resultado, dineroApostado, competicion, apuestaGanadora);
            apuestas.add(nuevaApuesta);
        }

        String filtroNombre = request.getParameter("filtroNombre");
        String filtroFecha = request.getParameter("filtroFecha");

        List<Apuesta> apuestasFiltradas = apuestas;

        if (filtroNombre != null && !filtroNombre.isEmpty()) {
            apuestasFiltradas = apuestasFiltradas.stream()
                    .filter(a -> a.getNombre().toLowerCase().contains(filtroNombre.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (filtroFecha != null && !filtroFecha.isEmpty()) {
            apuestasFiltradas = apuestasFiltradas.stream()
                    .filter(a -> a.getFecha().equals(filtroFecha))
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
            out.println("<label for='filtroFecha'>Filtrar por fecha:</label>");
            out.println("<input type='text' id='filtroFecha' name='filtroFecha' value='" + (filtroFecha != null ? filtroFecha : "") + "'>");
            out.println("<input type='submit' value='Filtrar'>");
            out.println("</form>");

            if (apuestasFiltradas.isEmpty()) {
                out.println("<p>No hay apuestas registradas con los criterios especificados.</p>");
            } else {
                for (Apuesta apuesta : apuestasFiltradas) {
                    out.println("<p>" + apuesta
                            + " <a href='apuestasServlet?action=delete&id=" + apuesta.getId() + "'>Eliminar</a> "
                            + "<a href='modificar.jsp?id=" + apuesta.getId() + "'>Modificar</a></p>");
                }
            }

            out.println("<a href='formulario.jsp'>Apostar más<a/>");
            out.println("</body>");
            out.println("</html>");
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            apuestas.removeIf(apuesta -> apuesta.getId() == id);
            response.sendRedirect("listaApuestas.jsp");
        } else {
            String filtroNombre = request.getParameter("filtroNombre");
            String filtroFecha = request.getParameter("filtroFecha");

            List<Apuesta> apuestasFiltradas = apuestas;
            if ((filtroNombre != null && !filtroNombre.isEmpty()) || (filtroFecha != null && !filtroFecha.isEmpty())) {
                apuestasFiltradas = apuestas.stream()
                        .filter(a -> (filtroNombre == null || filtroNombre.isEmpty() || a.getNombre().toLowerCase().contains(filtroNombre.toLowerCase()))
                        && (filtroFecha == null || filtroFecha.isEmpty() || a.getFecha().equals(filtroFecha)))
                        .collect(Collectors.toList());
            }

            request.setAttribute("apuestasFiltradas", apuestasFiltradas);
            request.getRequestDispatcher("listaApuestas.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
            String competicion = request.getParameter("competicion");
            boolean apuestaGanadora = Boolean.parseBoolean(request.getParameter("apuestaGanadora"));

            for (int i = 0; i < apuestas.size(); i++) {
                if (apuestas.get(i).getId() == id) {
                    double dineroApostado = Double.parseDouble(dinero);
                    Apuesta apuestaModificada = new Apuesta(nombre, partido, fecha, resultado, dineroApostado, competicion, apuestaGanadora);
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
            String competicion = request.getParameter("competicion");
            boolean apuestaGanadora = Boolean.parseBoolean(request.getParameter("apuestaGanadora"));

            if (nombre != null && partido != null && fecha != null && resultado != null && dinero != null) {
                double dineroApostado = Double.parseDouble(dinero);
                Apuesta nuevaApuesta = new Apuesta(nombre, partido, fecha, resultado, dineroApostado, competicion, apuestaGanadora);
                apuestas.add(nuevaApuesta);
            }
            response.sendRedirect("apuestasServlet");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    private List<Apuesta> filtrarApuestasPorPartido(String partido, String fecha) {
        return apuestas.stream()
                .filter(a -> a.getPartido().equals(partido) && a.getFecha().equals(fecha))
                .collect(Collectors.toList());
    }

    @Override
    public String getServletInfo() {
        return "Servlet de el gambling de cristian";
    }// </editor-fold>
}
