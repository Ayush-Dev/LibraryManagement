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
public class StudentPojo {
    public StudentPojo(){
        
    }
    
    public StudentPojo(String fname, String lname, String email, String id, String college, int sem, String sec, long pno, String gender, String branch){
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.id = id;
        this.college = college;
        this.sem = sem;
        this.sec = sec;
        this.pno = pno;
        this.gender = gender;
        this.branch = branch;
    }
    
    public StudentPojo(String id){
        this.id = id;
    }
    
    private String fname, lname, email, id, college, sec, gender, branch;
    private int sem;
    long pno;
    
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    public int getSem() {
        return sem;
    }

    public void setSem(int sem) {
        this.sem = sem;
    }

    public long getPno() {
        return pno;
    }

    public void setPno(long pno) {
        this.pno = pno;
    }
}
