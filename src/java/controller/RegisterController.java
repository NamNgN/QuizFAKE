/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDAO;
import dao.AccountFunction;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class RegisterController extends HttpServlet {

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
            out.println("<title>Servlet RegisterController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterController at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("register.jsp").forward(request, response);
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
        String regexUser = "[a-zA-Z0-9_]+";
        String regexMail = "^[A-Z0-9_a-z]+@[A-Z0-9\\.a-z]+\\.[A-Za-z]{2,6}$";
        String regexPass = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        String username = request.getParameter("username");
        String passwd = request.getParameter("passwd");
        String passwdcf = request.getParameter("passwdcf");
        String email = request.getParameter("email");
        RequestDispatcher rd1 = request.getRequestDispatcher("register.jsp");
        if (!username.matches(regexUser)) {
            request.setAttribute("error", "invalid username");
            rd1.forward(request, response);
        } else if (!email.matches(regexMail)) {
            request.setAttribute("error", "invalid Email");
            rd1.forward(request, response);
        } else if (!passwd.matches(regexPass)) {
            request.setAttribute("error", "invalid Password");
            rd1.forward(request, response);
        } else if (!passwd.equals(passwdcf)) {
            request.setAttribute("error", "Password confirm wrong");
            rd1.forward(request, response);
        } else {
            AccountDAO accountDAO = new AccountDAO();
            try {
                if (accountDAO.getAccountByUsername(username) != null) {
                    request.setAttribute("error", "Username Exists");
                    rd1.forward(request, response);
                } else if (accountDAO.getAccountByEmail(email) != null) {
                    request.setAttribute("error", "The Email was registered");
                    rd1.forward(request, response);
                } else {
                    AccountFunction func = new AccountFunction();
                    String id = func.randomId();
                    String encPass = func.encodePassword(passwd);
                    Account a = new Account(id, username, "", email, false, encPass, false);
                    accountDAO.addNewAccount(a);
                    HttpSession session = request.getSession();
                    session.setAttribute("Account", a);
                    response.sendRedirect("verify");
                }
            } catch (Exception ex) {
                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
