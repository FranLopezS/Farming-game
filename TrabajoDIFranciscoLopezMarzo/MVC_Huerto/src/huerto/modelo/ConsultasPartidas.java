package huerto.modelo;

import huerto.vista.AdministracionVista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConsultasPartidas extends Conexion {
    Connection conn = conectar();
    
    public void actualizarNombrePartida(int id_partida, String nombre) {
        PreparedStatement ps = null;
        String sql = "update partidas set nombre_partida = ? "
                + "where id_partida = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setInt(2, id_partida);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new AdministracionVista(),
                    "No ha sido posible actualizar el nombre de la partida.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void actualizarPassw(int id_partida, String passw) {
        PreparedStatement ps = null;
        String sql = "update partidas set password = ? "
                + "where id_partida = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, passw);
            ps.setInt(2, id_partida);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new AdministracionVista(),
                    "No ha sido posible actualizar la contraseña.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void borrarPartida(int id_partida) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "delete from partidas where id_partida = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_partida);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasHuerto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean esAdmin(int id_partida) {
        // DEVUELVE SI EL USER ACTUAL ES ADMIN O NO.
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select admin from partidas where id_partida = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_partida);
            rs = ps.executeQuery();
            while(rs.next()) {
                if(rs.getInt(1) == 1)
                    return true;
                else
                    return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasPartidas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void actualizarFecha(int id_partida, int dia, int mes, int anio) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "update partidas set dia=?, mes=?, anio=? "
                + "where id_partida = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, dia);
            ps.setInt(2, mes);
            ps.setInt(3, anio);
            ps.setInt(4, id_partida);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasPartidas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getPassword(int id_partida) {
        PreparedStatement ps = null;
        String passw = "";
        ResultSet rs = null;
        String sql = "select password from partidas where id_partida = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_partida);
            rs = ps.executeQuery();
            while(rs.next())
                passw = rs.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasPartidas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return passw;
    }
    
    // Para consultar todas las partidas.
    public ArrayList<PartidaPersonaje> todoPartidas() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select partidas.id_partida,nombre_partida,dia,mes,anio,nombre,dinero "
                + "from partidas join personajes "
                + "on (personajes.id_partida = partidas.id_partida)"; // Para que devuelva la última.
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasPartidas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultSetToArrayList(rs);
    }
    
    // Consultar 1 partida.
    public Partidas unaPartida(int id_partida) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Partidas p = null;
        String sql = "select * from partidas where id_partida = ?"; // Para que devuelva la última.
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_partida);
            rs = ps.executeQuery();
            while(rs.next()) {
                p = new Partidas(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getDate(7)
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasPartidas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
    
    private ArrayList<PartidaPersonaje> resultSetToArrayList(ResultSet rs) {
        ArrayList<PartidaPersonaje> partidas = new ArrayList<>();
        try {
            while(rs.next()) {
                partidas.add(new PartidaPersonaje(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getInt(7)
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasPartidas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return partidas;
    }
    
    // Consulta que inserta una partida. También inserta un personaje porque
    // se crean al mismo tiempo.
    public void insertarPartida(Partidas p, Personajes p2) {
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        String sql = "insert into partidas (nombre_partida, password, dia, mes, anio, fecha_creacion, admin) "
                + "values (?,?,?,?,?,?,?)";
        String sql2 = "insert into personajes (nombre,energia,energia_max,agua,agua_max,dinero,tam_bolsillo,id_partida) "
                + "values (?,?,?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, p.getNombre_partida());
            ps.setString(2, p.getPassword());
            ps.setInt(3, p.getDia());
            ps.setInt(4, p.getMes());
            ps.setInt(5, p.getAnio());
            ps.setDate(6, new java.sql.Date(p.getFecha_creacion().getTime()));
            ps.setInt(7, 0);
            ps.executeUpdate();
            
            ps2 = conn.prepareStatement(sql2);
            
            ps2.setString(1, p2.getNombre());
            ps2.setInt(2, p2.getEnergia());
            ps2.setInt(3, p2.getEnergia_max());
            ps2.setInt(4, p2.getAgua());
            ps2.setInt(5, p2.getAgua_max());
            ps2.setInt(6, p2.getDinero());
            ps2.setInt(7, p2.getTam_bolsillo());
            ps2.setInt(8, getIdUltimaPartidaInsertada()); // HAY QUE CONSULTAR EL ID DE PARTIDA.
            ps2.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al insertar partida y personaje",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    public int getIdUltimaPartidaInsertada() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select id_partida from partidas order by id_partida desc limit ?"; // Para que devuelva la última.
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 1);
            rs = ps.executeQuery();
            while(rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    
}
