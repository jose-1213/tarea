package VENTA;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JDialog;

public class me_mantenimiento {
Connection db = null;
Statement st = null;
ResultSet rs = null;
PreparedStatement pst=null;

String id_m,id_t,dan,nom;
Integer adq ;
java.sql.Date fecha, garan;

Boolean a;
String sql;

DefaultTableModel m = new DefaultTableModel();

//Conexion base datos  
DBcon DB = new DBcon();
Connection conexion =DB.conectarDB();

public void conecciondb() {
  try {
      db=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Venta y Mantenimiento de componentes informaticos","postgres","1213jose");    
     //Acualize ruta .../basedatos,usuario,contraseña
     } catch (SQLException e) {System.out.println("Ocurrio un error : "+e.getMessage());}
}

//Ingreso tabla mantenimiento
public void inserta_producto() throws SQLException {
    //conexion a la base de dato 
 conecciondb();
 // funcion de llamar a la transaccion
 try{
     // ingreso de datos 
     id_m=mantenimiento.id_m.getText();
     id_t=mantenimiento.id_t.getText();
     dan=mantenimiento.dano.getText();
     adq=Integer.valueOf(mantenimiento.adq.getText());
     fecha = new java.sql.Date(mantenimiento.fecha.getDate().getTime());
     garan=new java.sql.Date(mantenimiento.garan.getDate().getTime());
     //consulta sql en postgres
     sql ="select transaccionuno('"+id_m+"','"+id_t+"','"+dan+"','"+fecha+"','"+garan+"',"+adq+");" ;
     //select transaccionuno('25','003','circuito','2000/12/05','2001/12/05', 150);
      PreparedStatement pstmt = conexion.prepareStatement(sql);
      rs = pstmt.executeQuery();
      //mensaje de confirmacion
      JOptionPane.showMessageDialog(null,"Se Guardo...");
     System.out.println(rs);
    }catch(SQLException e){
        // mensaje de error que se mostrara como alert
         JOptionPane alerta = new JOptionPane(e.getMessage(), JOptionPane.ERROR_MESSAGE);
         JDialog dialog = alerta.createDialog("Warning!");
         dialog.setAlwaysOnTop(true); // to show top of all other application
         dialog.setVisible(true);          
    }
}
}


