/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class CourseDAO extends DAO {

    public List<Course> getAllCoursePublic() {
        List<Course> ls = new ArrayList<>();
        sql = "SELECT * FROM Course where visibleto=0";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Course c = new Course();
                c.setCourseid(rs.getInt("courseid"));
                c.setTitle(rs.getString("title"));
                c.setDescription(rs.getString("description"));
                c.setVisibleto(rs.getBoolean("visibleto"));
                c.setAccountid(rs.getString("accountid"));
                ls.add(c);
            }
            return ls;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public int insertCourse(Course s) {
        int n = 0;
        sql = "insert into Course(title,description,visibleto,accountid) "
                + "values(?,?,?,?)";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, s.getTitle());
            pre.setString(2, s.getDescription());
            pre.setBoolean(3, s.isVisibleto());
            pre.setString(4, s.getAccountid());
            pre.executeUpdate();
            sql = "SELECT TOP 1 courseid FROM Course ORDER BY courseid DESC";
            pre = con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if(rs!=null&&rs.next())n = rs.getInt(1);
        } catch (SQLException ex) {
        }
        return n;
    }
    public List<Course> getCourseByAccountId(String accountid)    {
        
      
        ArrayList<Course> list = new ArrayList();
        String sql = "SELECT * FROM Course where accountid = ? ";
          try {
               ps = con.prepareStatement(sql);
          ps.setString(1, accountid);
        rs = ps.executeQuery();
        while(rs.next()) {
            int courseid = rs.getInt("courseid");            
            String title = rs.getString("title");
            String description = rs.getString("description");
            Boolean visibleto = rs.getBoolean("visibleto");
            accountid = rs.getString("accountid");
            
            list.add(new Course(courseid, title, description, visibleto, accountid));     
        }
        return list;
          } catch (Exception e) {
          }
       return null;
       
   }
       public List<Course> getCourseByVisibletoPublic(String username)    {         
        ArrayList<Course> list = new ArrayList();
        String sql = "select c.* from Course c   join Account A on c.accountid=A.accountid where username like ? and visibleto=0 ";//lay course dc tai khoan day taao public
          try {
               ps = con.prepareStatement(sql);
          ps.setString(1, username);
        rs = ps.executeQuery();
        while(rs.next()) {
            int courseid = rs.getInt("courseid");            
            String title = rs.getString("title");
            String description = rs.getString("description");
            Boolean visibleto = rs.getBoolean("visibleto");
            String accountid = rs.getString("accountid");
            
            list.add(new Course(courseid, title, description, visibleto, accountid));     
        }
        return list;
          } catch (Exception e) {
          }
       return null;
       
   }
        public String getAccountID(int courseid){
        String result = "";
        sql = "select accountid from Course where courseid =?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1,courseid);
            ResultSet rs = pre.executeQuery();
            if(rs!=null&&rs.next()){
                result = rs.getString(1);
            }
        } catch (SQLException ex) {
        }
        return result;
    }
       public String getUsernameByCourseID(int courseid){
        String result = "";
        sql = "	select username from Account A join Course C on A.accountid=C.accountid where courseid = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1,courseid);
            ResultSet rs = pre.executeQuery();
            if(rs!=null&&rs.next()){
                result = rs.getString(1);
            }
        } catch (SQLException ex) {
        }
        return result;
    }
    
    public List<Course> searchCourseByTitle(String text) throws Exception {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM Course WHERE title LIKE '%" + text + "%' AND visibleto = 0";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("courseid");
            String title = rs.getString("title");
            String description = rs.getString("description");
            boolean visiableto = rs.getBoolean("visibleto");
            String accountid = rs.getString("accountid");
            Course c = new Course(id, title, description, visiableto, accountid);
            courses.add(c);
        }
        return courses;
    }
//    
//    public static void main(String[] args) {
//        CourseDAO cd = new CourseDAO();
//        System.out.println(cd.insertCourse(new Course("0 hj","desss", true,"P01"))); 
//    }

    public Course getCourseById(int courseid) {
        Course course = new Course();
        String sql = "SELECT * FROM Course WHERE courseid = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, courseid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("courseid");
                String title = rs.getString("title");
                String description = rs.getString("description");
                boolean visiableto = rs.getBoolean("visibleto");
                String accountid = rs.getString("accountid");
                course = new Course(id, title, description, visiableto, accountid);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return course;
    }

    public int updateCourseById(Course course){
        int n = 0;
        sql = "UPDATE [Course]\n" +
                "   SET [title] = ?\n" +
                "      ,[description] = ?\n" +
                "      ,[visibleto] = ?\n" +
                "      ,[accountid] = ?\n" +
                " WHERE courseid = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, course.getTitle());
            pre.setString(2, course.getDescription());
            pre.setBoolean(3, course.isVisibleto());
            pre.setString(4, course.getAccountid());
            pre.setInt(5, course.getCourseid());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
        }
        return n;
    }
}
