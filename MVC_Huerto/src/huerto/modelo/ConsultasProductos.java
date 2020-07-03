/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huerto.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fran
 */
public class ConsultasProductos extends Conexion {
    Connection conn = conectar();
    
    public Object[] getProductoParaTienda(int id_producto) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql="select nombre, descripcion, precio from productos "
                + "where id_producto=?";
        Object[] o = new Object[3];
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_producto);
            rs = ps.executeQuery();
            while(rs.next()) {
                o[0] = rs.getString(1);
                o[1] = rs.getString(2);
                o[2] = rs.getInt(3);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasHuerto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return o;
    }
    
    public Productos getProductoPorNombre(String nombre) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql="select * from productos "
                + "where nombre=?";
        Productos p = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            while(rs.next()) {
                p = new Productos(
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
        return p;
    }
    
}
