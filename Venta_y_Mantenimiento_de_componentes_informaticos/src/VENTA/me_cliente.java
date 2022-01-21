package VENTA;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class me_cliente {
Connection db = null;
Statement st = null;
ResultSet rs = null;
PreparedStatement pst=null;
String nom,ape,cor,dire ;
Integer cedu,tele ;
String sql;
Boolean a;
DefaultTableModel m = new DefaultTableModel();

//Conexion base datos  
public void conecciondb() {
  try {
      db=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Venta y Mantenimiento de componentes informaticos","postgres","1213jose"); 
          //Acualize ruta .../basedatos,usuario,contraseña
      } catch (SQLException e) {System.out.println("Ocurrio un error : "+e.getMessage());} }

//Ingreso  tabla cliente
public void inserta_cliente() throws SQLException {
if (a==false) { conecciondb();
//ingreso de variables
cedu=Integer.valueOf(cliente.cedula.getText());
nom=cliente.nombre.getText();
ape=cliente.apellido.getText();      
tele=Integer.valueOf(cliente.telefono.getText());
dire=cliente.direccion.getText(); 
cor=cliente.correo.getText();
//consulta sql en postgres
 sql = "insert into cliente (cedula_c,nombre_c,apellido_c,telefono_c,direccion_c,correo_c) values (?,?,?,?,?,?)";
 pst = db.prepareStatement(sql);
 //donde se muetra la insercion 
 pst.setInt(1,cedu);
 pst.setString(2,nom);
 pst.setString(3,ape);
 pst.setInt(4,tele);
 pst.setString(5,dire);
 pst.setString(6,cor);
 pst.executeUpdate();
 //mensaje de exito
 JOptionPane.showMessageDialog(null,"Se Guardo..."); } }

//Actualiza tabla cliente
public void actuali_cliente() throws SQLException {
if (a==true) { conecciondb();
//ingreso de variables
nom=cliente.nombre.getText();
ape=cliente.apellido.getText();
tele=Integer.valueOf(cliente.telefono.getText());
dire=cliente.direccion.getText();
cor=cliente.correo.getText();
//consulta sql en postgres
 sql = "update cliente set nombre_c=?, apellido_c=?, telefono_c=?,direccion_c=?,correo_c=? where cedula_c='"+cedu+"'";
 pst = db.prepareStatement(sql);
 //donde se muetra la insercion 
 pst.setString(1,nom);
 pst.setString(2,ape);
 pst.setInt(3,tele);
 pst.setString(4,dire);
 pst.setString(5,cor);
 pst.executeUpdate();
 JOptionPane.showMessageDialog(null,"Se Actualizo...");
}
}

//Consulta tabla cliente si existe cliente
public void consulta_cliente() throws SQLException {
 conecciondb();
 st = db.createStatement();
 //ingreso de variables
 cedu=Integer.valueOf(cliente.cedula.getText());
 //consulta sql en postgres
 rs = st.executeQuery("select * from cliente where cedula_c='"+cedu+"'");
 if (rs.next()) {a=true;
 //donde se muetra la insercion 
  cliente.nombre.setText(rs.getString(2));
  cliente.apellido.setText(rs.getString(3));
  cliente.telefono.setText(rs.getString(4));
  cliente.direccion.setText(rs.getString(5));
  cliente.correo.setText(rs.getString(6));
 }
 else {JOptionPane.showMessageDialog(null,"No Existe...");a=false;} }

//Elimina tabla cliente si existe cliente y no está en otra tabla relacionada
public void elimina_cliente() throws SQLException {
 try {
 if (a==true) { conecciondb();
 // mensaje para eliminar el dato 
 int resp = JOptionPane.showConfirmDialog(null, "Lo elimina","ALERTA",JOptionPane.YES_NO_OPTION);
  //consulta sql en postgres
 if (resp!=1) {st.execute("delete from cliente where cedula_c='"+cedu+"'");
   JOptionPane.showMessageDialog(null,"SE ELIMINO, ya que no tiene relación tabla"); } } 
    }
 catch (SQLException e) {JOptionPane.showMessageDialog(null,"No se puede eliminar, tiene relación tabla");} }

//Limpiar datos entrada de cliente
public void limpia() {
// datos a limpiar 
cliente.cedula.setText("");
cliente.nombre.setText("");
cliente.apellido.setText("");
cliente.telefono.setText("");
cliente.direccion.setText("");
cliente.correo.setText("");
}
}


