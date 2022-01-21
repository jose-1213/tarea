package VENTA;

import java.sql.*;
import javax.swing.JOptionPane;

public class me_modelo {
    
Connection db = null;
Statement st = null;
ResultSet rs = null;
PreparedStatement pst=null;

Integer id;
String nom;
String sql;
Boolean a;

//Conexion base datos 
public void conecciondb() {
  try {
      db=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Venta y Mantenimiento de componentes informaticos","postgres","1213jose"); 
          //Acualize ruta .../basedatos,usuario,contraseña
      } catch (SQLException e) {System.out.println("Ocurrio un error : "+e.getMessage());} }

//Ingreso  tabla modelo
public void inserta_modelo() throws SQLException {
if (a==false) { conecciondb();
//ingreso de variables
id=Integer.valueOf(modelo.id.getText());
nom=modelo.Nombre.getText();
//consulta sql en postgres
 sql = "insert into modelo (id_mo,nombre_mo) values (?,?)";
 pst = db.prepareStatement(sql);
 //donde se muetra la insercion 
 pst.setInt(1,id);
 pst.setString(2,nom);
 pst.executeUpdate();
 // mensaje de exito 
 JOptionPane.showMessageDialog(null,"Se Guardo..."); } }

//Actualiza tabla modelo
public void actuali_modelo() throws SQLException {
if (a==true) { conecciondb();
//ingreso de variables
id=Integer.valueOf(modelo.id.getText());
nom=modelo.Nombre.getText();
//consulta sql en postgres
 sql = "update modelo set nombre_mo=? where id_mo='"+id+"'";
 pst = db.prepareStatement(sql);
 //donde se muetra la insercion 
 pst.setString(1,nom);
 pst.executeUpdate();
 // mensaje de exito
 JOptionPane.showMessageDialog(null,"Se Actualizo...");
}
}

//Consulta tabla modelo si existe el modelo
public void consulta_modelo() throws SQLException {
 conecciondb();
 //ingreso de variables
 st = db.createStatement();
 id=Integer.valueOf(modelo.id.getText());
 //consulta sql en postgres
 rs = st.executeQuery("select * from modelo where id_mo='"+id+"'");
 if (rs.next()) {a=true;
  modelo.Nombre.setText(rs.getString(2));
 }
 // mensaje de exito
 else {JOptionPane.showMessageDialog(null,"No Existe...");a=false;} }

//Elimina tabla modelo si existe el modelo 
public void elimina_modelo() throws SQLException {
 try {
 if (a==true) { conecciondb();
 //consulta sql en postgres
 int resp = JOptionPane.showConfirmDialog(null, "Lo elimina","ALERTA",JOptionPane.YES_NO_OPTION);
 if (resp!=1) {st.execute("delete from modelo where id_mo='"+id+"'");
 // mensaje de exito
   JOptionPane.showMessageDialog(null,"SE ELIMINO, ya que no tiene relación tabla"); } } 
    }
 // mensaje de exito
 catch (SQLException e) {JOptionPane.showMessageDialog(null,"No se puede eliminar, tiene relación tabla");} }

//Limpiar datos de entrada de los modelos
public void limpia() {
modelo.id.setText("");
modelo.Nombre.setText("");
}
}