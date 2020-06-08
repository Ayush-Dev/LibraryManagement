/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryapp.pojo;

import java.util.Date;

/**
 *
 * @author Ayush's HP
 */
public class AdminPojo {

    public AdminPojo(){
        
    }
    public AdminPojo(String username) {
        this.username = username;
    }

    public AdminPojo(String username, String opassword) {
        this.username = username;
        this.opassword = opassword;
    }

    public AdminPojo(String username, String fname, String lname){
        this.username = username;
        this.fname = fname;
        this.lname = lname;
    }

    public AdminPojo(String username, String opassword, String npassword, String fname, String lname) {
        this.username = username;
        this.opassword = opassword;
        this.npassword = npassword;
        this.fname = fname;
        this.lname = lname;
    }

    private String username, opassword, npassword, fname, lname;
    private static String name;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOpassword() {
        return opassword;
    }

    public void setOpassword(String opassword) {
        this.opassword = opassword;
    }
    
    public String getNpassword() {
        return npassword;
    }
    
    public void setNpassword(String npassword) {
        this.npassword = npassword;
    }
    
    public static String getName() {
        return name;
    }
    
    public static void setName(String name) {
        AdminPojo.name = name;
    }
    
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
}
