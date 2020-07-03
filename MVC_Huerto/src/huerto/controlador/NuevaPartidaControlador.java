/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huerto.controlador;

import huerto.modelo.ConsultasHuerto;
import huerto.modelo.ConsultasPartidas;
import huerto.modelo.ConsultasPersonajes;
import huerto.modelo.ConsultasTienda;
import huerto.modelo.Partidas;
import huerto.modelo.Personajes;
import huerto.vista.NuevaPartidaVista;
import huerto.vista.PartidasVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;
import javax.help.MainWindow;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author tarde
 */
public class NuevaPartidaControlador implements ActionListener {
    private NuevaPartidaVista nuevaPartidaVista;
    private ConsultasPartidas consultasPartidas;
    private ConsultasPersonajes consultasPersonajes;
    private ConsultasHuerto consultasHuerto;
    private ConsultasTienda consultasTiendas;
    
    public NuevaPartidaControlador(ConsultasPartidas consultasPartidas, NuevaPartidaVista nuevaPartidaVista,
            ConsultasHuerto consultasHuerto, ConsultasTienda consultasTiendas, ConsultasPersonajes consultasPersonajes) {
        this.consultasPartidas = consultasPartidas;
        this.nuevaPartidaVista = nuevaPartidaVista;
        this.consultasPersonajes = consultasPersonajes;
        this.consultasHuerto = consultasHuerto;
        this.consultasTiendas = consultasTiendas;
    }
    
    public void inicio() {
        nuevaPartidaVista.setVisible(true);
        setHelp();
        setListeners();
    }
    
    // Cerrar esta ventana.
    public void fin() {
        nuevaPartidaVista.setVisible(false);
    }
    
    // Cuando das a la flcha de ir hacia atrás
    public void back() {
        PartidasControlador c = new PartidasControlador(new ConsultasPartidas(), new PartidasVista());
        c.inicio();
        fin();
    }
    
    private void setHelp() {
        ClassLoader c1 = MainWindow.class.getClassLoader();
        URL hsURL = HelpSet.findHelpSet(c1, "help/help_set.hs");
        try {
            HelpSet helpset = new HelpSet(c1, hsURL);
            HelpBroker hb = helpset.createHelpBroker();
            hb.enableHelpOnButton(nuevaPartidaVista.ayuda, "index", helpset);
            hb.enableHelpKey(nuevaPartidaVista.getRootPane(), "index", helpset);
        } catch (HelpSetException ex) {
            Logger.getLogger(AdministracionControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch(ae.getActionCommand()) {
            case "accept":
                addCharacter();
                break;
            case "back": // atrás
                back();
                break;
        }
    }

    private void setListeners() {
        nuevaPartidaVista.btnBack.addActionListener(this);
        nuevaPartidaVista.btnOk.addActionListener(this);
    }
    
    
    private void addCharacter() {
        // primero: comprobar que todos los campos tienen datos.
        if(tienenDatos(new JTextField[]{nuevaPartidaVista.txtNombreUsu,
                nuevaPartidaVista.txtPassw, nuevaPartidaVista.txtNombrePer})) {
            // Se crea la partida.
            Partidas p = new Partidas(nuevaPartidaVista.txtNombreUsu.getText(),
                    passwToString(nuevaPartidaVista.txtPassw.getPassword()), 1, 1, 1, new Date());
            // Se crea el personaje.
            Personajes p2 = new Personajes(nuevaPartidaVista.txtNombrePer.getText());
            if(p.getNombre_partida().contains("#")){
                JOptionPane.showMessageDialog(nuevaPartidaVista, "El nombre de una partida "
                        + "no puede contener el caracter '#'.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Se insertan.
                consultasPartidas.insertarPartida(p, p2);
                Personajes pNuevo = consultasPersonajes.unPersonaje(
                        consultasPartidas.getIdUltimaPartidaInsertada()
                );
                consultasHuerto.insertarPrimerasParcelas(pNuevo.getId_personaje());
                consultasTiendas.addTienda(nuevaPartidaVista.txtNombrePer.getText(),  consultasPartidas.getIdUltimaPartidaInsertada());
                back();
            }
        } else {
            JOptionPane.showMessageDialog(nuevaPartidaVista, 
                    "Debes rellenar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Para comprobar si los JTextFields tienen datos.
    private boolean tienenDatos(JTextField[] fields) {
        for (JTextField f: fields) {
            if (f.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    private String passwToString(char[] c) {
        String cad = "";
        for (char d : c) {
            cad += d;
        }
        return cad;
    }
    
}
