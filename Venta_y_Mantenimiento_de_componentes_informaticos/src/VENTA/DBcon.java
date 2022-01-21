
package VENTA;
import java.sql.*;
import javax.swing.JOptionPane;

public class DBcon {
    //Método
    public Connection conectarDB() {
        //Variables
        String database = "jdbc:postgresql://localhost:5432/Venta y Mantenimiento de componentes informaticos";
        String user = "postgres";
        String password = "1213jose";
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(database, user, password);
            
            //JOptionPane.showMessageDialog(null, "Conexión satisfactoria a la base de datos!");
            
        } catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return conexion;
    }
}
