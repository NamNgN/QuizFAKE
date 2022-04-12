/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import entity.VerifyToken;

/**
 *
 * @author Admin
 */
public class TokenDAO {

    public void addToken(String token, String email) throws Exception {
        String sql = "INSERT INTO [VerifyToken] ([Email], [Token])"
                + "VALUES(?, ?)";
        // get connection
        Connection conn = new DBContext().connection;
        PreparedStatement ps = conn.prepareStatement(sql);

        // set value for "?" in sql command
        ps.setString(1, email);
        ps.setString(2, token);

        // excecute sql command
        ps.execute();
        ps.close();
        conn.close();
    }

    public void updateToken(String email, String token) throws Exception {
        String sql = "UPDATE VerifyToken SET Token = ? WHERE Email = ?";
        try (Connection conn = new DBContext().connection; PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, token);
            ps.execute();
        }
    }

    public VerifyToken getTokenByEmail(String email) throws Exception {
        String sql = "SELECT * FROM VerifyToken WHERE Email = ?";
        Connection conn = new DBContext().connection;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String token = rs.getString("Token");
            Timestamp time_create = rs.getTimestamp("Create_Time");
            return new VerifyToken(email, token, time_create);
        }
        return null;
    }

    
//    public static void main(String[] args) throws Exception {
//        System.out.println(getTokenByEmail("thanhnhhe130698@gmail.com"));
//    }
}
