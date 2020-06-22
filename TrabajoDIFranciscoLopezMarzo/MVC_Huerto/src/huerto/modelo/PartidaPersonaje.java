/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huerto.modelo;

/**
 *
 * @author Fran
 */
public class PartidaPersonaje {
    private int id_partida;
    private String nombre_partida;
    private int dia;
    private int mes;
    private int anio;
    private String nombre;
    private int dinero;

    public PartidaPersonaje(int id_partida, String nombre_partida, int dia, int mes, int anio, String nombre, int dinero) {
        this.id_partida = id_partida;
        this.nombre_partida = nombre_partida;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.nombre = nombre;
        this.dinero = dinero;
    }

    public int getId_partida() {
        return id_partida;
    }

    public void setId_partida(int id_partida) {
        this.id_partida = id_partida;
    }

    public String getNombre_partida() {
        return nombre_partida;
    }

    public void setNombre_partida(String nombre_partida) {
        this.nombre_partida = nombre_partida;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }
    
}
