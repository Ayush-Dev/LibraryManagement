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
public class IssueBookPojo {

    public IssueBookPojo() {
        
    }
    
    public IssueBookPojo(String sid) {
        this.sid = sid;
    }

    public IssueBookPojo(String bname, int bcode, Date dissue, Date dreturn) {
        this.bname = bname;
        this.bcode = bcode;
        this.dissue = dissue;
        this.dreturn = dreturn;
    }

    public IssueBookPojo(int bcode, String sid, Date dissue, Date dreturn) {
        this.bcode = bcode;
        this.sid = sid;
        this.dissue = dissue;
        this.dreturn = dreturn;
    }

    public IssueBookPojo(int bcode, String sid) {
        this.bcode = bcode;
        this.sid = sid;
    }

    public IssueBookPojo(Date date) {
        this.dissue = date;
    }

    private int bcode;
    private String sid, bname;
    private Date dissue, dreturn;


    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
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

    public Date getDissue() {
        return dissue;
    }

    public void setDissue(Date dissue) {
        this.dissue = dissue;
    }

    public Date getDreturn() {
        return dreturn;
    }

    public void setDreturn(Date dreturn) {
        this.dreturn = dreturn;
    }
}
