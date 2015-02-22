package bicicletas;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ConexionBD {
  Connection c;
  Statement s;
  ResultSet r;
  PreparedStatement p;

  public ConexionBD(String BaseD) throws SQLException,ClassNotFoundException
  {
    String dburl="jdbc:sqlite:"+BaseD;
    String usuario="";
    String contrasena="";
    Class.forName("org.sqlite.JDBC");
    c=DriverManager.getConnection(dburl,usuario,contrasena);
  }

  
  public ResultSet Consulta(String ins)
      throws SQLException,ClassNotFoundException
  {
    s=c.createStatement();
    r=s.executeQuery(ins);
    return r;
  }
  
   public void Insertar(String ins)
      throws SQLException,ClassNotFoundException
  {
    s=c.createStatement();
    int n=s.executeUpdate(ins);

  }
  
  public void InsertarAlquiler(String ins,String fechaa,String fechad) throws SQLException,ClassNotFoundException
  {
       p=c.prepareStatement(ins);
       /*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       String fechatotal=dateFormat.format(Calendar.getInstance().getTime());
       String fecha=fechatotal.substring(0, 10);
       String hora=fechatotal.substring(11,16);
    
       DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");*/
       p.setDate(1, java.sql.Date.valueOf(fechaa));
       p.setDate(2, java.sql.Date.valueOf(fechad));
       //p.setString(2, hora);
       p.executeUpdate();
      
  }
  
  public void ActualizarDevuelta(String ins,String fecha) throws SQLException,ClassNotFoundException
  {
       p=c.prepareStatement(ins);
       /*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       String fechatotal=dateFormat.format(Calendar.getInstance().getTime());
       String fecha=fechatotal.substring(0, 10);
       String hora=fechatotal.substring(11,16);
         
       DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");*/
       p.setDate(1, java.sql.Date.valueOf(fecha));
       //p.setString(2, hora);
       p.executeUpdate();
  }
  
  public void Liberar()throws SQLException,ClassNotFoundException
  {
    if(r!=null)
      r.close();
    if(s!=null)
      s.close();
     if(c!=null)
      c.close();

  }
}











