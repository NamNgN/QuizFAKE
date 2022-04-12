/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CourseDAO;
import dao.QuestionDAO;
import entity.Account;
import entity.Course;
import entity.Question;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
public class EditCourseController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        Account currAcc = (Account) session.getAttribute("currAccount");
        CourseDAO courseDAO = new CourseDAO();
        int courseid = -1;
        try {
            courseid = Integer.parseInt(request.getParameter("courseid"));
        } catch (Exception e) {
            response.sendRedirect("home");
        }
        String currAccountid = courseDAO.getCourseById(courseid).getAccountid();
        if (currAcc == null || !currAcc.getAccountid().equals(currAccountid)) {
            response.sendRedirect("login");
        } else {
            try {
                Course course = courseDAO.getCourseById(courseid);
                List<Question> lsQuestion = new QuestionDAO().getQuestionByCourseId(courseid);
                request.setAttribute("course", course);
                request.setAttribute("lsQuestion", lsQuestion);
            } catch (Exception e) {
                out.print("<h4>Error</h4>");
                request.getRequestDispatcher("home").include(request, response);
            }
            request.getRequestDispatcher("editCourse.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        Account currAcc = (Account) session.getAttribute("currAccount");
        CourseDAO courseDAO = new CourseDAO();
        int courseid = -1;
        try {
            courseid = Integer.parseInt(request.getParameter("courseid"));
        } catch (Exception e) {
            response.sendRedirect("home");
        }
        try {
            //update course
            String title = request.getParameter("title");
            String desciption = request.getParameter("description");
            boolean visibleto = "1".equals(request.getParameter("visibleto"));
            Course course = new Course(courseid, title, desciption, visibleto, currAcc.getAccountid());
            courseDAO.updateCourseById(course);
            //update question of course
            QuestionDAO questionDAO = new QuestionDAO();
            List<Question> lsQuestion = questionDAO.getQuestionByCourseId(courseid);
            int i = 1;
            while (request.getParameter("term" + i) != null && !request.getParameter("term" + i).equals("")) {
                String term = request.getParameter("term" + i);
                String definition = request.getParameter("def" + i);

                int questionId = -1;
                //lay gia tri questionid
                try {
                    questionId = Integer.parseInt(request.getParameter("questionid" + i));
                } catch (Exception e) {
                }
                Question question = new Question();
                //ko ton tai thi insert nguoc lai thi update
                if (questionId == -1) {
                    question = new Question(null, term, definition, courseid);
                    questionDAO.insertQuestion(question);
                } else {
                    question = new Question(questionId, title, term, definition, courseid);
                    questionDAO.updateQuestionById(question);
                }
                i++;
            }
        } catch (Exception e) {
            out.print("<h4>Error</h4>");
            request.getRequestDispatcher("home").include(request, response);
        }
        request.getRequestDispatcher("displayQuestion?courseid=" + courseid).forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
