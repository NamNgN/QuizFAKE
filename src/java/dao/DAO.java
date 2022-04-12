package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO extends DBContext{
    public Connection con;
    public PreparedStatement ps;
    public ResultSet rs;
    public String sql;
    
    public DAO(){
        con = connection;
    }
    @Override
    public void finalize(){
        try {
            if(con != null)
                con.close();
        } catch (SQLException e) {
        }
    }
}
