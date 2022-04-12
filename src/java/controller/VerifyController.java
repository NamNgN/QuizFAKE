/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDAO;
import dao.AccountFunction;
import dao.TokenDAO;
import dao.TokenFunction;
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
public class VerifyController extends HttpServlet {

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
            out.println("<title>Servlet VerifyController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VerifyController at " + request.getContextPath() + "</h1>");
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
        if (request.getParameter("token") != null) {
            String token = request.getParameter("token");
            TokenFunction tokenfunc = new TokenFunction();
            RequestDispatcher rd1 = request.getRequestDispatcher("verify.jsp");
            try {
                String email = tokenfunc.decodeBase64(token.substring(token.indexOf('.') + 1));
                AccountDAO accountDAO = new AccountDAO();
                Account a = accountDAO.getAccountByEmail(email);
                int checkToken = tokenfunc.checkToken(token, email);
                switch (checkToken) {
                    case 1:
                        try {
                            accountDAO.enableAccount(a.getUsername());
                        } catch (Exception ex) {
                            Logger.getLogger(VerifyController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        request.setAttribute("error", "Sucess");
                        rd1.forward(request, response);
                        break;
                    case 2:
                        request.setAttribute("error", "Token Expired");
                        rd1.forward(request, response);
                        break;
                    case 3:
                        request.setAttribute("error", "Token Wrong");
                        rd1.forward(request, response);
                        break;
                    case 4:
                        request.setAttribute("error", "Email unregistered");
                        rd1.forward(request, response);
                        break;
                }

            } catch (Exception ex) {
                Logger.getLogger(VerifyController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            RequestDispatcher rd1 = request.getRequestDispatcher("verify.jsp");
            try {
                HttpSession session = request.getSession();
                Account a = (Account) session.getAttribute("Account");
                if (!a.isEnabled()) {
                    AccountFunction func = new AccountFunction();
                    TokenDAO tokenDAO = new TokenDAO();
                    TokenFunction tokenFunc = new TokenFunction();
                    String email = a.getEmail();
                    String token = tokenFunc.generateToken(email);
                    tokenDAO.addToken(token, email);
                    func.sendEmail(email, token);
                    request.setAttribute("error", "Plese check your email to confirm");
                } else {
                    request.setAttribute("error", "Your account has been verified");
                }
                rd1.forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(VerifyController.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect(request.getContextPath() + "/login");
            }

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
        processRequest(request, response);
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
