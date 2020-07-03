/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huerto.controlador;

import huerto.modelo.ConsultasHuerto;
import huerto.modelo.ConsultasParcelas;
import huerto.modelo.ConsultasPartidas;
import huerto.modelo.ConsultasPersonajes;
import huerto.modelo.ConsultasProductos;
import huerto.modelo.ConsultasProductosEnTiendas;
import huerto.modelo.ConsultasTienda;
import huerto.modelo.PartidaPersonaje;
import huerto.modelo.Partidas;
import huerto.modelo.Personajes;
import huerto.vista.AdministracionVista;
import huerto.vista.HuertoVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;
import javax.help.MainWindow;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Fran
 */
public class AdministracionControlador implements MouseListener, ActionListener {
    private AdministracionVista administracionVista;
    private ConsultasPersonajes consultasPersonajes;
    private ConsultasPartidas consultasPartidas;
    private ConsultasHuerto consultasHuerto;
    private int id_partida, id_personaje;
    private String nombre_personaje;
    private final String BBDD = "huerto";
    private final String USER = "root";
    private final String PASS = "";
    private final String url = "jdbc:mysql://localhost/"+BBDD;
    private Connection conn;
    
    public void conectar() {
        try {
            conn = DriverManager.getConnection(url, USER, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(AdministracionControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public AdministracionControlador(AdministracionVista administracionVista,
            ConsultasPersonajes consultasPersonajes, ConsultasPartidas consultasPartidas,
            ConsultasHuerto consultasHuerto, int id_partida, int id_personaje, String nombre_personaje) {
        this.administracionVista = administracionVista;
        this.consultasPersonajes = consultasPersonajes;
        this.consultasPartidas = consultasPartidas;
        this.consultasHuerto = consultasHuerto;
        this.id_partida = id_partida;
        this.id_personaje = id_personaje;
        this.nombre_personaje = nombre_personaje;
    }
    
    public void inicio() {
        rellenarComboPartidas();
        ponerInfoEnCampos();
        conectar();
        setHelp();
        administracionVista.btnAplicar.addActionListener(this);
        administracionVista.btnBack.addActionListener(this);
        administracionVista.comboUsuarios.addMouseListener(this);
        administracionVista.btnInfoJugador.addActionListener(this);
        administracionVista.btnProductosEnBolsillos.addActionListener(this);
        administracionVista.setVisible(true);
    }
    
    private void setHelp() {
        ClassLoader c1 = MainWindow.class.getClassLoader();
        URL hsURL = HelpSet.findHelpSet(c1, "help/help_set.hs");
        try {
            HelpSet helpset = new HelpSet(c1, hsURL);
            HelpBroker hb = helpset.createHelpBroker();
            hb.enableHelpOnButton(administracionVista.ayuda, "index", helpset);
            hb.enableHelpKey(administracionVista.getRootPane(), "index", helpset);
        } catch (HelpSetException ex) {
            Logger.getLogger(AdministracionControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void fin() {
        administracionVista.setVisible(false);
    }
    
    private void rellenarComboPartidas() {
        ArrayList<PartidaPersonaje> partidas = consultasPartidas.todoPartidas();
        for (PartidaPersonaje p: partidas) {
            if(p.getId_partida() != 25)
                administracionVista.comboUsuarios.addItem(p.getId_partida()+"#"+p.getNombre_partida());
        }
    }
    
    private void ponerInfoEnCampos() {
        int idPartida = Integer.valueOf(administracionVista.comboUsuarios.getSelectedItem().toString().split("#")[0]);
        Partidas p = consultasPartidas.unaPartida(idPartida);
        Personajes per = consultasPersonajes.unPersonaje(idPartida);
        administracionVista.txtNombrePar.setText(p.getNombre_partida());
        administracionVista.txtNombrePer.setText(per.getNombre());
        administracionVista.txtPassw.setText(p.getPassword());
        administracionVista.txtDinero.setText(per.getDinero()+"");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        ponerInfoEnCampos();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        ponerInfoEnCampos();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ponerInfoEnCampos();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        ponerInfoEnCampos();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        ponerInfoEnCampos();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "aplicar":
                aplicarCambios();
                break;
            case "back":
                HuertoControlador c = new HuertoControlador(
                        new ConsultasPersonajes(),
                        new HuertoVista(),
                        new ConsultasHuerto(),
                        new ConsultasPartidas(),
                        id_partida,
                        nombre_personaje,
                        1,
                        new ConsultasParcelas(),
                        new ConsultasTienda(),
                        new ConsultasProductosEnTiendas(),
                        new ConsultasProductos()
                );
                fin();
                c.inicio();
                break;
            case "bolsillos": // 1er form.
                bolsillos();
                break;
            case "partidas": // 2º form.
                partidas();
                break;
        }
    }
    
    private void partidas() {
        Map params = new HashMap();
        params.put("NOMBRE_PARTIDA", administracionVista.comboUsuarios.getSelectedItem().toString()
                        .split("#")[1]);
        try {
            JasperPrint print = JasperFillManager.fillReport("jasper/InformacionPartida.jasper", params, conn);
            JasperExportManager.exportReportToPdfFile(print, "jasper/InformacionPartida.pdf");
            JasperViewer.viewReport(print);
            conn.close();
        } catch (JRException ex) {
            Logger.getLogger(AdministracionControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdministracionControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void bolsillos() {
        try {
            JasperPrint print = JasperFillManager.fillReport("jasper/ProductosEnBolsillos.jasper", null, conn);
            JasperExportManager.exportReportToPdf(print);
            JasperViewer.viewReport(print);
            conn.close();
        } catch (JRException ex) {
            Logger.getLogger(AdministracionControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdministracionControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void aplicarCambios() {
        int idPartida = Integer.valueOf(administracionVista.comboUsuarios.getSelectedItem().toString().split("#")[0]);
        Personajes per = consultasPersonajes.unPersonaje(idPartida);
        int idPersonaje = per.getId_personaje();
        try {
            if(!administracionVista.txtNombrePar.getText().isEmpty())
                if(!administracionVista.txtNombrePar.getText().contains("#"))
                    consultasPartidas.actualizarNombrePartida(idPartida, administracionVista.txtNombrePar.getText());
                else
                    JOptionPane.showMessageDialog(administracionVista, "El nombre de una partida "
                        + "no puede contener el caracter '#'.", "Error", JOptionPane.ERROR_MESSAGE);
            if(!administracionVista.txtNombrePer.getText().isEmpty())
                consultasPersonajes.actualizarNombre(idPersonaje, administracionVista.txtNombrePer.getText());
            if(!passwToString(administracionVista.txtPassw.getPassword()).isEmpty())
                consultasPartidas.actualizarPassw(idPartida, passwToString(administracionVista.txtPassw.getPassword()));
            if(!administracionVista.txtDinero.getText().isEmpty())
                consultasPersonajes.setDinero(idPersonaje, Integer.valueOf(administracionVista.txtDinero.getText()));
        } catch(Exception e) {
            JOptionPane.showMessageDialog(administracionVista, "Algo ha ido mal. Procure poner valores correctos para "
                    + "todos los campos e inténtelo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        JOptionPane.showMessageDialog(administracionVista, "Se han aplicado los cambios.");
        rellenarComboPartidas();
        ponerInfoEnCampos();
    }
    
    public String passwToString(char[] passw) {
        String cad = "";
        for(int i=0; i<passw.length; i++) {
            cad += passw[i];
        }
        return cad;
    }
    
}
