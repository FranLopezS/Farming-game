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
import huerto.modelo.Partidas;
import huerto.modelo.Personajes;
import huerto.modelo.Productos;
import huerto.modelo.ProductosEnTiendas;
import huerto.modelo.Tiendas;
import huerto.vista.HuertoVista;
import huerto.vista.TiendasVista;
import java.awt.GridLayout;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fran
 */
public class TiendaControlador implements ActionListener, MouseListener {
    private ConsultasTienda consultasTienda;
    private TiendasVista tiendasVista;
    private DefaultTableModel modelo;
    private ConsultasPersonajes consultasPersonajes;
    private ConsultasPartidas consultasPartidas;
    private ConsultasHuerto consultasHuerto;
    private ConsultasProductos consultasProductos;
    private ConsultasProductosEnTiendas consultasProductosTiendas;
    private int id_partida;
    private int id_personaje;
    private String nombre_personaje;
    private int indice_bolsillo;
    private int[] productosGlobal;
    int ultimaSeccionPulsada;
    private JToggleButton[] botonesObjs;
    private int id_del_personaje_cuya_tienda_estas_viendo;
    
    // AQUÍ GUARDO LOS BOTONES DE LAS TIENDAS AUTO-GENERADOS, Y LAS TIENDAS, PARA SABER DE QUIÉN SON Y ESO.
    private ArrayList<JButton> botonesTiendas;
    private ArrayList<Tiendas> tiendas;
    private int permiso;
    
    public TiendaControlador(ConsultasTienda consultasTienda, TiendasVista tiendasVista, int id_partida,
            int id_personaje, String nombre_personaje, int permiso,ConsultasPersonajes consultasPersonajes,
            ConsultasPartidas consultasPartidas, ConsultasHuerto consultasHuerto,
            ConsultasProductos consultasProductos,ConsultasProductosEnTiendas consultasProductosTiendas) {
        this.consultasTienda = consultasTienda;
        this.tiendasVista = tiendasVista;
        this.id_partida = id_partida;
        this.id_personaje = id_personaje;
        this.nombre_personaje = nombre_personaje;
        this.indice_bolsillo = 1;
        this.botonesTiendas = new ArrayList<>();
        this.tiendas = new ArrayList<>();
        this.id_del_personaje_cuya_tienda_estas_viendo = -1;
        this.modelo = new DefaultTableModel();
        this.permiso = permiso;
        this.consultasPersonajes=consultasPersonajes;
        this.consultasPartidas=consultasPartidas;
        this.consultasHuerto=consultasHuerto;
        this.consultasProductos=consultasProductos;
        this.consultasProductosTiendas=consultasProductosTiendas;
    }
    
    public void inicio() {
        initButtons();
        ajustarAguaYEnergia();
        ajustarBolsilloExtensible();
        ajustarFecha();
        actualizarDinero();
        actualizarBolsillo();
        generateProducts();
        rellenarTabla();
        ponerTiendasDeOtrosJugadores();
        setHelp();
        tiendasVista.setExtendedState(JFrame.MAXIMIZED_BOTH);
        tiendasVista.tabla.setModel(modelo);
        tiendasVista.setVisible(true);
    }
    
    private void setHelp() {
        ClassLoader c1 = MainWindow.class.getClassLoader();
        URL hsURL = HelpSet.findHelpSet(c1, "help/help_set.hs");
        try {
            HelpSet helpset = new HelpSet(c1, hsURL);
            HelpBroker hb = helpset.createHelpBroker();
            hb.enableHelpOnButton(tiendasVista.ayuda, "index", helpset);
            hb.enableHelpKey(tiendasVista.getRootPane(), "index", helpset);
        } catch (HelpSetException ex) {
            Logger.getLogger(AdministracionControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void fin() {
        tiendasVista.setVisible(false);
    }
    
    private void actualizarDinero() {
        Personajes p = consultasPersonajes.unPersonaje(id_partida);
        tiendasVista.lblDinero.setText(consultasPersonajes.getDineroActual(p.getId_personaje())+" €");
    }
    
    private void ajustarFecha() {
        Partidas p = consultasPartidas.unaPartida(id_partida);
        tiendasVista.lblFecha.setText("* Día "+p.getDia()+" * Mes "+p.getMes()+" * Año "+p.getAnio()+" *");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "item1":
                ultimaSeccionPulsada = 0;
                cambiarToggleButtons(new boolean[]{true,false,false,false,false,
                                        false,false,false,false,false});
                break;
            case "item2":
                ultimaSeccionPulsada = 0;
                cambiarToggleButtons(new boolean[]{false,true,false,false,false,
                                        false,false,false,false,false});
                break;
            case "item3":
                ultimaSeccionPulsada = 0;
                cambiarToggleButtons(new boolean[]{false,false,true,false,false,
                                        false,false,false,false,false});
                break;
            case "item4":
                ultimaSeccionPulsada = 0;
                cambiarToggleButtons(new boolean[]{false,false,false,true,false,
                                        false,false,false,false,false});
                break;
            case "item5":
                ultimaSeccionPulsada = 0;
                cambiarToggleButtons(new boolean[]{false,false,false,false,true,
                                        false,false,false,false,false});
                break;
            case "item6":
                ultimaSeccionPulsada = 0;
                cambiarToggleButtons(new boolean[]{false,false,false,false,false,
                                        true,false,false,false,false});
                break;
            case "item7":
                ultimaSeccionPulsada = 0;
                cambiarToggleButtons(new boolean[]{false,false,false,false,false,
                                        false,true,false,false,false});
                break;
            case "item8":
                ultimaSeccionPulsada = 0;
                cambiarToggleButtons(new boolean[]{false,false,false,false,false,
                                        false,false,true,false,false});
                break;
            case "item9":
                ultimaSeccionPulsada = 0;
                cambiarToggleButtons(new boolean[]{false,false,false,false,false,
                                        false,false,false,true,false});
                break;
            case "item10":
                ultimaSeccionPulsada = 0;
                cambiarToggleButtons(new boolean[]{false,false,false,false,false,
                                        false,false,false,false,true});
                break;
            case "back":
                HuertoControlador c = new HuertoControlador(
                        new ConsultasPersonajes(),
                        new HuertoVista(),
                        new ConsultasHuerto(),
                        new ConsultasPartidas(),
                        id_partida,
                        nombre_personaje,
                        permiso,
                        new ConsultasParcelas(),
                        new ConsultasTienda(),
                        new ConsultasProductosEnTiendas(),
                        new ConsultasProductos()
                );
                fin();
                c.inicio();
                break;
            case "exit":
                System.exit(0);
                break;
            case "itemnext":
                indice_bolsillo++;
                actualizarBolsillo();
                break;
            case "buy":
                comprarProducto();
                break;
            case "global":
                generateProducts();
                rellenarTabla();
                id_del_personaje_cuya_tienda_estas_viendo = -1;
                break;
        }
        
        // SI SE PULSA UN BOTÓN DE TIENDA.
        for(int i=0; i<botonesTiendas.size(); i++) {
            if(e.getActionCommand().equals(botonesTiendas.get(i).getActionCommand())) {
                Tiendas t = tiendas.get(i); // SE RECOGE LA TIENDA PULSADA.
                // SE RECOGEN SUS PRODUCTOS Y SE PONEN EN LA TABLA.
                ArrayList<ProductosEnTiendas> productos = 
                        consultasProductosTiendas.getProductosByIdPartida(t.getId_partida());
                modelo.setColumnCount(0);
                modelo.setRowCount(0);
                modelo.addColumn("Nombre");
                modelo.addColumn("Descripción");
                modelo.addColumn("Precio");
                for (ProductosEnTiendas p : productos) {
                    id_del_personaje_cuya_tienda_estas_viendo = p.getId_personaje();
                    Object[] o = consultasProductos.getProductoParaTienda(p.getId_producto());
                    modelo.addRow(o);
                }
            }
        }
        
        ajustarFecha();
        actualizarDinero();
        actualizarBolsillo();
    }
    
    private void cambiarToggleButtons(boolean[] b) {
        for(int i=0; i<botonesObjs.length; i++) {
            botonesObjs[i].setSelected(b[i]);
        }
    }

    private void initButtons() {
        tiendasVista.btnTiendaGlobal.addActionListener(this);
        tiendasVista.btnComprar.addActionListener(this);
        tiendasVista.btnBack.addActionListener(this);
        tiendasVista.item1.addActionListener(this);
        tiendasVista.item2.addActionListener(this);
        tiendasVista.item3.addActionListener(this);
        tiendasVista.item4.addActionListener(this);
        tiendasVista.item5.addActionListener(this);
        tiendasVista.item6.addActionListener(this);
        tiendasVista.item7.addActionListener(this);
        tiendasVista.item8.addActionListener(this);
        tiendasVista.item9.addActionListener(this);
        tiendasVista.item10.addActionListener(this);
        tiendasVista.tabla.addMouseListener(this);
        
        botonesObjs = new JToggleButton[10];
        botonesObjs[0] = tiendasVista.item1;
        botonesObjs[1] = tiendasVista.item2;
        botonesObjs[2] = tiendasVista.item3;
        botonesObjs[3] = tiendasVista.item4;
        botonesObjs[4] = tiendasVista.item5;
        botonesObjs[5] = tiendasVista.item6;
        botonesObjs[6] = tiendasVista.item7;
        botonesObjs[7] = tiendasVista.item8;
        botonesObjs[8] = tiendasVista.item9;
        botonesObjs[9] = tiendasVista.item10;
    }
    
    private void actualizarBolsillo() {
        Personajes p2 = consultasPersonajes.unPersonaje(id_partida);
        switch (indice_bolsillo) {
            case 1:
                for(int i=1; i<=10; i++) { // Recorre todos los bolsillos del 1 al 10.
                    try {
                        Productos prod = consultasHuerto.getProducto(i, p2.getId_personaje());
                        Icon ico = new ImageIcon("src/imagenes/"+prod.getImg()+".png");
                        botonesObjs[i-1].setIcon(ico);
                    } catch(Exception e) { // ESTA EXCEPCIÓN SALTA SI NO HAY NADA EN ALGÚN BOLSILLO.
                        Icon ico = new ImageIcon("src/imagenes/nada.png");
                        botonesObjs[i-1].setIcon(ico);
                    }
                }
                break;
            case 2:
                for(int i=11; i<=20; i++) { // Recorre todos los bolsillos.
                    try {
                        Productos prod = consultasHuerto.getProducto(i, p2.getId_personaje());
                        Icon ico = new ImageIcon("src/imagenes/"+prod.getImg()+".png");
                        botonesObjs[i-11].setIcon(ico);
                    } catch(Exception e) { // ESTA EXCEPCIÓN SALTA SI NO HAY NADA EN ALGÚN BOLSILLO.
                        Icon ico = new ImageIcon("src/imagenes/nada.png");
                        botonesObjs[i-11].setIcon(ico);
                    }
                }
                break;
            default:
                for(int i=21; i<=30; i++) { // Recorre todos los bolsillos.
                    try {
                        Productos prod = consultasHuerto.getProducto(i, p2.getId_personaje());
                        Icon ico = new ImageIcon("src/imagenes/"+prod.getImg()+".png");
                        botonesObjs[i-21].setIcon(ico);
                    } catch(Exception e) { // ESTA EXCEPCIÓN SALTA SI NO HAY NADA EN ALGÚN BOLSILLO.
                        Icon ico = new ImageIcon("src/imagenes/nada.png");
                        botonesObjs[i-21].setIcon(ico);
                    }
                }
                break;
        }
        
    }
    
    private void ajustarBolsilloExtensible() {
        int tam = consultasPersonajes.getTamBolsillo(id_partida);
        System.out.println("ajustarBolsilloExtensible() Tamaño bolsillo: "+tam);
        switch (tam) {
            case 10:
                tiendasVista.itemNext.setEnabled(false);
                tiendasVista.itemUndo.setEnabled(false);
                break;
            case 20:
                // SI ESTÁS EN EL 2º BOLSILLO
                switch(indice_bolsillo) {
                    case 1: // SI ESTÁS EN LA 1ª PARTE, PODER PASAR A LA 2ª
                        tiendasVista.itemNext.setEnabled(true);
                        tiendasVista.itemUndo.setEnabled(false);
                        break;
                    case 2:
                        tiendasVista.itemNext.setEnabled(false);
                        tiendasVista.itemUndo.setEnabled(true);
                        break;
                }   break;
            case 30:
                switch(indice_bolsillo) {
                    case 1: // SI ESTÁS EN LA 1ª PARTE, PODER PASAR A LA 2ª
                        tiendasVista.itemNext.setEnabled(true);
                        tiendasVista.itemUndo.setEnabled(false);
                        break;
                    case 2:
                        tiendasVista.itemNext.setEnabled(true);
                        tiendasVista.itemUndo.setEnabled(true);
                        break;
                    case 3:
                        tiendasVista.itemNext.setEnabled(false);
                        tiendasVista.itemUndo.setEnabled(true);
                        break;
                }   break;
            default:
                break;
        }
    }
    
    private void ajustarAguaYEnergia() {
        Personajes p = consultasPersonajes.unPersonaje(id_partida);
        tiendasVista.barEnergia.setMaximum(p.getEnergia_max());
        tiendasVista.barEnergia.setValue(p.getEnergia());
        tiendasVista.barAgua.setMaximum(p.getAgua_max());
        tiendasVista.barAgua.setValue(p.getAgua());
    }
    
    private void generateProducts() {
        productosGlobal = new int[10];
        for (int i = 0; i < 10; i++) {
            productosGlobal[i] = (int)(Math.random()*8+1);
        }
    }

    private void rellenarTabla() {
        modelo.setRowCount(0);
        modelo.setColumnCount(0);
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripción");
        modelo.addColumn("Precio");
        for(int i=0; i<productosGlobal.length; i++) {
            Object[] o = consultasProductos.getProductoParaTienda(productosGlobal[i]);
            int precio = (int) o[2];
            precio += (Math.random()*50+5); // Para que los precios varíen.
            o[2] = precio;
            modelo.addRow(o);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // PONER LOS DATOS DEL PRODUCTO PARA PODER COMPRARLO.
        int row = tiendasVista.tabla.getSelectedRow();
        String nombreProducto = (String) tiendasVista.tabla.getValueAt(row, 0); // COGE EL NOMBRE DEL PRODUCTO QUE HAS PULSADO.
        Productos producto = consultasProductos.getProductoPorNombre(nombreProducto);
        tiendasVista.lblFotoProd.setIcon(new ImageIcon("imagenes/"+producto.getImg()+".png"));
        tiendasVista.btnComprar.setEnabled(true);
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

    private void comprarProducto() {
        int row = tiendasVista.tabla.getSelectedRow();
        String nombreProducto = (String) tiendasVista.tabla.getValueAt(row, 0); // COGE EL NOMBRE DEL PRODUCTO QUE HAS PULSADO.
        int precioReal = (int) tiendasVista.tabla.getValueAt(row, 2);
        Productos producto = consultasProductos.getProductoPorNombre(nombreProducto);
        if(precioReal <= consultasPersonajes.getDineroActual(id_personaje)) {
            // SI TIENES DINERO PARA COMPRARLO...
            System.err.println("Se restan: "+precioReal+" €");
            consultasPersonajes.restarDinero(id_personaje, precioReal);
            insertarProductoEnBolsillo(producto.getId_producto());
            // SI NO SE LO COMPRAS AL VENDEDOR GLOBAL, QUITARLO DE LA TIENDA DE QUIEN SEA EL PRODUCTO.
            if(id_del_personaje_cuya_tienda_estas_viendo != -1) {
                consultasProductosTiendas.quitarProd(id_del_personaje_cuya_tienda_estas_viendo, producto.getId_producto());
                consultasPersonajes.sumarDinero(id_del_personaje_cuya_tienda_estas_viendo, precioReal);
            }
            modelo.removeRow(row);
            tiendasVista.btnComprar.setEnabled(false);
        } else {
            // NO PUEDES COMPRARLO.
            JOptionPane.showMessageDialog(tiendasVista,
                    "No tienes suficiente dinero para comprar este producto.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    private void insertarProductoEnBolsillo(int id_producto) {
        Partidas p = consultasPartidas.unaPartida(id_partida);
        Personajes p2 = consultasPersonajes.unPersonaje(id_partida);
        try {
            int bolsillo = getPrimerBolsilloLibre(p2.getId_personaje());
            // COMPROBAMOS SI PODEMOS GUARDAR MÁS COSAS EN EL BOLSILLO.
            //System.err.println(consultasPersonajes.getTamBolsillo(id_partida)+" > "+consultasHuerto.getNumProductosEnElBolsillo(id_partida));
            if(consultasPersonajes.getTamBolsillo(id_partida) > consultasHuerto.getNumProductosEnElBolsillo(id_partida)){
                consultasHuerto.insertarProductoParcelaPersonaje(p2.getId_personaje(), bolsillo,
                    id_producto, p.getDia(), p.getMes(), p.getAnio());
            } else {
                JOptionPane.showMessageDialog(tiendasVista,
                    "Tienes los bolsillos llenos. Haz espacio antes de recoger más productos.",
                    "Bolsillos llenos", JOptionPane.ERROR_MESSAGE);
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(tiendasVista,
                    "Tienes los bolsillos llenos. Haz espacio antes de recoger más productos. "+e.getMessage(),
                    "Bolsillos llenos", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private int getPrimerBolsilloLibre(int id_personaje) {
        for(int i=1; i<=30; i++) {
            if(!consultasHuerto.hayAlgo(i, id_personaje)) { // Si no hay nada, devuelve esa parcela.
                System.out.println("Libre bolsillo "+i);
                return i;
            }
            System.out.println("Hay algo en el bolsillo "+i);
        }
        return 0;
    }

    private void ponerTiendasDeOtrosJugadores() {
        ArrayList<Tiendas> arrTiendas = consultasTienda.todoTiendas();
        try{
            tiendasVista.PanelTiendas.setLayout(new GridLayout( (int)(arrTiendas.size()/4), (int)(arrTiendas.size()/4)));
        }catch(Exception e){
            tiendasVista.PanelTiendas.setLayout(new GridLayout(1,1));
        }
        for (int i=0; i<arrTiendas.size(); i++) {
            // POR CADA VUELTA DEL BUCLE SE AÑADE UNA TIENDA
            // APARTE DE LA GLOBAL, QUE APARECE A TODOS POR DEFECTO.
            if(arrTiendas.get(i).getId_partida() != id_partida) {
                tiendas.add(arrTiendas.get(i));
                JButton btn = new JButton(arrTiendas.get(i).getNombre());
                btn.addActionListener(this);
                botonesTiendas.add(btn);
                btn.setActionCommand("btn"+i);
                tiendasVista.PanelTiendas.add(btn);
            }
        }
    }
    
}
