/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaPanificadora;

/**
 *
 * @author Ian
 */
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
public class Conectividad {


 private final String url = "jdbc:mysql://localhost/panificadora";
 private final String username = "root";
 private final String password = "";
  
    public DefaultTableModel model;
    public  Connection con = null;
    public PreparedStatement ps;
    public ResultSet rs;
  
   public Connection getConexion() {
       //Connection con = null;
       if (con == null) {
        try {

            Class.forName("com.mysql.jdbc.Driver");

            con = (Connection) DriverManager.getConnection(url, username, password);

        } catch (Exception e) {
            System.out.println(e);

                }
        }
        
          return con;

        
   }
   
   public void closeConexion(){
   if (con != null){
       try {
           con.close();
       } catch (SQLException ex) {
           Logger.getLogger(Conectividad.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
     
   
   }
    
    
}

