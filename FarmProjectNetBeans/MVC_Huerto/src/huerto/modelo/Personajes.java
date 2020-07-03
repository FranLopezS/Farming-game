/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huerto.modelo;

/**
 *
 * @author tarde
 */
public class Personajes {
    private int id_personaje;
    private String nombre;
    private int energia;
    private int energia_max;
    private int agua;
    private int agua_max;
    private int dinero;
    private int tam_bolsillo;
    private int id_partida;
    
    public Personajes(int id_personaje, String nombre) {
        this.id_personaje = id_personaje;
        this.nombre = nombre;
        this.energia = 100;
        this.energia_max = 100;
        this.agua = 100;
        this.agua_max = 100;
        this.dinero = 0;
        this.tam_bolsillo = 10;
    }

    public Personajes(String nombre) {
        this.nombre = nombre;
        this.energia = 100;
        this.energia_max = 100;
        this.agua = 100;
        this.agua_max = 100;
        this.dinero = 0;
        this.tam_bolsillo = 10;
    }

    public Personajes(String nombre, int energia, int agua, int dinero, int id_partida) {
        this.nombre = nombre;
        this.energia = energia;
        this.energia_max = 100;
        this.agua = agua;
        this.dinero = dinero;
        this.agua_max = 100;
        this.id_partida = id_partida;
    }
    
    public Personajes(int id_personaje, String nombre, int energia, int agua, int dinero, int id_partida) {
        this.id_personaje = id_personaje;
        this.nombre = nombre;
        this.energia = energia;
        this.energia_max = 100;
        this.agua = agua;
        this.dinero = dinero;
        this.agua_max = 100;
        this.id_partida = id_partida;
    }
    
    public int getId_personaje() {
        return id_personaje;
    }

    public void setId_personaje(int id_personaje) {
        this.id_personaje = id_personaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public int getEnergia_max() {
        return energia_max;
    }

    public void setEnergia_max(int energia_max) {
        this.energia_max = energia_max;
    }

    public int getAgua() {
        return agua;
    }

    public void setAgua(int agua) {
        this.agua = agua;
    }

    public int getAgua_max() {
        return agua_max;
    }

    public void setAgua_max(int agua_max) {
        this.agua_max = agua_max;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }
    
    public int getTam_bolsillo() {
        return tam_bolsillo;
    }

    public void setTam_bolsillo(int tam_bolsillo) {
        this.tam_bolsillo = tam_bolsillo;
    }

    public int getId_partida() {
        return id_partida;
    }

    public void setId_partida(int id_partida) {
        this.id_partida = id_partida;
    }
    
}
