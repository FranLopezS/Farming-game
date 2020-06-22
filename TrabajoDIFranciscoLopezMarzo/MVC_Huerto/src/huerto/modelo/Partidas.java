/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huerto.modelo;

import java.util.Date;

/**
 *
 * @author tarde
 */
public class Partidas {
    private int id_partida;
    private String nombre_partida;
    private String password;
    private int dia;
    private int mes;
    private int anio;
    private Date fecha_creacion;

    public Partidas(int id_partida, String nombre_partida) {
        this.id_partida = id_partida;
        this.nombre_partida = nombre_partida;
    }
    
    public Partidas(String nombre_partida, String password, int dia, int mes, int anio, Date fecha_creacion) {
        this.nombre_partida = nombre_partida;
        this.password = password;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.fecha_creacion = fecha_creacion;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
    
}
