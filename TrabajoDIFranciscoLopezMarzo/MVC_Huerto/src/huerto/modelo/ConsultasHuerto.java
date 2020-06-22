/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huerto.modelo;

import huerto.vista.HuertoVista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Fran
 */
public class ConsultasHuerto extends Conexion {
    Connection conn = conectar();
    ConsultasPersonajes consultasPersonajes;
    
    public ConsultasHuerto() {
        consultasPersonajes = new ConsultasPersonajes();
    }
    
    // HACE QUE PASE UN DÍA PARA UN PRODUCTO. NO SIRVE PARA LOS PRODUCTOS CRECIDOS DEL TODO.
    public void pasarDia(int id_personaje, int id_parcela, int regado, int fase, int dias_sin_regar) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql="";
        sql = "update productos_de_personajes_en_parcelas set fase=?,"
                + "regada=?, dias_sin_regar=? where id_personaje=? and id_parcela=?";
        try {
            ps = conn.prepareStatement(sql);
            if(regado == 1) { // SI EL PROD. HA SIDO REGADO.
                ps.setInt(1, fase+1);
                ps.setInt(2, 0); // SE PONE A 0 PARA QUE EL SIG DÍA HAYA QUE REGARLO DE NUEVO.
                ps.setInt(3, 1); // AL PASAR DE DÍA, YA ESTÁ 1 DÍA SIN REGAR.
            } else {
                ps.setInt(1, 4); // LA FASE 4 SIGNIFICA QUE SE HA SECADO.
                ps.setInt(2, 0);
                ps.setInt(3, dias_sin_regar+1);
            }
            ps.setInt(4, id_personaje);
            ps.setInt(5, id_parcela);
           
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasHuerto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // EL ATRIBUTO newIdProd es para asignar un nuevo producto a esa parcela.
    // ESTE MÉTODO SE USA PARA CAMBIAR EL DÍA Y EVOLUCIONAR EL PRODUCTO DE FASE 0 A FASE 1.
    public void pasarDia(int id_personaje, int id_parcela, int regado, int fase, int dias_sin_regar, int newIdProd) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql="";
        sql = "update productos_de_personajes_en_parcelas set fase=?,"
                + "regada=?, dias_sin_regar=?, id_producto=? where id_personaje=? and id_parcela=?";
        try {
            ps = conn.prepareStatement(sql);
            if(regado == 1) { // SI EL PROD. HA SIDO REGADO.
                ps.setInt(1, fase+1);
                ps.setInt(2, 0); // SE PONE A 0 PARA QUE EL SIG DÍA HAYA QUE REGARLO DE NUEVO.
                ps.setInt(3, 1); // AL PASAR DE DÍA, YA ESTÁ 1 DÍA SIN REGAR.
            } else {
                ps.setInt(1, 4); // LA FASE 4 SIGNIFICA QUE SE HA SECADO.
                ps.setInt(2, 0);
                ps.setInt(3, dias_sin_regar+1);
            }
            ps.setInt(4, newIdProd);
            ps.setInt(5, id_personaje);
            ps.setInt(6, id_parcela);
           
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasHuerto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void regar(int id_personaje, int id_parcela) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "update productos_de_personajes_en_parcelas set regada=?, dias_sin_regar=? "
                + "where id_personaje=? and id_parcela=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 1); // REGADA PASA A SER 1 PORQUE HAS REGADO.
            ps.setInt(2, 0); // COMO RIEGAS, DIAS_SIN_REGAR PASA A SER 0.
            ps.setInt(3, id_personaje);
            ps.setInt(4, id_parcela);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasHuerto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getFaseProd(int id_personaje, int id_parcela) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select fase from productos_de_personajes_en_parcelas "
                + "where id_personaje=? and id_parcela=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_personaje);
            ps.setInt(2, id_parcela);
            rs = ps.executeQuery();
            while(rs.next())
                return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasHuerto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public int getRegadoProd(int id_personaje, int id_parcela) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select regada from productos_de_personajes_en_parcelas "
                + "where id_personaje=? and id_parcela=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_personaje);
            ps.setInt(2, id_parcela);
            rs = ps.executeQuery();
            while(rs.next())
                return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasHuerto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public int getDiasSinRegarProd(int id_personaje, int id_parcela) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select dias_sin_regar from productos_de_personajes_en_parcelas "
                + "where id_personaje=? and id_parcela=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_personaje);
            ps.setInt(2, id_parcela);
            rs = ps.executeQuery();
            while(rs.next())
                return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasHuerto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public void borrarObjetoBolsillo(int id_personaje, int id_parcela) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "delete from productos_de_personajes_en_parcelas "
                + "where id_personaje = ? and id_parcela = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_personaje);
            ps.setInt(2, id_parcela);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasHuerto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void borrarParcelasHuerto(int id_personaje) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "delete from productos_de_personajes_en_parcelas where id_personaje = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_personaje);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasHuerto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Cuenta las parcelas para dibujar el gridlayout.
    public int contarParcelas(int id_personaje) {
        PreparedStatement ps = null;
        int n = 0;
        ResultSet rs = null;
        String sql = "select count(*) from productos_de_personajes_en_parcelas "
                + "where id_personaje = ? "
                + "and id_parcela >= ? "
                + "and id_parcela <= ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_personaje);
            ps.setInt(2, 31);
            ps.setInt(3, 94);
            rs = ps.executeQuery();
            while(rs.next())
                n = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasHuerto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    // INSERTA LAS PRIMERAS 4 PARCELAS DE ESTE PERSONAJE.
    public void insertarPrimerasParcelas(int id_personaje) {
        for(int i=31; i<35; i++) {
            System.out.println("Se inserta parcela "+i+" del personaje "+id_personaje);
        
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "insert into productos_de_personajes_en_parcelas "
                    + "(id_personaje, id_parcela, id_producto, fecha, dia, mes, anio,dias_sin_regar,regada,fase) values "
                    + "(?,?,?,?,?,?,?,?,?,?)";
            try {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id_personaje);
                System.out.println("Vuelta "+(i-30)+". ID Personaje: "+id_personaje);
                ps.setInt(2, i);
                ps.setInt(3, 17);
                //System.out.println("Se intenta insertar el producto "+id_producto+" de "+id_personaje+" en la parcela "+id_parcela+".");
                ps.setDate(4, new java.sql.Date(new Date().getTime()));
                ps.setInt(5, 1);
                ps.setInt(6, 1);
                ps.setInt(7, 1);
                ps.setInt(8, 0);
                ps.setInt(9, 0);
                ps.setInt(10, 0);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ConsultasHuerto.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
    }
    
    public void actualizarProductoParcela(int id_personaje, int id_parcela,
            int id_producto, int dia, int mes, int anio, int regada, int fase, int dias_sin_regar) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "update productos_de_personajes_en_parcelas set "
                + "id_personaje=?, id_parcela=?, id_producto=?, fecha=?, dia=?, mes=?, anio=?, "
                + "regada=?, fase=?, dias_sin_regar=? "
                + "where id_personaje=? and id_parcela=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_personaje);
            ps.setInt(2, id_parcela);
            ps.setInt(3, id_producto);
            //System.out.println("Se intenta insertar el producto "+id_producto+" de "+id_personaje+" en la parcela "+id_parcela+".");
            ps.setDate(4, new java.sql.Date(new Date().getTime()));
            ps.setInt(5, dia);
            ps.setInt(6, mes);
            ps.setInt(7, anio);
            ps.setInt(8, regada);
            ps.setInt(9, fase);
            ps.setInt(10, dias_sin_regar);
            ps.setInt(11, id_personaje);
            ps.setInt(12, id_parcela);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasHuerto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertarProductoParcelaPersonaje(int id_personaje, int id_parcela,
            int id_producto, int dia, int mes, int anio) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "insert into productos_de_personajes_en_parcelas "
                + "(id_personaje,id_parcela,id_producto,fecha,dia,mes,anio,dias_sin_regar,regada,fase) values "
                + "(?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_personaje);
            ps.setInt(2, id_parcela);
            ps.setInt(3, id_producto);
            //System.out.println("Se intenta insertar el producto "+id_producto+" de "+id_personaje+" en la parcela "+id_parcela+".");
            ps.setDate(4, new java.sql.Date(new Date().getTime()));
            ps.setInt(5, dia);
            ps.setInt(6, mes);
            ps.setInt(7, anio);
            ps.setInt(8, 0);
            ps.setInt(9, 0);
            ps.setInt(10, 0);
            try {
                ps.executeUpdate();
            } catch(Exception e) {
                JOptionPane.showMessageDialog(new HuertoVista(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasHuerto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Mira una parcela y devuelve si ve algo. Vale para comprobar bolsillos y parcelas, pq se guardan en la misma tabla.
    public boolean hayAlgo(int id_parcela, int id_personaje) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        System.out.println("se comprueba el bolsillo "+id_parcela);
        String sql = "select count(*) from productos_de_personajes_en_parcelas "
                + "where id_parcela = ? and id_personaje = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_parcela);
            ps.setInt(2, id_personaje);
            rs = ps.executeQuery();
            while(rs.next()) {
                if(rs.getInt(1) == 1)
                    return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasHuerto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    // Devuelve el producto entero de una parcela. Lo uso para cuando haces click en una parcela.
    public Productos getProducto(int id_parcela, int id_personaje) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select p.id_producto, p.nombre, p.precio, p.img, "
                + "p.descripcion, p.id_tipo_producto from productos_de_personajes_en_parcelas join productos p "
                + "on (productos_de_personajes_en_parcelas.id_producto = p.id_producto) "
                + "where productos_de_personajes_en_parcelas.id_personaje = ? " // donde el id_personaje sea x
                + "and productos_de_personajes_en_parcelas.id_parcela = ?"; // y la parcela sea x
        //System.out.println(sql);
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_personaje);
            ps.setInt(2, id_parcela);
            rs = ps.executeQuery();
            while(rs.next()) {
                return new Productos
                (
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6)
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasHuerto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String getImgProducto(int id_parcela_bolsillo, int id_personaje) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select productos.img from productos_de_personajes_en_parcelas join productos "
                + "on (productos_de_personajes_en_parcelas.id_producto = productos.id_producto) "
                + "where productos_de_personajes_en_parcelas.id_personaje = ? " // donde el id_personaje sea x
                + "and productos_de_personajes_en_parcelas.id_parcela = ?"; // y la parcela sea x
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_personaje);
            ps.setInt(2, id_parcela_bolsillo);
            rs = ps.executeQuery();
            while(rs.next())
                return rs.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasHuerto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "nada";
    }
    
    // Devuelve el número de productos que tienes en el bolsillo.
    public int getNumProductosEnElBolsillo(int id_partida) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int num = 0;
        Personajes p = consultasPersonajes.unPersonaje(id_partida);
        // las parcelas 1 .. 30 representan los bolsillos del personaje.
        String sql = "select count(*) from productos_de_personajes_en_parcelas "
                + "where id_personaje = ? and id_parcela >= ? and id_parcela <= ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, p.getId_personaje());
            ps.setInt(2, 1);
            ps.setInt(3, 30);
            rs = ps.executeQuery();
            while(rs.next())
                num = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasHuerto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return num;
    }
    
    // SABER CUÁNTOS CULTIVOS TIENES PLANTADOS.
    public int getNumCultivos(int id_partida) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int num = 0;
        Personajes p = consultasPersonajes.unPersonaje(id_partida);
        // las parcelas 1 .. 30 representan los bolsillos del personaje.
        String sql = "select count(*) from productos_de_personajes_en_parcelas "
                + "where id_personaje = ? and id_parcela >= ? and id_parcela <= ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, p.getId_personaje());
            ps.setInt(2, 31);
            ps.setInt(3, 94);
            rs = ps.executeQuery();
            while(rs.next())
                num = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasHuerto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return num;
    }
}
