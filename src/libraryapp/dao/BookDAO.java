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
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import libraryapp.dbutil.DBConnection;
import libraryapp.pojo.BookPojo;
import libraryapp.pojo.IssueBookPojo;

/**
 *
 * @author Ayush's HP
 */
public class BookDAO {
    public static boolean addBook(BookPojo obj) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Insert into library_books (bname, bauthor, bcost, bcategory, bpublisher, bcode) values(?,?,?,?,?,?)");
        ps.setString(1, obj.getBname());
        ps.setString(2, obj.getBauthor());
        ps.setInt(3, obj.getBcost());
        ps.setString(4, obj.getBcategory());
        ps.setString(5, obj.getBpublisher());
        ps.setInt(6, obj.getBcode());
        int count = ps.executeUpdate();
        return (count==1);
    }

    public static String getBookName(BookPojo obj) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Select * from library_books where bcode=?");
        ps.setInt(1, obj.getBcode());
        ResultSet result = ps.executeQuery();
        String bName;
        bName = "--BOOK NOT FOUND !!--";
        if(result.next()){
            bName = result.getString("bname");
        }
        return bName;
    }

    public static int totalBook() throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry;
        int count;
        count = 0;
        qry = "Select count(*) as total from library_books";
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(qry);
        if(result.next()){
            count = result.getInt(1);
        }
        return count;
    }

    public static int totalIssuedBook() throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry;
        int count;
        count = 0;
        qry = "Select count(*) as total from library_books where sid is not null";
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(qry);
        if(result.next()){
            count = result.getInt(1);
        }
        return count;
    }

    public static boolean validateFreeBook(BookPojo obj) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Select count (*) as total from library_books where bcode=? and sid is null");
        ps.setString(1, Integer.toString(obj.getBcode()));
        ResultSet result = ps.executeQuery();
        if(result.next() && result.getInt(1) == 1){
            return true;
        }
        return false;
    }

    public static boolean issueBook(IssueBookPojo obj) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry, date;
        int count;
        qry = "Update library_books set sid=?, dissue=?, dreturn=? where bcode=?";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1,obj.getSid());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        date = sdf.format(obj.getDissue());
        ps.setString(2, date);
        date = sdf.format(obj.getDreturn());
        ps.setString(3, date);
        ps.setString(4, Integer.toString(obj.getBcode()));
        count = ps.executeUpdate();
        return (count==1);
    }

    public static boolean validateBookIssue(IssueBookPojo obj) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry;
        qry = "Select * from library_books where bcode=?";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setInt(1, obj.getBcode());
        ResultSet result = ps.executeQuery();
        if(result.next() && result.getString("sid").equals(obj.getSid())){
            return true;
        }
        return false;
    }

    public static boolean returnBook(IssueBookPojo obj) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry;
        int count;
        qry = "Update library_books set sid=null, dissue=null, dreturn=null where bcode=?";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setInt(1, obj.getBcode());
        count = ps.executeUpdate();
        return (count==1);        
    }

    public static ArrayList <IssueBookPojo> studentBooks(IssueBookPojo obj) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry;
        qry = "Select * from library_books where sid=?";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, obj.getSid());
        ResultSet result = ps.executeQuery();
        ArrayList <IssueBookPojo> issueBookList = new ArrayList<>();
        while(result.next()){
            String bname = result.getString("bname");
            int bcode = result.getInt("bcode");
            Date dissue = result.getDate("dissue");
            Date dreturn = result.getDate("dreturn");
            IssueBookPojo issueBook = new IssueBookPojo(bname, bcode, dissue, dreturn);
            issueBookList.add(issueBook);
        }
        return issueBookList;
    }

    public static ArrayList <BookPojo> getBookDetailsFromName(BookPojo obj) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry;
        qry = "Select * from library_books where bname=?";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, obj.getBname());
        ResultSet result = ps.executeQuery();
        ArrayList <BookPojo> bookDetails = new ArrayList<>();
        while(result.next()){
            String bname = result.getString("bname");
            int bcode = result.getInt("bcode");
            String bauthor = result.getString("bauthor");
            String bcategory = result.getString("bcategory");
            String sid = result.getString("sid");
            BookPojo book = new BookPojo(bname, bcode, bauthor, bcategory, sid);
            bookDetails.add(book);
        }
        return bookDetails;
    }

    public static boolean isBook(BookPojo obj) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry;
        qry = "Select count (*) from library_books where bcode=? or bname=?";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setInt(1, obj.getBcode());
        ps.setString(2, obj.getBname());
        ResultSet result = ps.executeQuery();
        if(result.next() && !(result.getInt(1)==0)) {
            return true;
        }
        return false;
    }

    public static boolean isAuthor(BookPojo obj) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry;
        qry = "Select count (*) from library_books where bauthor=?";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, obj.getBname());                                           //Bname refers to Bauthor
        ResultSet result = ps.executeQuery();
        if(result.next() && !(result.getInt(1)==0)){
            return true;
        }
        return false;
    }

    public static BookPojo getBookDetailsFromCode(BookPojo obj) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry;
        qry = "Select * from library_books where bcode=?";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setInt(1, obj.getBcode());
        ResultSet result = ps.executeQuery();
        result.next();
        String bname = result.getString("bname");
        int bcode = result.getInt("bcode");
        String bauthor = result.getString("bauthor");
        String bcategory = result.getString("bcategory");
        String sid = result.getString("sid");
        BookPojo bookDetails = new BookPojo(bname, bcode, bauthor, bcategory, sid);
        return bookDetails;
    }

    public static ArrayList <BookPojo> getBookDetailsFromAuthor(BookPojo obj) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry;
        qry = "Select * from library_books where bauthor=?";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, obj.getBname());                                            //bname refers to bauthor
        ResultSet result = ps.executeQuery();
        ArrayList <BookPojo> bookDetails = new ArrayList<>();
        while(result.next()){
            String bname = result.getString("bname");
            int bcode = result.getInt("bcode");
            String bauthor = result.getString("bauthor");
            String bcategory = result.getString("bcategory");
            String sid = result.getString("sid");
            BookPojo book = new BookPojo(bname, bcode, bauthor, bcategory, sid);
            bookDetails.add(book);
        }
        return bookDetails;
    }

    public static boolean checkBookAvailable(BookPojo obj) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry;
        qry = "Select * from library_books where bcode=?";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setInt(1, obj.getBcode());
        ResultSet result = ps.executeQuery();
        if(result.next() && result.getString("sid")==null){
            return true;
        }
        return false;
    }

    public static ArrayList <BookPojo> getReturnListFromDate(IssueBookPojo obj) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry, date;
        qry = "Select * from library_books where dreturn=?";
        PreparedStatement ps = conn.prepareStatement(qry);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-YYYY");
        date = sdf.format(obj.getDissue());
        ps.setString(1, date);
        ResultSet result = ps.executeQuery();
        ArrayList <BookPojo> bookList = new ArrayList<>();
        while(result.next()){
            String sid = result.getString("sid");
            int bcode = result.getInt("bcode");
            BookPojo books = new BookPojo(sid, bcode);
            bookList.add(books);
        }
        return bookList;
    }

    public static ArrayList <BookPojo> getIssueListFromDate(IssueBookPojo obj) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry, date;
        qry = "Select * from library_books where dissue=?";
        PreparedStatement ps = conn.prepareStatement(qry);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-YYYY");
        date = sdf.format(obj.getDissue());
        ps.setString(1, date);
        ResultSet result = ps.executeQuery();
        ArrayList <BookPojo> bookList = new ArrayList<>();
        while(result.next()){
            String sid = result.getString("sid");
            int bcode = result.getInt("bcode");
            BookPojo books = new BookPojo(sid, bcode);
            bookList.add(books);
        }
        return bookList;
    }

    public static boolean isBookIssueDateAvailable(IssueBookPojo obj) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry, date;
        qry = "Select count (*) as total from library_books where dissue=?";
        PreparedStatement ps = conn.prepareStatement(qry);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-YYYY");
        date = sdf.format(obj.getDissue());
        ps.setString(1, date);
        ResultSet result = ps.executeQuery();
        if(result.next() && !(result.getInt(1) == 0)){
            return true;
        }
        return false;
    }

    public static boolean isBookReturnDateAvailable(IssueBookPojo obj) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry, date;
        qry = "Select count (*) as total from library_books where dreturn=?";
        PreparedStatement ps = conn.prepareStatement(qry);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-YYYY");
        date = sdf.format(obj.getDissue());                                     //issue date used as a return date
        ps.setString(1, date);
        ResultSet result = ps.executeQuery();
        if(result.next() && !(result.getInt(1) == 0)){
            return true;
        }
        return false;
    }
}
