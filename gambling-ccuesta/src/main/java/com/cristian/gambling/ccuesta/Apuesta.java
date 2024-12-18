package com.cristian.gambling.ccuesta;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ubuntu
 */
public class Apuesta {

    private static int contador = 0;
    private int id;
    private String nombre;
    private String partido;
    private String fecha;
    private String resultado;
    private double dinero;
    private String competicion;
    private boolean apuestaGanadora;

    public Apuesta(String nombre, String partido, String fecha, String resultado, double dinero, String competicion, boolean apuestaGanadora) {
        this.id = ++contador;
        this.nombre = nombre;
        this.partido = partido;
        this.fecha = fecha;
        this.resultado = resultado;
        this.dinero = dinero;
        this.competicion = competicion;
        this.apuestaGanadora = apuestaGanadora;
    }

    public static void setContador(int contador) {
        Apuesta.contador = contador;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public void setDinero(double dinero) {
        this.dinero = dinero;
    }

    public void setCompeticion(String competicion) {
        this.competicion = competicion;
    }

    public void setApuestaGanadora(boolean apuestaGanadora) {
        this.apuestaGanadora = apuestaGanadora;
    }

    public static int getContador() {
        return contador;
    }

    public String getCompeticion() {
        return competicion;
    }

    public boolean isApuestaGanadora() {
        return apuestaGanadora;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPartido() {
        return partido;
    }

    public String getFecha() {
        return fecha;
    }

    public String getResultado() {
        return resultado;
    }

    public double getDinero() {
        return dinero;
    }

    @Override
    public String toString() {
        return "[" + id + "][" + nombre + "][" + partido + "][" + fecha + "][" + resultado + "][" + dinero + "][" + competicion + "][" + (apuestaGanadora ? "Ganadora" : "Perdedora") + "]";
    }
}
