/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author User
 */
public class ProgressAccount {
    private int progressid;
    private String accountid;

    public ProgressAccount() {
    }

    public ProgressAccount(int progressid, String accountid) {
        this.progressid = progressid;
        this.accountid = accountid;
    }

    public int getProgressid() {
        return progressid;
    }

    public void setProgressid(int progressid) {
        this.progressid = progressid;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }
    
}
