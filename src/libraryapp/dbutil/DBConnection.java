/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryapp.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ayush's HP
 */
public class DBConnection {
    private static Connection conn;
    static{
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@AyushPC:1521:XE","library","Ayush123");
        } 
        catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Connection getConnection(){
        return conn;
    }
    
    public static void closeConnection() {
        try{
            conn.close();
            JOptionPane.showMessageDialog(null, "Connection closed Successfully !!");
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Cannot connect to Database !!");
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
