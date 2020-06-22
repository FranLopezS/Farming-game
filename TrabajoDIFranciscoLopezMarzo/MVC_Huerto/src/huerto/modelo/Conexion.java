
package huerto.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    private final String BBDD = "huerto";
    private final String USER = "root";
    private final String PASS = "";
    private final String url = "jdbc:mysql://localhost/"+BBDD;
    
    public Connection conectar() {
        Connection conn = null;
        try {
            conn = (Connection) DriverManager.getConnection(url, USER, PASS);
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return conn;
    }
    
}
