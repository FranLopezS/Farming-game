/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huerto.modelo;

import java.util.Date;

/**
 *
 * @author Fran
 */
public class ProductosEnTiendas {
    private int id_personaje;
    private int id_producto;
    private int id_tienda;
    private int precio;
    private Date fecha;
    private int dia;
    private int mes;
    private int anio;

    public ProductosEnTiendas(int id_personaje, int id_producto, int id_tienda, int precio, Date fecha, int dia, int mes, int anio) {
        this.id_personaje = id_personaje;
        this.id_producto = id_producto;
        this.id_tienda = id_tienda;
        this.precio = precio;
        this.fecha = fecha;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    public int getId_personaje() {
        return id_personaje;
    }

    public void setId_personaje(int id_personaje) {
        this.id_personaje = id_personaje;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_tienda() {
        return id_tienda;
    }

    public void setId_tienda(int id_tienda) {
        this.id_tienda = id_tienda;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
    
}
