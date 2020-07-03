/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huerto.modelo;

import huerto.vista.AdministracionVista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Fran
 */
public class ConsultasPersonajes extends Conexion {
    Connection conn = conectar();
    
    public void actualizarNombre(int id_personaje, String nombre) {
        PreparedStatement ps = null;
        String sql = "update personajes set nombre = ? "
                + "where id_personaje = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setInt(2, id_personaje);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new AdministracionVista(),
                    "No ha sido posible actualizar el nombre del personaje.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void borrarPersonaje(int id_personaje) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "delete from personajes where id_personaje = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_personaje);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasHuerto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void restarDinero(int id_personaje, int dinero) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int dineroActual=getDineroActual(id_personaje);
        int dineroNuevo = dineroActual - dinero;
        String sql = "update personajes set dinero=? where id_personaje=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, dineroNuevo);
            ps.setInt(2, id_personaje);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasPersonajes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setDinero(int id_personaje, int dinero) {
        PreparedStatement ps = null;
        String sql = "update personajes set dinero=? where id_personaje=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, dinero);
            ps.setInt(2, id_personaje);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasPersonajes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sumarDinero(int id_personaje, int dinero) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int dineroActual=getDineroActual(id_personaje);
        int dineroNuevo = dineroActual + dinero;
        String sql = "update personajes set dinero=? where id_personaje=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, dineroNuevo);
            ps.setInt(2, id_personaje);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasPersonajes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getDineroActual(int id_personaje) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select dinero from personajes where id_personaje=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_personaje);
            rs = ps.executeQuery();
            while(rs.next())
                return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasPersonajes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public int getTamBolsillo(int id_partida) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Personajes p = null;
        int n = 0;
        String sql = "select tam_bolsillo from personajes where id_partida = ?"; // Para que devuelva la última.
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_partida);
            rs = ps.executeQuery();
            while(rs.next())
                n = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasPersonajes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public void quitarAgua(int id_personaje, int agua) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int nuevaAgua = getAgua(id_personaje) - agua;
        String sql = "update personajes set agua = ? "
                + "where id_personaje = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, nuevaAgua);
            ps.setInt(2, id_personaje);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasPartidas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setAgua(int id_personaje, int agua) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "update personajes set agua = ? "
                + "where id_personaje = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, agua);
            ps.setInt(2, id_personaje);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasPartidas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getAgua(int id_personaje) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select agua from personajes where id_personaje = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_personaje);
            rs = ps.executeQuery();
            while(rs.next())
                return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasPersonajes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public void quitarEnergia(int id_personaje, int energia) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int nuevaEnergia = getEnergia(id_personaje) - energia;
        String sql = "update personajes set energia = ? "
                + "where id_personaje = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, nuevaEnergia);
            ps.setInt(2, id_personaje);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasPersonajes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setEnergia(int id_personaje, int energia) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "update personajes set energia = ? "
                + "where id_personaje = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, energia);
            ps.setInt(2, id_personaje);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasPersonajes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getEnergia(int id_personaje) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select energia from personajes where id_personaje = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_personaje);
            rs = ps.executeQuery();
            while(rs.next())
                return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasPartidas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    // Consultar 1 personaje.
    public Personajes unPersonaje(int id_partida) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Personajes p = null;
        String sql = "select * from personajes where id_partida = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_partida);
            rs = ps.executeQuery();
            while(rs.next()) {
                p = new Personajes(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(5),
                        rs.getInt(7),
                        rs.getInt(9)
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasPartidas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
}
