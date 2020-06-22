/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huerto.modelo;

import huerto.vista.TiendasVista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Fran
 */
public class ConsultasProductosEnTiendas extends Conexion {
    Connection conn = conectar();
    
    public void borrarProdByIdPersonaje(int id_personaje) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "delete from productos_de_personajes_en_tiendas where id_personaje=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_personaje);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasProductosEnTiendas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<ProductosEnTiendas> getProductosByIdPartida(int id_partida) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<ProductosEnTiendas> productosTiendas = new ArrayList<>();
        String sql = "select productos_de_personajes_en_tiendas.id_personaje,"
                + "productos_de_personajes_en_tiendas.id_producto,"
                + "productos_de_personajes_en_tiendas.id_tienda,"
                + "productos_de_personajes_en_tiendas.precio,productos_de_personajes_en_tiendas.fecha,"
                + "productos_de_personajes_en_tiendas.dia,productos_de_personajes_en_tiendas.mes,"
                + "productos_de_personajes_en_tiendas.anio "
                + "from productos_de_personajes_en_tiendas join personajes "
                + "on (personajes.id_personaje = productos_de_personajes_en_tiendas.id_personaje) "
                + "where personajes.id_partida = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_partida); //id_personaje
            rs = ps.executeQuery();
            while(rs.next()) {
                productosTiendas.add(
                        new ProductosEnTiendas(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                                rs.getInt(4), rs.getDate(5), rs.getInt(6), rs.getInt(7), rs.getInt(8))
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasProductosEnTiendas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productosTiendas;
    }
    
    public ArrayList<ProductosEnTiendas> getProductosByPersonaje(int id_personaje) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<ProductosEnTiendas> productosTiendas = new ArrayList<>();
        String sql = "select * from productos_de_personajes_en_tiendas "
                + "where id_personaje = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_personaje); //id_personaje
            rs = ps.executeQuery();
            while(rs.next()) {
                productosTiendas.add(
                        new ProductosEnTiendas(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                                rs.getInt(4), rs.getDate(5), rs.getInt(6), rs.getInt(7), rs.getInt(8))
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasProductosEnTiendas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productosTiendas;
    }
    
    public void quitarProd(int id_personaje, int id_producto) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "delete from productos_de_personajes_en_tiendas "
                + "where id_personaje = ? and id_producto = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_personaje);
            ps.setInt(2, id_producto);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasProductosEnTiendas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // AÑADE UN PRODUCTO A TU TIENDA.
    public boolean addProd(int id_personaje, int id_tienda, Productos producto, Partidas partida) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "insert into productos_de_personajes_en_tiendas values (?,?,?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_personaje); //id_personaje
            ps.setInt(2, producto.getId_producto()); //id_producto
            ps.setInt(3, id_tienda);
            ps.setInt(4, producto.getPrecio());
            ps.setDate(5, new java.sql.Date(new Date().getTime()));
            ps.setInt(6, partida.getDia()); //dia
            ps.setInt(7, partida.getMes()); //mes
            ps.setInt(8, partida.getAnio()); //año
            try {
                ps.executeUpdate();
                return true;
            } catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException i) {
                JOptionPane.showMessageDialog(new TiendasVista(), "Ya tienes este producto en la tienda. "
                        + "Añade otro distinto.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasProductosEnTiendas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
