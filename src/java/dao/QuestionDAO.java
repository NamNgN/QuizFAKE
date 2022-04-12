/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Question;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class QuestionDAO extends DAO {

    public List<Question> getQuestionByCourseId(int cid) {
        List<Question> question = new ArrayList<>();
        sql = "SELECT * FROM Question where courseid = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, cid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Question q = new Question();
                q.setQuestionid(rs.getInt("questionid"));
                q.setImage(rs.getString("image"));
                q.setTerm(rs.getString("term"));
                q.setDefinition(rs.getString("definition"));
                q.setCourseid(rs.getInt("courseid"));
                question.add(q);
            }
            return question;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public int insertQuestion(Question s) {
        int n = 0;
        sql = "insert into Question(image,term,definition,courseid) "
                + "values(?,?,?,?)";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, s.getImage());
            pre.setString(2, s.getTerm());
            pre.setString(3, s.getDefinition());
            pre.setInt(4, s.getCourseid());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
        }
        return n;
    }

    public String getAuthorID(int id){
        String result = "";
        sql = "SELECT accountid FROM Question Q LEFT JOIN Course C\n" +
                "ON Q.courseid = C.courseid WHERE Q.questionid = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1,id);
            ResultSet rs = pre.executeQuery();
            if(rs!=null&&rs.next()){
                result = rs.getString(1);
            }
        } catch (SQLException ex) {
        }
        return result;
    }
    
    public int removeQuestion(int qid){
        int n = 0;
        sql = "DELETE QUESTION WHERE questionid = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1,qid);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
        }
        return n;
    }
//    
//    public static void main(String[] args) {
//        QuestionDAO qd = new QuestionDAO();
//        System.out.println(qd.removeQuestion(6));
//    }

    public int updateQuestionById(Question question) {
        int n = 0;
        sql = "UPDATE [Question]\n" +
                "   SET [image] = ?\n" +
                "      ,[term] = ?\n" +
                "      ,[definition] = ?\n" +
                "      ,[courseid] = ?\n" +
                " WHERE questionid = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,question.getImage());
            ps.setString(2, question.getTerm());
            ps.setString(3, question.getDefinition());
            ps.setInt(4, question.getCourseid());
            ps.setInt(5, question.getQuestionid());
            n = ps.executeUpdate();
        } catch (SQLException ex) {
        }
        return n;
    }

}
