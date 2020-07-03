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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Fran
 */
public class ConsultasTienda extends Conexion {
    Connection conn = conectar();
    
    public int getIdTiendaPorIdPartida(int id_partida) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select id_tienda from tiendas where id_partida=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_partida);
            rs = ps.executeQuery();
            while(rs.next())
                return rs.getInt(1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new TiendasVista(), ex.getMessage());
        }
        return 0;
    }
    
    public void borrarTiendaByIdPartida(int id_partida) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Integer> arr = new ArrayList<>();
        String sql = "delete from tiendas where id_partida = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_partida);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasTienda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // DEVUELVE LOS ID PARTIDAS QUE TIENEN TIENDAS (TODAS)
    public ArrayList<Tiendas> todoTiendas() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Tiendas> arr = new ArrayList<>();
        String sql = "select * from tiendas";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                arr.add(new Tiendas(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasTienda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    
    public void addTienda(String nombre, int id_partida) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "insert into tiendas (nombre, id_partida) "
                + "values (?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, "Tienda de "+nombre);
            ps.setInt(2, id_partida);
            System.err.println("SE AÑADE LA TIENDA DE LA PARTIDA DE "+nombre+", de id: "+id_partida);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new TiendasVista(), ex.getMessage());
        }
    }
    
}
