/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryapp.pojo;

/**
 *
 * @author Ayush's HP
 */
public class BookPojo {

    public BookPojo() {
        
    }

    public BookPojo(String bname) {
        this.bname = bname;
    }

    public BookPojo(String bname, String bauthor, int bcost, String bcategory, String bpublisher, int bcode) {
        this.bname = bname;
        this.bauthor = bauthor;
        this.bcost = bcost;
        this.bcategory = bcategory;
        this.bcode = bcode;
        this.bpublisher = bpublisher;
    }

//    public BookPojo(String bname, int bcode, String bauthor, String bcategory) {
//        this.bname = bname;
//        this.bcode = bcode;
//        this.bauthor = bauthor;
//        this.bcategory = bcategory;
//    }

    public BookPojo(String bname, int bcode, String bauthor, String bcategory, String sid) {
        this.bname = bname;
        this.bcode = bcode;
        this.bauthor = bauthor;
        this.bcategory = bcategory;
        this.sid = sid;
    }
    public BookPojo(int bcode) {
        this.bcode = bcode;
    }

    public BookPojo(int bcode, String bname) {
        this.bcode = bcode;
        this.bname = bname;
    }

    public BookPojo(String sid, int bcode) {
        this.sid = sid;
        this.bcode = bcode;
    }

    private String sid, bname, bauthor, bcategory ,bpublisher;
    private int bcost, bcode;
    
    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBauthor() {
        return bauthor;
    }

    public void setBauthor(String bauthor) {
        this.bauthor = bauthor;
    }
    
    public int getBcost() {
        return bcost;
    }

    public void setBcost(int bcost) {
        this.bcost = bcost;
    }

    public String getBcategory() {
        return bcategory;
    }

    public void setBcategory(String bcategory) {
        this.bcategory = bcategory;
    }
    
    public String getBpublisher() {
        return bpublisher;
    }
    
    public void setBpublisher(String bpublisher) {
        this.bpublisher = bpublisher;
    }
    
    public int getBcode() {
        return bcode;
    }

    public void setBcode(int bcode) {
        this.bcode = bcode;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
