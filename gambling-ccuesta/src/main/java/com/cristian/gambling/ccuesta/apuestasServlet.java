/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.cristian.gambling.ccuesta;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
        // Inicializar la lista de apuestas en el ServletContext
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

        if (nombre != null && partido != null && fecha != null && resultado != null && dinero != null) {
            double dineroApostado = Double.parseDouble(dinero);
            Apuesta nuevaApuesta = new Apuesta(nombre, partido, fecha, resultado, dineroApostado);
            apuestas.add(nuevaApuesta);
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
            if (apuestas.isEmpty()) {
                out.println("<p>No hay apuestas registradas.</p>");
                out.println("<a href='formulario.jsp'>Apostar más<a/>");
            } else {
                for (Apuesta apuesta : apuestas) {
                    out.println("<p>" + apuesta + " <a href='apuestasServlet?action=delete&id=" + apuesta.getId() + "'>Eliminar</a> <a href='modificar.jsp?id=" + apuesta.getId() + "'>Modificar</a></p>");
                }
                out.println("<a href='formulario.jsp'>Apostar más<a/>");
            }
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
            response.sendRedirect("apuestasServlet");
        } else {
            processRequest(request, response);
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet de el gambling de cristian";
    }// </editor-fold>
}
