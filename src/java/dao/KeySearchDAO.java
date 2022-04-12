/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.KeySearch;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class KeySearchDAO {

    public List<KeySearch> getAllKeySearch() throws Exception {
        List<KeySearch> keys = new ArrayList<>();
        String sql = "SELECT * FROM KeySearch";
        Connection conn = new DBContext().connection;
        ResultSet rs = conn.prepareStatement(sql).executeQuery();
        while (rs.next()) {
            int id = rs.getInt("ID");
            String keyWord = rs.getString("KeyWord");
            KeySearch keySearch = new KeySearch(id, keyWord);
            keys.add(keySearch);
        }
        return keys;
    }

    public List<KeySearch> searchKeySearch(String key) throws Exception {
        List<KeySearch> keys = new ArrayList<>();
        String sql = "SELECT * FROM KeySearch WHERE KeyWord LIKE '%" + key + "%'";
        Connection conn = new DBContext().connection;
        ResultSet rs = conn.prepareStatement(sql).executeQuery();
        while (rs.next()) {
            int id = rs.getInt("ID");
            String keyWord = rs.getString("KeyWord");
            KeySearch keySearch = new KeySearch(id, keyWord);
            keys.add(keySearch);
        }
        return keys;
    }

    public KeySearch getKeySearchByWord(String keyWord) throws Exception {
        for (KeySearch k : getAllKeySearch()) {
            if (k.getKeyWord().equals(keyWord)) {
                return k;
            }
        }
        return null;
    }

    public void addKeySearch(String keySearch) throws Exception {
        String sql = "INSERT INTO [KeySearch] ([KeyWord])"
                + "VALUES(?)";
        Connection conn = new DBContext().connection;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, keySearch);

        // excecute sql command
        ps.execute();
        ps.close();
        conn.close();
    }
}
