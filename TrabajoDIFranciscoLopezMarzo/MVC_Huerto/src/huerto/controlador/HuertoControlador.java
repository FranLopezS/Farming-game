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
import huerto.modelo.Productos;
import huerto.vista.AdministracionVista;
import huerto.vista.HuertoVista;
import huerto.vista.PartidasVista;
import huerto.vista.TiendasVista;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import relojhuerto.RelojListener;

/**
 *
 * @author Fran
 */
public class HuertoControlador implements ActionListener, RelojListener {
    private HuertoVista huertoVista;
    private ConsultasHuerto consultasHuerto;
    private ConsultasPartidas consultasPartidas;
    private ConsultasPersonajes consultasPersonajes;
    private ConsultasParcelas consultasParcelas;
    private ConsultasTienda consultasTiendas;
    private ConsultasProductosEnTiendas consultasProductosTiendas;
    private ConsultasProductos consultasProductos;
    private int id_partida;
    private JToggleButton[] botonesObjs;
    private int indice_bolsillo; // INDICA QUÉ SECCIÓN DEL BOLSILLO ESTÁS MIRANDO.
    private ArrayList<JButton> parcelas;
    private int ultimaParcelaPulsada;
    private int ultimaSeccionPulsada; // SI ES 0 HAS PULSADO LO ÚLTIMO UN BOTÓN, SI ES 1 UNA PARCELA.
    // USO UN ARRAY DE NOMBRES DE PARCELAS PARA RECORRER TODAS LAS PARCELAS EN EL LISTENER,
    // EN UN CICLO FOR, A VER SI ALGUNA ES PULSADA.
    private String[] nombreParcelas = {"parcela0","parcela1","parcela2","parcela3","parcela4","parcela5"
        ,"parcela6","parcela7","parcela8","parcela9","parcela10","parcela11","parcela12","parcela13","parcela14"
        ,"parcela15","parcela16","parcela17","parcela18","parcela19","parcela20","parcela21","parcela22","parcela23",
        "parcela24","parcela25","parcela26" ,"parcela27","parcela28","parcela29"};
    private int permiso = 0;
    private boolean frameVisible;

    public HuertoControlador(ConsultasPersonajes consultasPersonajes, HuertoVista huertoVista, ConsultasHuerto consultasHuerto,
            ConsultasPartidas consultasPartidas, int id_partida, String nombre_personaje, int permiso,
            ConsultasParcelas consultasParcelas, ConsultasTienda consultasTiendas,
            ConsultasProductosEnTiendas consultasProductosTiendas, ConsultasProductos consultasProductos) {
        this.consultasPersonajes = consultasPersonajes;
        this.huertoVista = huertoVista;
        this.consultasHuerto = consultasHuerto;
        this.consultasPartidas = consultasPartidas;
        this.id_partida = id_partida;
        this.indice_bolsillo = 1;
        this.parcelas = new ArrayList<>();
        this.permiso = permiso;
        this.consultasParcelas = consultasParcelas;
        this.consultasTiendas = consultasTiendas;
        this.consultasProductosTiendas = consultasProductosTiendas;
        this.consultasProductos = consultasProductos;
    }
    
    public void inicio() {
        initButtons(); // Se inicializan los botones. Debe ir antes de inicializarVentana para que no haya nullpointer.
        inicializarVentana();
        ajustarParcelas(); // AJUSTA EL GRIDLAYOUT A LAS PARCELAS EXISTENTES.
        actualizarParcelas();
        ajustarAguaYEnergia();
        ajustarBolsilloExtensible();
        ajustarFecha();
        actualizarDinero();
        comprobarAdmin();
        setHelp();
        huertoVista.btnAddTienda.setVisible(false);
        huertoVista.setExtendedState(JFrame.MAXIMIZED_BOTH);
        huertoVista.setVisible(true);
        frameVisible=true;
    }
    
    private void setHelp() {
        ClassLoader c1 = MainWindow.class.getClassLoader();
        URL hsURL = HelpSet.findHelpSet(c1, "help/help_set.hs");
        try {
            HelpSet helpset = new HelpSet(c1, hsURL);
            HelpBroker hb = helpset.createHelpBroker();
            hb.enableHelpOnButton(huertoVista.ayuda, "index", helpset);
            hb.enableHelpKey(huertoVista.getRootPane(), "index", helpset);
        } catch (HelpSetException ex) {
            Logger.getLogger(AdministracionControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void fin() {
        huertoVista.setVisible(false);
        frameVisible=false;
    }

    private void inicializarVentana() {
        Personajes p = consultasPersonajes.unPersonaje(id_partida);
        huertoVista.lblNombreHuerto.setText(p.getNombre());
        huertoVista.item1.setSelected(true); // Se selecciona el primer objeto por defecto.
        actualizarBolsillo();
    }
    
    private void initButtons() {
        huertoVista.item1.addActionListener(this);
        huertoVista.item2.addActionListener(this);
        huertoVista.item3.addActionListener(this);
        huertoVista.item4.addActionListener(this);
        huertoVista.item5.addActionListener(this);
        huertoVista.item6.addActionListener(this);
        huertoVista.item7.addActionListener(this);
        huertoVista.item8.addActionListener(this);
        huertoVista.item9.addActionListener(this);
        huertoVista.item10.addActionListener(this);
        huertoVista.itemNext.addActionListener(this);
        huertoVista.btnObjeto.addActionListener(this);
        huertoVista.btnBack.addActionListener(this);
        huertoVista.btnBorrarPartida.addActionListener(this);
        huertoVista.btnAddParcela.addActionListener(this);
        huertoVista.itemUndo.addActionListener(this);
        huertoVista.btnRegar.addActionListener(this);
        huertoVista.btnVender.addActionListener(this);
        huertoVista.btnTirar.addActionListener(this);
        huertoVista.btnTienda.addActionListener(this);
        huertoVista.btnAddTienda.addActionListener(this);
        huertoVista.btnEntrarAdmin.addActionListener(this);
        huertoVista.btnAdministrar.addActionListener(this);
        huertoVista.relojHuerto.addRelojListener(this);
        
        if(consultasPartidas.esAdmin(id_partida)) {
            huertoVista.PanelUpHuerto.setBackground(Color.RED);
        } else if(permiso == 1) {
            huertoVista.PanelUpHuerto.setBackground(Color.CYAN);
        }
        
        if(!consultasPartidas.esAdmin(id_partida))
            huertoVista.btnAdministrar.setVisible(false);
        
        botonesObjs = new JToggleButton[10];
        botonesObjs[0] = huertoVista.item1;
        botonesObjs[1] = huertoVista.item2;
        botonesObjs[2] = huertoVista.item3;
        botonesObjs[3] = huertoVista.item4;
        botonesObjs[4] = huertoVista.item5;
        botonesObjs[5] = huertoVista.item6;
        botonesObjs[6] = huertoVista.item7;
        botonesObjs[7] = huertoVista.item8;
        botonesObjs[8] = huertoVista.item9;
        botonesObjs[9] = huertoVista.item10;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Personajes p = consultasPersonajes.unPersonaje(id_partida);
        Partidas par = consultasPartidas.unaPartida(id_partida);
        int id_parcela = 0;
        int energia = 0;
        System.out.println("*LISTENER* Has pulsado: "+e.getActionCommand());
        switch(e.getActionCommand()) {
            case "item1":
                ultimaSeccionPulsada = 0;
                cambiarToggleButtons(new boolean[]{true,false,false,false,false,
                                        false,false,false,false,false});
                ponerDatosBolsilloEnTabla(getBotonOn(), p);
                break;
            case "item2":
                ultimaSeccionPulsada = 0;
                cambiarToggleButtons(new boolean[]{false,true,false,false,false,
                                        false,false,false,false,false});
                ponerDatosBolsilloEnTabla(getBotonOn(), p);
                break;
            case "item3":
                ultimaSeccionPulsada = 0;
                cambiarToggleButtons(new boolean[]{false,false,true,false,false,
                                        false,false,false,false,false});
                ponerDatosBolsilloEnTabla(getBotonOn(), p);
                break;
            case "item4":
                ultimaSeccionPulsada = 0;
                cambiarToggleButtons(new boolean[]{false,false,false,true,false,
                                        false,false,false,false,false});
                ponerDatosBolsilloEnTabla(getBotonOn(), p);
                break;
            case "item5":
                ultimaSeccionPulsada = 0;
                cambiarToggleButtons(new boolean[]{false,false,false,false,true,
                                        false,false,false,false,false});
                ponerDatosBolsilloEnTabla(getBotonOn(), p);
                break;
            case "item6":
                ultimaSeccionPulsada = 0;
                cambiarToggleButtons(new boolean[]{false,false,false,false,false,
                                        true,false,false,false,false});
                ponerDatosBolsilloEnTabla(getBotonOn(), p);
                break;
            case "item7":
                ultimaSeccionPulsada = 0;
                cambiarToggleButtons(new boolean[]{false,false,false,false,false,
                                        false,true,false,false,false});
                ponerDatosBolsilloEnTabla(getBotonOn(), p);
                break;
            case "item8":
                ultimaSeccionPulsada = 0;
                cambiarToggleButtons(new boolean[]{false,false,false,false,false,
                                        false,false,true,false,false});
                ponerDatosBolsilloEnTabla(getBotonOn(), p);
                break;
            case "item9":
                ultimaSeccionPulsada = 0;
                cambiarToggleButtons(new boolean[]{false,false,false,false,false,
                                        false,false,false,true,false});
                ponerDatosBolsilloEnTabla(getBotonOn(), p);
                break;
            case "item10":
                ultimaSeccionPulsada = 0;
                cambiarToggleButtons(new boolean[]{false,false,false,false,false,
                                        false,false,false,false,true});
                ponerDatosBolsilloEnTabla(getBotonOn(), p);
                break;
            case "girasol":
                energia = 10;
                if(sePuedeHacer(10))
                    insertarProductoEnBolsillo((int) (Math.random()*8+1));
                break;
            case "erase":
                if(borrarPartida()) {
                    PartidasControlador c = new PartidasControlador(new ConsultasPartidas(), new PartidasVista());
                    fin();
                    c.inicio();
                }
                break;
            case "back":
                if(permiso == 0) {
                    PartidasControlador c = new PartidasControlador(new ConsultasPartidas(), new PartidasVista());
                    fin();
                    c.inicio();
                } else { // permiso == 1
                    HuertoControlador c = new HuertoControlador(
                        new ConsultasPersonajes(),
                        new HuertoVista(),
                        new ConsultasHuerto(),
                        new ConsultasPartidas(),
                        25,
                        "Admin",
                        0,
                        new ConsultasParcelas(),
                        new ConsultasTienda(),
                        new ConsultasProductosEnTiendas(),
                        new ConsultasProductos()
                );
                    fin();
                    c.inicio();
                }
                
                break;
            case "exit":
                System.exit(0);
                break;
            case "newparcela":
                energia = 50;
                if(sePuedeHacer(energia)) {
                    if(consultasHuerto.contarParcelas(p.getId_personaje()) < 30) {
                        addParcela();
                    } else {
                        JOptionPane.showMessageDialog(huertoVista, "No puedes añadir más de 30 parcelas.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                
                break;
            case "itemnext":
                indice_bolsillo++;
                actualizarBolsillo();
                break;
            case "itemundo":
                indice_bolsillo--;
                actualizarBolsillo();
                break;
            case "shop":
                TiendaControlador c2 = new TiendaControlador(new ConsultasTienda(),
                        new TiendasVista(), id_partida, p.getId_personaje(), p.getNombre(), permiso,
                        new ConsultasPersonajes(), new ConsultasPartidas(), new ConsultasHuerto(),
                        new ConsultasProductos(), new ConsultasProductosEnTiendas());
                fin();
                c2.inicio();
                break;
            case "nextday":
                actualizarDia();
                break;
            case "administrar":
                AdministracionControlador c = new AdministracionControlador(new AdministracionVista(),
                        new ConsultasPersonajes(), new ConsultasPartidas(), new ConsultasHuerto(),
                        id_partida, p.getId_personaje(), p.getNombre());
                fin();
                c.inicio();
                break;
            case "regar":
                energia = 4;
                if(sePuedeHacer(energia)) {
                    if(sePuedeRegar()) {
                        // RIEGAS LA ULTIMA PARCELA PULSADA.
                        consultasHuerto.regar(p.getId_personaje(), ultimaParcelaPulsada);
                        parcelas.get(ultimaParcelaPulsada-31).setBackground(new Color(238,208,168));
                        // DESACTIVAS EL BOTÓN PQ YA HAS REGADO.
                        huertoVista.btnRegar.setEnabled(false);
                    }
                }
                break;
            case "vender":
                energia = 4;
                if(sePuedeHacer(energia)) {
                    // GANAS EL DINERO DE ESE PRODUCTO.
                    Productos productoVender = null;
                    if(ultimaSeccionPulsada == 1) {
                        // SI LO ÚLTIMO QUE HAS PULSADO ES UNA PARCELA, COGE EL PRODUCTO DE LA PARCELA.
                        productoVender = consultasHuerto.getProducto(ultimaParcelaPulsada, p.getId_personaje());
                        // SE SUMA EL DINERO SEGÚN EL PRODUCTO.
                        consultasPersonajes.sumarDinero(p.getId_personaje(), productoVender.getPrecio());
                        // SE BORRA EL PRODUCTO DEL HUERTO.
                        consultasHuerto.actualizarProductoParcela(p.getId_personaje(), 
                            ultimaParcelaPulsada, 17,
                            par.getDia(), par.getMes(), par.getAnio(),
                            0,0,0);
                    } else if(ultimaSeccionPulsada == 0) {
                        // COGE EL PRODUCTO DEL BOTÓN.
                        productoVender = consultasHuerto.getProducto(getBotonOn(), p.getId_personaje());
                        // SE SUMA EL DINERO SEGÚN EL PRODUCTO.
                        consultasPersonajes.sumarDinero(p.getId_personaje(), productoVender.getPrecio());
                        // SE BORRA EL PRODUCTO DEL BOLSILLO.
                        consultasHuerto.borrarObjetoBolsillo(p.getId_personaje(), getBotonOn());
                    }
                    huertoVista.btnVender.setEnabled(false);
                    huertoVista.btnAddTienda.setVisible(false);
                }
                break;
            case "tirar":
                energia = 3;
                if(sePuedeHacer(energia)) {
                    if(ultimaSeccionPulsada == 1) {
                        // SE BORRA EL PRODUCTO DEL HUERTO.
                        consultasHuerto.actualizarProductoParcela(p.getId_personaje(), 
                            ultimaParcelaPulsada, 17,
                            par.getDia(), par.getMes(), par.getAnio(),
                            0,0,0);
                    } else if(ultimaSeccionPulsada == 0) {
                        // SE BORRA EL PRODUCTO DEL BOLSILLO.
                        consultasHuerto.borrarObjetoBolsillo(p.getId_personaje(), getBotonOn());
                    }
                    huertoVista.btnTirar.setEnabled(false);
                    huertoVista.btnVender.setEnabled(false);
                    huertoVista.btnRegar.setEnabled(false);
                    huertoVista.btnTienda.setVisible(false);
                }
                break;
            case "addShop":
                energia = 5;
                if(sePuedeHacer(energia)) {
                    int n = getBotonOn();
                    //SE AÑADE A LA TIENDA EL PRODUCTO Y SE BORRA.
                    // SE BORRA EL PRODUCTO DEL HUERTO.
                    int id_tienda = consultasTiendas.getIdTiendaPorIdPartida(id_partida);
                    Productos productoTienda = consultasHuerto.getProducto(ultimaParcelaPulsada, p.getId_personaje());
                    if(ultimaSeccionPulsada == 1) {
                        // SI SE AÑADE A LA TIENDA.
                        if(consultasProductosTiendas.addProd(p.getId_personaje(), id_tienda,
                                productoTienda, consultasPartidas.unaPartida(id_partida))){
                            // SE BORRA EL PRODUCTO DEL HUERTO.
                            consultasHuerto.actualizarProductoParcela(p.getId_personaje(), 
                                ultimaParcelaPulsada, 17,
                                par.getDia(), par.getMes(), par.getAnio(),
                                0,0,0);
                        }
                    } else if(ultimaSeccionPulsada == 0) {
                        // SE BORRA EL PRODUCTO DEL BOLSILLO.
                        consultasHuerto.borrarObjetoBolsillo(p.getId_personaje(), getBotonOn());
                    }

                    huertoVista.btnTirar.setEnabled(false);
                    huertoVista.btnVender.setEnabled(false);
                    huertoVista.btnRegar.setEnabled(false);
                    huertoVista.btnAddTienda.setVisible(false);
                }
                break;
            case "entrarParcela":
                entrarEnParcelaAjena();
                break;
        }
        
        // Comprobar pulsación de parcelas.
        for(int i=0; i<nombreParcelas.length; i++) {
            if(e.getActionCommand().equals(nombreParcelas[i])) {
                // Si tienes algo PLANTABLE en el bolsillo marcado, se planta.
                // SE RECOGE EL BOTÓN SELECCIONADO Y SEGÚN EL ÍNDICE DE BOLSILLO SE VACÍA UN CAMPO.
                // SE SUMA 1 PORQUE LOS BOLSILLOS EMPIEZAN POR 1, NO POR 0.
                System.out.println("Pulsada parcela "+i);
                id_parcela = i+31;
                pulsarParcela(id_parcela, p, par);
            }
        }
        
        actualizarDinero();
        actualizarBolsillo();
        ajustarBolsilloExtensible();
        actualizarParcelas();
        ajustarAguaYEnergia();
    }

    private void pulsarParcela(int id_parcela, Personajes p, Partidas par) {
        ultimaParcelaPulsada = id_parcela;
        ultimaSeccionPulsada = 1;
        // SI LA FASE DE LA PARCELA ES MENOR A 3...
        if(consultasHuerto.getFaseProd(p.getId_personaje(), id_parcela) < 3) {
            // SE DESACTIVAN LOS BOTONES VENDER Y TIRAR.
            huertoVista.btnVender.setEnabled(false);
            huertoVista.btnTirar.setEnabled(false);
            huertoVista.btnAddTienda.setVisible(false);
            if(consultasHuerto.getRegadoProd(p.getId_personaje(), id_parcela) == 0 &&
                    consultasHuerto.getProducto(id_parcela, p.getId_personaje()).getId_producto() != 17) {
                //SI NO ESTÁ REGADO EL PRODUCTO
                //Y EL ID DE PROD NO ES 17
                huertoVista.btnRegar.setEnabled(true);
            } else {
                // SI SÍ ESTÁ REGADO, NO ACTIVAR EL BTN DE REGAR.
                huertoVista.btnRegar.setEnabled(false);
            }
        } else if(consultasHuerto.getFaseProd(p.getId_personaje(), id_parcela) == 3) {
            // SI ES UN PRODUCTO QUE YA HA CRECIDO DEL TODO
            // PUEDES VENDERLO.
            huertoVista.btnVender.setEnabled(true);
            huertoVista.btnAddTienda.setVisible(true);
            huertoVista.btnTirar.setEnabled(false);
        } else { // SI ES UN PRODUCTO SECO, PUEDES TIRARLO.
            huertoVista.btnVender.setEnabled(false);
            huertoVista.btnTirar.setEnabled(true);
        }
        
        // COGER EL BTN BOLSILLO PULSADO.
        int n = getBotonOn();
        
        // SE COGE LO QUE HAY EN LA PARCELA A VER SI HAY ALGO PLANTADO.
        Productos prod_parcela = consultasHuerto.getProducto(id_parcela, p.getId_personaje());
        
        try {
            Productos prodBolsillo = consultasHuerto.getProducto(n, p.getId_personaje());
            System.out.println("El bolsillo tiene: "+prodBolsillo.getNombre());
            
            if(prodBolsillo.getId_tipo_producto() == 1 && prod_parcela.getId_producto() == 17) {
                int energia = 15;
                if(sePuedeHacer(energia)) {
                    // SI ES PLANTABLE LO QUE HAY EN EL BOLSILLO Y EN LA PARCELA NO HAY NADA PLANTADO...
                    quitarProductoSeleccionado();
                    // SE PLANTA EL PRODUCTO DE LA SEMILLA.
                    // POR EJEMPLO, SI USAS SEMILLA DE AJO PARA PLANTAR, SE PLANTA UN AJO
                    consultasHuerto.actualizarProductoParcela(p.getId_personaje(), id_parcela,
                            getIdProductoDeSemilla(prodBolsillo.getId_producto()), par.getDia(),
                            par.getMes(), par.getAnio(),0,0,0);
                }
            }
        } catch(Exception e1) {
            System.err.println("No hay objeto en el bolsillo.");
        }
        ponerDatosEnTabla(id_parcela, p);
    }

    private void ponerDatosEnTabla(int id_parcela, Personajes p) {
        //La parcela0 mira la plantacion 31 en la BBDD, etc.
        // Coger info de la plantacion 0 (en la bbdd es +31, es decir, la plantacion 31).
        Productos prod = consultasHuerto.getProducto(id_parcela, p.getId_personaje());
        huertoVista.lblDescNombre.setText(prod.getNombre());
        huertoVista.lblDescDesc.setText(prod.getDescripcion());
        huertoVista.lblDescFase.setText(""+consultasHuerto.getFaseProd(p.getId_personaje(), id_parcela));
    }
    
    private void ponerDatosBolsilloEnTabla(int id_bolsillo, Personajes p) {
        //La parcela0 mira la plantacion 31 en la BBDD, etc.
        // Coger info de la plantacion 0 (en la bbdd es +31, es decir, la plantacion 31).
        Productos prod = consultasHuerto.getProducto(id_bolsillo, p.getId_personaje());
        if(prod != null) {
            huertoVista.lblDescNombre.setText(prod.getNombre());
            huertoVista.lblDescDesc.setText(prod.getDescripcion());
            huertoVista.lblDescFase.setText(""+consultasHuerto.getFaseProd(p.getId_personaje(), id_bolsillo));
            
            // DETERMINAR SI SE ACTIVA EL BOTÓN DE VENDER.
            if(prod.getPrecio() > 0) {
                // SI EL PRECIO ES MAYOR QUE 0 ES QUE LO PUEDES VENDER.
                huertoVista.btnVender.setEnabled(true);
                huertoVista.btnAddTienda.setVisible(true);
            } else {
                huertoVista.btnVender.setEnabled(false);
                huertoVista.btnAddTienda.setVisible(false);
            }
        } else { // BOLSILLO VACÍO.
            huertoVista.lblDescNombre.setText("Vacío.");
            huertoVista.lblDescDesc.setText("Vacío.");
            huertoVista.lblDescFase.setText("Vacío.");
            huertoVista.btnVender.setEnabled(false);
            huertoVista.btnAddTienda.setVisible(false);
        }
        
    }
    
    // Desactiva todos los demás toggle buttons cuando activas uno.
    // Sólo puedes tener un objeto activo a la vez.
    private void cambiarToggleButtons(boolean[] b) {
        for(int i=0; i<botonesObjs.length; i++) {
            botonesObjs[i].setSelected(b[i]);
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
                JOptionPane.showMessageDialog(huertoVista,
                    "Tienes los bolsillos llenos. Haz espacio antes de recoger más productos.",
                    "Bolsillos llenos", JOptionPane.ERROR_MESSAGE);
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(huertoVista,
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

    private void ajustarParcelas() {
        // EJEMPLO: SI TIENES 5 PARCELAS.
        // SE DIVIDE ENTRE 2: 5/2 = 2.5
        // rows: 2.5 SE REDONDEA AL ALZA --> 3
        // cols: 2.5 SE REDONDEA A LA BAJA --> 2
        // EL GRID LAYOUT DEBE SER DE 3x2
        // SE DESACTIVAN LAS PARCELAS QUE NO PUEDAN SER USADAS.
        huertoVista.setVisible(false);
        
        Personajes p2 = consultasPersonajes.unPersonaje(id_partida);
        int num_parcelas = consultasHuerto.contarParcelas(p2.getId_personaje());
        //System.out.println("ajustarParcelas(): Nº de parcelas: "+num_parcelas);
        int rows = (int) Math.ceil((num_parcelas/4));
        int cols = (int) Math.floor((num_parcelas/4));
        // POR SI ROWS O COLS SALE 0.
        try {
            huertoVista.PanelGrid.setLayout(new GridLayout(rows, cols));
        } catch(Exception e) {
            huertoVista.PanelGrid.setLayout(new GridLayout(1, 1));
        }
        for(int i=0; i<num_parcelas; i++) { // SE AÑADEN 5 LABELS.
            JButton btn = new JButton();
            btn.setActionCommand("parcela"+i);
            if(consultasHuerto.getRegadoProd(p2.getId_personaje(), i+31) == 0)
                btn.setBackground(new java.awt.Color(196, 164, 121));
            else
                btn.setBackground(new java.awt.Color(238,208,168));
            btn.addActionListener(this);
            parcelas.add(btn);
            //System.out.println("Se añade una parcela al arraylist de botones de parcelas. "+parcelas.get(i).getActionCommand());
            huertoVista.PanelGrid.add(btn);
        }
        
        huertoVista.setVisible(true);
    }
    
    private void ajustarXParcelas(int x) {
        // EJEMPLO: SI TIENES 5 PARCELAS.
        // SE DIVIDE ENTRE 2: 5/2 = 2.5
        // rows: 2.5 SE REDONDEA AL ALZA --> 3
        // cols: 2.5 SE REDONDEA A LA BAJA --> 2
        // EL GRID LAYOUT DEBE SER DE 3x2
        // SE DESACTIVAN LAS PARCELAS QUE NO PUEDAN SER USADAS.
        huertoVista.setVisible(false);
        
        Personajes p2 = consultasPersonajes.unPersonaje(id_partida);
        int num_parcelas = consultasHuerto.contarParcelas(p2.getId_personaje());
        System.out.println("ajustarParcelas(): Nº de parcelas: "+num_parcelas);
        int rows = (int) Math.ceil((num_parcelas/4));
        int cols = (int) Math.ceil((num_parcelas/4));
        // SI LAS ROWS O COLS SALEN 0 DA ERROR, ASÍ QUE LO CAPTURO Y PONGO AMBOS A 1.
        try {
            huertoVista.PanelGrid.setLayout(new GridLayout(rows, cols));
        }catch(Exception e) {
            huertoVista.PanelGrid.setLayout(new GridLayout(1, 1));
        }
        for(int i=0; i<x; i++) { // SE AÑADEN 5 LABELS.
            JButton btn = new JButton();
            btn.setActionCommand("parcela"+(num_parcelas-1));
            if(consultasHuerto.getRegadoProd(p2.getId_personaje(), i+31) == 0)
                btn.setBackground(new java.awt.Color(196, 164, 121));
            else
                btn.setBackground(new java.awt.Color(238,208,168));
            btn.addActionListener(this);
            parcelas.add(btn);
            huertoVista.PanelGrid.add(btn);
        }
        
        huertoVista.setVisible(true);
    }

    private boolean borrarPartida() {
        if(JOptionPane.showConfirmDialog(huertoVista,
                "¿Seguro de que quieres borrar la partida? Se perderá todo el progreso.") == 0) {
            if(JOptionPane.showConfirmDialog(huertoVista,
                    "¿Estás totalmente seguro? Piénsatelo igual.") == 0) {
                if(JOptionPane.showConfirmDialog(huertoVista,
                        "¿SEGURÍSIMO SEGURO DEL TODO?") == 0) {
                    Personajes p2 = consultasPersonajes.unPersonaje(id_partida);
                    consultasHuerto.borrarParcelasHuerto(p2.getId_personaje());
                    consultasProductosTiendas.borrarProdByIdPersonaje(p2.getId_personaje());
                    consultasPersonajes.borrarPersonaje(p2.getId_personaje());
                    consultasTiendas.borrarTiendaByIdPartida(id_partida);
                    consultasPartidas.borrarPartida(id_partida);
                    JOptionPane.showMessageDialog(huertoVista, "Se ha borrado la partida.",
                            "Adiós", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                }
            }
        }
        return false;
    }

    private void addParcela() {
        Personajes p = consultasPersonajes.unPersonaje(id_partida);
        Partidas pa = consultasPartidas.unaPartida(id_partida);
        int num_parcela = getParcelaLibre(p.getId_personaje());
        
        if(num_parcela != 0) {
            consultasHuerto.insertarProductoParcelaPersonaje(p.getId_personaje(),
                num_parcela, 17, pa.getDia(), pa.getMes(), pa.getAnio());
            JOptionPane.showMessageDialog(huertoVista, "Se ha añadido una parcela.", "Información",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(huertoVista, "No puedes añadir más parcelas.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        ajustarXParcelas(1);
    }
    
    private int getParcelaLibre(int id_personaje) {
        for(int i=31; i<=94; i++) {
            if(!consultasHuerto.hayAlgo(i, id_personaje))
                return i;
        }
        return 0;
    }
    
    // no va bien x el constructor de personajes
    private void ajustarAguaYEnergia() {
        Personajes p = consultasPersonajes.unPersonaje(id_partida);
        huertoVista.barEnergia.setMaximum(p.getEnergia_max());
        huertoVista.barEnergia.setValue(p.getEnergia());
        huertoVista.barAgua.setMaximum(p.getAgua_max());
        huertoVista.barAgua.setValue(p.getAgua());
    }

    private void ajustarBolsilloExtensible() {
        int tam = consultasPersonajes.getTamBolsillo(id_partida);
        System.out.println("ajustarBolsilloExtensible() Tamaño bolsillo: "+tam);
        switch (tam) {
            case 10:
                huertoVista.itemNext.setEnabled(false);
                huertoVista.itemUndo.setEnabled(false);
                break;
            case 20:
                // SI ESTÁS EN EL 2º BOLSILLO
                switch(indice_bolsillo) {
                    case 1: // SI ESTÁS EN LA 1ª PARTE, PODER PASAR A LA 2ª
                        huertoVista.itemNext.setEnabled(true);
                        huertoVista.itemUndo.setEnabled(false);
                        break;
                    case 2:
                        huertoVista.itemNext.setEnabled(false);
                        huertoVista.itemUndo.setEnabled(true);
                        break;
                }   break;
            case 30:
                switch(indice_bolsillo) {
                    case 1: // SI ESTÁS EN LA 1ª PARTE, PODER PASAR A LA 2ª
                        huertoVista.itemNext.setEnabled(true);
                        huertoVista.itemUndo.setEnabled(false);
                        break;
                    case 2:
                        huertoVista.itemNext.setEnabled(true);
                        huertoVista.itemUndo.setEnabled(true);
                        break;
                    case 3:
                        huertoVista.itemNext.setEnabled(false);
                        huertoVista.itemUndo.setEnabled(true);
                        break;
                }   break;
            default:
                break;
        }
    }

    private void quitarProductoSeleccionado() {
        Personajes p = consultasPersonajes.unPersonaje(id_partida);
        int n = getBotonOn(); // SE RECOGE EL BOTÓN SELECCIONADO Y SEGÚN EL ÍNDICE DE BOLSILLO SE VACÍA UN CAMPO.
        switch(indice_bolsillo) {
            case 1: // borras n+1
                consultasHuerto.borrarObjetoBolsillo(p.getId_personaje(), n);
                break;
            case 2: // borras n+11
                consultasHuerto.borrarObjetoBolsillo(p.getId_personaje(), n);
                break;
            case 3: // borras n+21
                consultasHuerto.borrarObjetoBolsillo(p.getId_personaje(), n);
                break;
        }
    }
    
    private int getBotonOn() {
        // SIEMPRE VA A HABER UN BOTÓN SELECCIONADO.
        for(int i=0; i<10; i++) { // SI EMPIEZO EL ARRAY EN 1 HASTA 10 DA ERROR
            if(botonesObjs[i].isSelected()){
                switch (indice_bolsillo) {
                    case 1:
                        return i+1;
                    case 2:
                        return i+11;
                    case 3:
                        return i+21;
                    default:
                        break;
                }
            }
        }
        return 0;
    }

    private void ajustarFecha() {
        Partidas p = consultasPartidas.unaPartida(id_partida);
        huertoVista.lblFecha.setText("* Día "+p.getDia()+" * Mes "+p.getMes()+" * Año "+p.getAnio()+" *");
    }

    private void actualizarDia() {
        frameVisible=false;
        huertoVista.setVisible(false);
        Partidas p = consultasPartidas.unaPartida(id_partida);
        Personajes per = consultasPersonajes.unPersonaje(id_partida);
        
        consultasPersonajes.setEnergia(per.getId_personaje(), 100);
        consultasPersonajes.setAgua(per.getId_personaje(), 100);
        
        int dia=p.getDia(),mes = p.getMes(),anio = p.getAnio();
        if(p.getDia() == 19) {
            dia = 1;
            if(p.getMes() == 4) {
                mes = 1;
                anio = p.getAnio()+1;
            } else {
                mes = p.getMes()+1;
            }
        } else {
            dia = p.getDia()+1;
        }
        
        consultasPartidas.actualizarFecha(id_partida, dia, mes, anio);
        
        // EVOLUCIONAR CULTIVOS.
        // LAS FASES DE CRECIMIENTOS DE LOS CULTIVOS SON LAS 0, 1 Y 2. LA 3 ES CRECIDO DEL TODO Y LA 4 ES QUE SE HA SECADO.
        int num_cultivos = consultasHuerto.getNumCultivos(id_partida);
        // LA i DEL FOR ES EL Nº DE PARCELA.
        for(int i=31; i<(num_cultivos+31); i++) {
            Productos prod = consultasHuerto.getProducto(i, per.getId_personaje());
            if(prod.getId_producto() != 17) { // SI HAY ALGO PLANTADO...
                if(consultasHuerto.getFaseProd(per.getId_personaje(), i) < 3) { // SI LA FASE DEL PROD. ES MENOR QUE 3...
                // SE PASA EL DÍA PARA EL PRODUCTO PLANTADO.
                    consultasHuerto.pasarDia(per.getId_personaje(), i, // PERSONAJE, PARCELA
                        consultasHuerto.getRegadoProd(per.getId_personaje(), i),
                        consultasHuerto.getFaseProd(per.getId_personaje(), i),
                        consultasHuerto.getDiasSinRegarProd(per.getId_personaje(), i));
                }
            }
        }
        
        ajustarFecha();
        JOptionPane.showMessageDialog(huertoVista, "¡Siguiente día!");
        huertoVista.setVisible(true);
        frameVisible=true;
        actualizarDinero();
        actualizarBolsillo();
        ajustarBolsilloExtensible();
        actualizarParcelas();
        ajustarAguaYEnergia();
    }

    private void actualizarParcelas() {
        Personajes per = consultasPersonajes.unPersonaje(id_partida);
        // CUENTO EL NÚMERO DE PARCELAS PARA HACER UN FOR ACTUALIZANDO TODAS.
        // SUMO 31 PORQUE LA 1º PARCELA ES LA NÚMERO 31 EN LA BBDD.
        try {
            int n = consultasHuerto.contarParcelas(per.getId_personaje()) + 31;
            for(int i=31; i<n; i++) {
                Productos p = consultasHuerto.getProducto(i, per.getId_personaje());
                // ACCEDO A LOS BOTONES DE LAS PARCELAS, QUE ESTÁN EN UN ARRAYLIST PARA PODER ACCEDER A ELLOS.
                if(consultasHuerto.getFaseProd(per.getId_personaje(), i) == 4) {
                    parcelas.get(i-31).setIcon(new ImageIcon("src/imagenes/seco.png"));
                    //parcelas.get(i-31).setIcon();
                } else { // Si la parcela no es de fase 4, pone el producto. Si sí lo es, pone una rama seca.
                    parcelas.get(i-31).setIcon(new ImageIcon("src/imagenes/"+p.getImg()+""
                            +consultasHuerto.getFaseProd(per.getId_personaje(), i)+".png"));
                }
                // SI UNA PARCELA ESTÁ REGADA, SU COLOR ES MÁS CLARO.
                if(consultasHuerto.getRegadoProd(per.getId_personaje(), i) == 0)
                    parcelas.get(i-31).setBackground(new java.awt.Color(196, 164, 121));
                else
                    parcelas.get(i-31).setBackground(new java.awt.Color(238,208,168));
            }
        } catch(Exception e) {
            System.err.println("No se han podido actualizar las parcelas, pero no pasa nada.");
        }
        
    }
    
    // DEVUELVE EL ID DE PRODUCTO DE LA SEMILLA QUE SE PASA COMO ATRIBUTO.
    private int getIdProductoDeSemilla(int id_semilla) {
        return id_semilla+8;
    }

    private void actualizarDinero() {
        try {
            Personajes p = consultasPersonajes.unPersonaje(id_partida);
            huertoVista.lblDinero.setText(consultasPersonajes.getDineroActual(p.getId_personaje())+" €");
        } catch(Exception e) {
            System.err.println("No se ha podido actualizar el dinero. No pasa nada.");
            //e.printStackTrace();
        }
    }

    private void comprobarAdmin() {
        if(!consultasPartidas.esAdmin(id_partida) && permiso == 0){
            huertoVista.comboParcelas.setVisible(false);
            huertoVista.btnEntrarAdmin.setVisible(false);
        } else {
            ArrayList<PartidaPersonaje> partidas = consultasPartidas.todoPartidas();
            for(int i=0; i<partidas.size(); i++) {
                if(!partidas.get(i).getNombre_partida().equalsIgnoreCase("Admin"))
                    huertoVista.comboParcelas.addItem(partidas.get(i).getId_partida()+"#"+partidas.get(i).getNombre());
            }
        }
    }

    private void entrarEnParcelaAjena() {
        // COJO EL ID DE PARTIDA A CUYA PARCELA ACCEDO.
        String cad = huertoVista.comboParcelas.getSelectedItem().toString();
        int id = Integer.valueOf(cad.split("#")[0]);
        Partidas p = consultasPartidas.unaPartida(id);
        HuertoControlador c = new HuertoControlador(
                        new ConsultasPersonajes(),
                        new HuertoVista(),
                        new ConsultasHuerto(),
                        new ConsultasPartidas(),
                        id,
                        p.getNombre_partida(),
                        1,
                        new ConsultasParcelas(),
                        new ConsultasTienda(),
                        new ConsultasProductosEnTiendas(),
                        new ConsultasProductos()
                );
        fin();
        c.inicio();
    }
    
    // PARA COMPROBAR SI SE PUEDE REALIZAR ALGUNA ACTIVIDAD SEGÚN TU ENERGÍA.
    private boolean sePuedeHacer(int energiaUsada) {
        Personajes p = consultasPersonajes.unPersonaje(id_partida);
        int energiaActual = consultasPersonajes.getEnergia(p.getId_personaje());
        // SI SE PUEDE HACER...
        if(energiaUsada <= energiaActual) {
            consultasPersonajes.quitarEnergia(p.getId_personaje(), energiaUsada);
            return true;
        } else {
            JOptionPane.showMessageDialog(huertoVista, "No tienes suficiente energía para realizar esta acción",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    private boolean sePuedeRegar() {
        Personajes p = consultasPersonajes.unPersonaje(id_partida);
        int aguaActual = consultasPersonajes.getAgua(p.getId_personaje());
        
        // CALCULAR CUANTA AGUA SE GASTA.
        int faseProd = consultasHuerto.getFaseProd(p.getId_personaje(), ultimaParcelaPulsada);
        int aguaUsada = 0;
        switch (faseProd) {
            case 0:
                aguaUsada = 25;
                break;
            case 1:
                aguaUsada = 20;
                break;
            default:
                aguaUsada = 15;
                break;
        }
        
        // SI SE PUEDE HACER...
        if(aguaUsada <= aguaActual) {
            consultasPersonajes.quitarAgua(p.getId_personaje(), aguaUsada);
            return true;
        } else {
            JOptionPane.showMessageDialog(huertoVista, "No tienes suficiente agua para regar.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public void cambioDia() {
        if(frameVisible) actualizarDia();
    }
    
}
