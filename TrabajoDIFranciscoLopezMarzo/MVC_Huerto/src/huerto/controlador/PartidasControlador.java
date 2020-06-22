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
import huerto.vista.HuertoVista;
import huerto.vista.NuevaPartidaVista;
import huerto.vista.PartidasVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;
import javax.help.MainWindow;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fran
 */
public class PartidasControlador implements ActionListener, MouseListener {
    private PartidasVista partidasVista;
    private ConsultasPartidas consultaPartidas;
    private DefaultTableModel modelo;
    
    public PartidasControlador(ConsultasPartidas consultaPartidas, PartidasVista partidasVista) {
        this.consultaPartidas = consultaPartidas;
        this.partidasVista = partidasVista;
        this.modelo = new DefaultTableModel();
        partidasVista.tabla.setModel(modelo);
        
        setHelp();
    }
    
    private void setHelp() {
        ClassLoader c1 = MainWindow.class.getClassLoader();
        URL hsURL = HelpSet.findHelpSet(c1, "help/help_set.hs");
        try {
            HelpSet helpset = new HelpSet(c1, hsURL);
            HelpBroker hb = helpset.createHelpBroker();
            hb.enableHelpOnButton(partidasVista.ayuda, "index", helpset);
            hb.enableHelpKey(partidasVista.getRootPane(), "index", helpset);
        } catch (HelpSetException ex) {
            Logger.getLogger(AdministracionControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void inicio() {
        partidasVista.setVisible(true);
        setListeners();
    }
    
    public void fin() {
        partidasVista.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "new":
                nuevaPartida();
                break;
            case "continue":
                rellenarTabla();
                break;
            case "exit":
                System.exit(0);
                break;
        }
    }

    private void setListeners() {
        partidasVista.btnNew.addActionListener(this);
        partidasVista.btnContinue.addActionListener(this);
        partidasVista.btnSalir.addActionListener(this);
        partidasVista.tabla.addMouseListener(this);
    }

    private void nuevaPartida() {
        NuevaPartidaControlador c = new NuevaPartidaControlador(new ConsultasPartidas(), new NuevaPartidaVista(),
            new ConsultasHuerto(), new ConsultasTienda(), new ConsultasPersonajes());
        c.inicio(); // Iniciar y abrir la nueva vista.
        fin(); // Cerrar esta vista.
    }

    private void rellenarTabla() {
        modelo.setRowCount(0);
        modelo.setColumnCount(0);
        modelo.addColumn("Nº");
        modelo.addColumn("Nombre de la partida");
        modelo.addColumn("Personaje");
        modelo.addColumn("Dinero");
        ArrayList<PartidaPersonaje> partidas = consultaPartidas.todoPartidas();
        for(PartidaPersonaje p: partidas) {
            Object[] o = new Object[4];
            o[0] = p.getId_partida();
            o[1] = p.getNombre_partida();
            o[2] = p.getNombre();
            o[3] = p.getDinero();
            modelo.addRow(o);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Contraseña para "
                +partidasVista.tabla.getValueAt(partidasVista.tabla.getSelectedRow(), 1)+":");
        JPasswordField pass = new JPasswordField(10);
        panel.add(label);
        panel.add(pass);
        String[] options = new String[]{"Cancelar", "Aceptar"};
        int option = JOptionPane.showOptionDialog(null, panel, "Contraseña",
                                 JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                 null, options, options[1]);
        if(option == 1) { // Aceptar
            int id_partida = (int) partidasVista.tabla.getValueAt(partidasVista.tabla.getSelectedRow(),0);
            String nombre_partida = (String) partidasVista.tabla.getValueAt(partidasVista.tabla.getSelectedRow(),1);
            String passw = consultaPartidas.getPassword(id_partida);
            char[] password = pass.getPassword();
            if(new String(password).equals(passw)) {
                // CONECTA!!
                HuertoControlador c = new HuertoControlador(
                        new ConsultasPersonajes(),
                        new HuertoVista(),
                        new ConsultasHuerto(),
                        new ConsultasPartidas(),
                        id_partida,
                        nombre_partida,
                        0,
                        new ConsultasParcelas(),
                        new ConsultasTienda(),
                        new ConsultasProductosEnTiendas(),
                        new ConsultasProductos()
                );
                fin();
                c.inicio();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
