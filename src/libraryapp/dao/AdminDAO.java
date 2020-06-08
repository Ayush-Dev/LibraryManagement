/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import libraryapp.dbutil.DBConnection;
import libraryapp.pojo.AdminPojo;

/**
 *
 * @author Ayush's HP
 */
public class AdminDAO {
    public static boolean isValidAdmin(AdminPojo obj) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Select * from library_users where username=?");
        ps.setString(1, obj.getUsername());
        ResultSet result = ps.executeQuery();
        if(result.next() && result.getString("password").equals(obj.getOpassword())){
            return true;
        }
        return false;
    }
    
    public static String getAdminName(AdminPojo obj) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Select * from library_users where username=?");
        ps.setString(1, obj.getUsername());
        ResultSet result = ps.executeQuery();
        String adminName = "Not Found";
        if(result.next()){
            adminName = result.getString("f_name") + " " + result.getString("l_name");
            return adminName;
        }
        return adminName;
    }
    
    public static boolean updateAdmin(AdminPojo obj) throws SQLException {
        int count;
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Update library_users set password=?, f_name=?, l_name=? where username=?");
        ps.setString(1, obj.getNpassword());
        ps.setString(2, obj.getFname());
        ps.setString(3, obj.getLname());
        ps.setString(4, obj.getUsername());
        count = ps.executeUpdate();
        return (count==1);
    }
    
    public static AdminPojo getAdminDetails(AdminPojo obj) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Select * from library_users where username=?");
        ps.setString(1, obj.getUsername());
        ResultSet result = ps.executeQuery();
        if(result.next()){
            obj.setFname(result.getString("f_name"));
            obj.setLname(result.getString("l_name"));
            return obj;
        }
        return obj;
    }
    
    public static boolean isUsernameValid(AdminPojo obj) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Select count(*) as total from library_users where username = ?");
        ps.setString(1, obj.getUsername());
        ResultSet result = ps.executeQuery();
        if(result.next() && result.getInt(1)==1){
            return true;
        }
        return false;
    }
    
    public static boolean createAdmin(AdminPojo obj) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Insert into library_users values(?,?,?,?)");
        ps.setString(1, obj.getUsername());
        ps.setString(2, obj.getNpassword());
        ps.setString(3, obj.getFname());
        ps.setString(4, obj.getLname());
        int count = ps.executeUpdate();
        return (count==1);
    }

    public static boolean updateLogs(AdminPojo obj) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Insert into library_logs values(?,?,?)");
        ps.setString(1, obj.getUsername());
        ps.setString(2, obj.getFname());
        ps.setString(3, obj.getLname());
        int count = ps.executeUpdate();
        return (count==1);
    }
}
