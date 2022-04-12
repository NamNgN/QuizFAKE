/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import dao.AccountDAO;
import dao.CourseDAO;
import entity.KeySearch;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.KeySearchDAO;

/**
 *
 * @author User
 */
public class SearchController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String keySearch = request.getParameter("term");
        KeySearchDAO keyDao = new KeySearchDAO();
        try {
            List<KeySearch> keySearchs = keyDao.searchKeySearch(keySearch);
            if ((request.getParameter("result") == null)) {
                List<String> results = new ArrayList<>();
                if (!keySearchs.isEmpty()) {
                    keySearchs.forEach((s) -> {
                        results.add(s.getKeyWord());
                    });
                }
                String json = new Gson().toJson(results);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            } else {
                CourseDAO courseDAO = new CourseDAO();
                AccountDAO accountDAO = new AccountDAO();
                request.setAttribute("listCourse", courseDAO.searchCourseByTitle(keySearch));
                request.setAttribute("listAccount", accountDAO.searchAccountByUsername(keySearch));
                request.getRequestDispatcher("search.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        String keySearch = request.getParameter("key");
        KeySearchDAO keyDao = new KeySearchDAO();
        try {
            if (keyDao.getKeySearchByWord(keySearch) == null) {
                keyDao.addKeySearch(keySearch);
            }
            CourseDAO courseDAO = new CourseDAO();
            AccountDAO accountDAO = new AccountDAO();
            request.setAttribute("listCourse", courseDAO.searchCourseByTitle(keySearch));
            request.setAttribute("listAccount", accountDAO.searchAccountByUsername(keySearch));
            request.getRequestDispatcher("search.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
