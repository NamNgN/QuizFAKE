/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CourseDAO;
import entity.Question;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.QuestionDAO;
import entity.Course;

/**
 *
 * @author User
 */
public class TestController extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        int courseId = Integer.parseInt(request.getParameter("courseid"));
        Course course = new CourseDAO().getCourseById(courseId);
//        int courseId = 1;
        QuestionDAO questionDao = new QuestionDAO();
        try {
            ArrayList<Question> listAll = (ArrayList<Question>) questionDao.getQuestionByCourseId(courseId);
            if (listAll == null || listAll.size() == 0) {
                request.getRequestDispatcher("displayQuestion?courseid=" + courseId).forward(request, response);
            }
            ArrayList<Question> list = new ArrayList<Question>();
            //random list
            Collections.shuffle(listAll);

            int count = 0;
            //lay 20 cau hoi cho vao list de kiem tra
            for (Question question : listAll) {
                list.add(question);
                count += 1;
                if (count == 20) {
                    break;
                }
            }
            request.setAttribute("courseTitle", course.getTitle());
            request.getSession().setAttribute("listQuestion", list);
            request.setAttribute("courseId", courseId);

        } catch (Exception ex) {
            request.setAttribute("message", "Get list question fail");
        }
//        request.getRequestDispatcher("display_question.jsp").forward(request, response);
        request.getRequestDispatcher("test.jsp").forward(request, response);

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int courseId = Integer.parseInt(request.getParameter("courseid"));
        Course course = new CourseDAO().getCourseById(courseId);
        QuestionDAO questionDao = new QuestionDAO();
        int correctAnswer = 0;
        try {
            ArrayList<Question> list = (ArrayList<Question>) request.getSession().getAttribute("listQuestion");
            for (Question q : list) {
                String answer = request.getParameter(q.getQuestionid() + "");
                if (q.getDefinition().equalsIgnoreCase(answer)) {
                    correctAnswer++;
                    q.setIsCorrect(true);
                } else {
                    q.setIsCorrect(false);

                }

                request.setAttribute("" + q.getQuestionid(), answer);
            }
            request.setAttribute("courseTitle", course.getTitle());
            request.setAttribute("correctAnswer", correctAnswer);
            request.setAttribute("score", correctAnswer * 10.0 / list.size());

            request.setAttribute("listQuestion", list);
        } catch (Exception ex) {
            request.setAttribute("message", "Get list question fail");
        }
        request.getRequestDispatcher("result.jsp").forward(request, response);

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
