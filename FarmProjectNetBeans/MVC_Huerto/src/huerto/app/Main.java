/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huerto.app;

import huerto.controlador.PartidasControlador;
import huerto.modelo.ConsultasPartidas;
import huerto.vista.PartidasVista;

/**
 *
 * @author Fran
 */
public class Main {
    
    public static void main(String[] args) {
        PartidasControlador c = new PartidasControlador(new ConsultasPartidas(), new PartidasVista());
        c.inicio();
    }
    
}
