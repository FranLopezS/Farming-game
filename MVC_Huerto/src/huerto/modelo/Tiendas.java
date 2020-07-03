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
public class Tiendas {
    private int id_tienda;
    private String nombre;
    private int id_partida;

    public Tiendas(int id_tienda, String nombre, int id_partida) {
        this.id_tienda = id_tienda;
        this.nombre = nombre;
        this.id_partida = id_partida;
    }

    public int getId_tienda() {
        return id_tienda;
    }

    public void setId_tienda(int id_tienda) {
        this.id_tienda = id_tienda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_partida() {
        return id_partida;
    }

    public void setId_partida(int id_partida) {
        this.id_partida = id_partida;
    }
    
}
