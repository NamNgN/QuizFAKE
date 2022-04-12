/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class AccountDAO extends DAO {

    public Account getAccount(String user, String pass) {
        Account acc = new Account();
        sql = "SELECT * FROM Account WHERE username = ? AND password = ?";
        try {
            Connection conn = new DBContext().connection;
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                acc.setAccountid(rs.getString("accountid"));
                acc.setUsername(rs.getString("username"));
                acc.setFullname(rs.getString("fullname"));
                acc.setEmail(rs.getString("email"));
                acc.setGender(rs.getBoolean("gender"));
                acc.setPassword(rs.getString("password"));
            }else{
                acc = null;
            }
            return acc;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;

    }
    //select all user

    public List<Account> getAllAccount() throws Exception {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM Account";
        Connection conn = new DBContext().connection;
        ResultSet rs = conn.prepareStatement(sql).executeQuery();
        while (rs.next()) {
            String accountid = rs.getString("accountid");
            String username = rs.getString("username");
            String fullname = rs.getString("fullname");
            String email = rs.getString("email");
            boolean gender = rs.getBoolean("gender");
            String passwd = rs.getString("password");
            boolean enabled = rs.getBoolean("enabled");
            accounts.add(new Account(accountid, username, fullname, email, gender, passwd, enabled));
        }
        rs.close();
        con.close();
        return accounts;
    }

    public List<Account> searchAccountByUsername(String text) throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM Account WHERE username LIKE '%" + text + "%'";
        Connection conn = new DBContext().connection;
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String accountid = rs.getString("accountid");
            String username = rs.getString("username");
            String fullname = rs.getString("fullname");
            String email = rs.getString("email");
            boolean gender = rs.getBoolean("gender");
            String passwd = rs.getString("password");
            boolean enabled = rs.getBoolean("enabled");
            accounts.add(new Account(accountid, username, fullname, email, gender, passwd, enabled));
        }
        rs.close();
        con.close();
        return accounts;
    }

    public Account getAccountByUsername(String username) throws Exception {
        for (Account a : getAllAccount()) {
            if (a.getUsername().equals(username)) {
                return a;
            }
        }
        return null;
    }

    public Account getAccountByEmail(String email) throws Exception {
        for (Account a : getAllAccount()) {
            if (a.getEmail().equals(email)) {
                return a;
            }
        }
        return null;
    }

    public Account getAccountById(String id) throws Exception {
        for (Account a : getAllAccount()) {
            if (a.getAccountid().equals(id)) {
                return a;
            }
        }
        return null;
    }

    public void addNewAccount(Account a) throws Exception {
        String sql = "INSERT INTO Account ([accountid], [username], [email], [password], [enabled])"
                + "VALUES(?, ?, ?, ?, ?)";
        Connection conn = new DBContext().connection;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, a.getAccountid());
        ps.setString(2, a.getUsername());
        ps.setString(3, a.getEmail());
        ps.setString(4, a.getPassword());
        ps.setBoolean(5, a.isEnabled());
        ps.execute();
        ps.close();
        con.close();
    }

    public void updatePassword(String pass) throws Exception {

    }

    public void enableAccount(String username) throws Exception {
        String sql = "UPDATE Account SET enabled = 1 WHERE username = ?";
        Connection conn = new DBContext().connection;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.execute();
        ps.close();
        con.close();
    }

}
