/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;


/**
 *
 * @author Admin
 */
public class VerifyToken {
    private String email;
    private String token;
    private Date create_Time;

    public VerifyToken() {
    }

    public VerifyToken(String email, String token, Date create_Time) {
        this.email = email;
        this.token = token;
        this.create_Time = create_Time;
    }

    public Date getCreate_Time() {
        return create_Time;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public void setCreate_Time(Date create_Time) {
        this.create_Time = create_Time;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
}
