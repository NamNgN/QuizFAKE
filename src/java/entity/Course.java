/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Asus
 */
public class Course {
    private int courseid;
    private String title;
    private String description;
    private boolean visibleto;
    private String accountid;

    public Course(String title, String description, boolean visibleto, String accountid) {
        this.title = title;
        this.description = description;
        this.visibleto = visibleto;
        this.accountid = accountid;
    }
    public Course(int courseid, String title, String description, boolean visibleto, String accountid) {
        this.courseid = courseid;
        this.title = title;
        this.description = description;
        this.visibleto = visibleto;
        this.accountid = accountid;
    }

    public Course() {
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVisibleto() {
        return visibleto;
    }

    public void setVisibleto(boolean visibleto) {
        this.visibleto = visibleto;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }
    
}
