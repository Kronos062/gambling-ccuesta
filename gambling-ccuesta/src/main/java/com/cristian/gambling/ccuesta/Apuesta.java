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

    public Apuesta(String nombre, String partido, String fecha, String resultado, double dinero) {
        this.id = ++contador;
        this.nombre = nombre;
        this.partido = partido;
        this.fecha = fecha;
        this.resultado = resultado;
        this.dinero = dinero;
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
        return "[" + id + "][" + nombre + "][" + partido + "][" + fecha + "][" + resultado + "][" + dinero + "]";
    }
}
