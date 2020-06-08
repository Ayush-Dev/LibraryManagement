/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryapp.dao;

import libraryapp.dbutil.DBConnection;
import libraryapp.pojo.StudentPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Ayush's HP
 */
public class StudentDAO {
    public static boolean addStudent(StudentPojo obj) throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Insert into library_students values(?,?,?,?,?,?,?,?,?,?)");
        ps.setString(1, obj.getFname());
        ps.setString(2, obj.getLname());
        ps.setString(3, obj.getEmail());
        ps.setString(4, obj.getId());
        ps.setString(5, obj.getCollege());
        ps.setInt(6, obj.getSem());
        ps.setString(7, obj.getSec());
        ps.setLong(8, obj.getPno());
        ps.setString(9, obj.getGender());
        ps.setString(10, obj.getBranch());
        int count = ps.executeUpdate();
        return (count==1);
    }

//    public static ArrayList <StudentPojo> getAllStudent() throws SQLException {
//        Connection conn = DBConnection.getConnection();
//        Statement st = conn.createStatement();
//        ResultSet rs = st.executeQuery("Select * from library_students");
//        ArrayList <StudentPojo> studentList = new ArrayList<>();
//        while(rs.next()){
//            String fname = rs.getString(1);
//            String lname = rs.getString(2);
//            String email = rs.getString(3);
//            String id = rs.getString(4);
//            String college = rs.getString(5);
//            int sem = rs.getInt(6);
//            String sec = rs.getString(7);
//            long pno = rs.getLong(8);
//            String gender = rs.getString(9);
//            String branch = rs.getString(10);
//            StudentPojo obj = new StudentPojo(fname, lname, email, id, college, sem, sec, pno, gender, branch);
//            studentList.add(obj);
//        }
//        return studentList;
//    }

    public static String getStudentNameFromID(StudentPojo obj) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Select * from library_students where id=?");
        ps.setString(1, obj.getId());
        ResultSet result = ps.executeQuery();
        String studentName;
        studentName = "--STUDENT NOT FOUND !!--";
        if(result.next()) {
            studentName = result.getString("f_name") + " " + result.getString("l_name");
            return studentName;
        }
        return studentName;
    }

    public static int totalStudent() throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry;
        int count;
        count = 0;
        qry = "Select count(*) as total from library_students";
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(qry);
        if(result.next()){
            count = result.getInt(1);
        }
        return count;
    }

    public static StudentPojo getStudentDetails(StudentPojo obj) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Select * from library_students where id=?");
        ps.setString(1, obj.getId());
        ResultSet result = ps.executeQuery();
        result.next();
        StudentPojo student = new StudentPojo(result.getString("f_name"), result.getString("l_name"), result.getString("email"), result.getString("id"), result.getString("college"), result.getInt("sem"), result.getString("sec"), result.getLong("p_no"), result.getString("gender"), result.getString("branch"));
        return student;
    }

    public static boolean isStudent(StudentPojo obj) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Select count (*) as total from library_students where id=?");
        ps.setString(1, obj.getId());
        ResultSet result = ps.executeQuery();
        if(result.next() && result.getInt(1)==1){
            return true;
        }
        return false;
    }
}
