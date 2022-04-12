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
public class Account {
    private String accountid;
    private String username;
    private String fullname;
    private String email;
    private boolean gender;
    private String password;
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public Account(String accountid, String username, String fullname, String email, boolean gender, String password, boolean enabled) {
        this.accountid = accountid;
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.gender = gender;
        this.password = password;
        this.enabled = enabled;
    }

    public Account() {
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
