package VENTA;

import java.sql.*;
import javax.swing.JOptionPane;

public class me_tecnicos {
    
Connection db = null;
Statement st = null;
ResultSet rs = null;
PreparedStatement pst=null;

String id,nom;
String sql;
Boolean a;

//Conexion base datos  
public void conecciondb() {
  try {
      db=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Venta y Mantenimiento de componentes informaticos","postgres","1213jose"); 
         //Acualize ruta .../basedatos,usuario,contraseña
      } catch (SQLException e) {System.out.println("Ocurrio un error : "+e.getMessage());} }

//Ingreso tabla tecnicos
public void inserta_tecnicos() throws SQLException {
conecciondb();
//ingreso de variables
 id=tecnicos.id.getText();
 nom=tecnicos.nombre.getText();
 //consulta sql en postgres
 sql = "insert into tecnicos (id_t,nombre_t) values (?,?)";
 pst = db.prepareStatement(sql);
 //donde se muetra la insercion
 pst.setString(1,id);
 pst.setString(2,nom);
 pst.executeUpdate();
 //mensaje de exito
 JOptionPane.showMessageDialog(null,"Se Guardo..."); 
}

//Actualiza tabla tecnicos
public void actuali_tecnicos() throws SQLException {
if (a==true) { conecciondb();
//ingreso de variables
id=tecnicos.id.getText();
nom=tecnicos.nombre.getText();
//consulta sql en postgres
 sql = "update tecnicos set nombre_t=? where id_t='"+id+"'";
 pst = db.prepareStatement(sql);
 //donde se muetra la insercion
 pst.setString(1,nom);
 pst.executeUpdate();
 //mensaje de exito
 JOptionPane.showMessageDialog(null,"Se Actualizo...");
}
}

//Consulta tabla tecnicos si existe tecnico
public void consulta_tecnicos() throws SQLException {
 conecciondb();
 st = db.createStatement();
 //ingreso de variables
 id=tecnicos.id.getText();
 //consulta sql en postgres
 rs = st.executeQuery("select * from tecnicos where id_t='"+id+"'");
 if (rs.next()) {a=true;
 //donde se muetra la insercion
  tecnicos.nombre.setText(rs.getString(2));
 }
 //mensaje de exito
 else {JOptionPane.showMessageDialog(null,"No Existe...");a=false;} }

//Elimina tabla tecnicos si existe tecnico 
public void elimina_tecnicos() throws SQLException {
 try {
 if (a==true) { conecciondb();
 //consulta sql en postgres
 int resp = JOptionPane.showConfirmDialog(null, "Lo elimina","ALERTA",JOptionPane.YES_NO_OPTION);
 if (resp!=1) {st.execute("delete from tecnicos where id_t='"+id+"'");
 //mensaje de exito
   JOptionPane.showMessageDialog(null,"SE ELIMINO, ya que no tiene relación tabla"); } } 
    }
 //mensaje de exito
 catch (SQLException e) {JOptionPane.showMessageDialog(null,"No se puede eliminar, tiene relación tabla");} }

//Limpiar datos entrada de tecnicos
public void limpia() {
tecnicos.id.setText("");
tecnicos.nombre.setText("");
}
}