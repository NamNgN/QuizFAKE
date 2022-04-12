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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.QuestionDAO;
import entity.Course;

/**
 *
 * @author Asus
 */
public class StudyController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        QuestionDAO quesDAO = new QuestionDAO();
        String r_cid = request.getParameter("courseid");
        int cid = Integer.parseInt(r_cid);
        Course currCourse = new CourseDAO().getCourseById(cid);
        request.getSession().setAttribute("currCourse", currCourse);
        List<Question> listQuestion = quesDAO.getQuestionByCourseId(cid);
        if (listQuestion==null || listQuestion.size() == 0) {
            request.getRequestDispatcher("displayQuestion?courseid="+cid).forward(request, response);
        }
        Collections.shuffle(listQuestion);
        listQuestion.add(listQuestion.get(0));
        int count = 0;
        int currentIndexQuestion = 0;
        //listQuestion.get(0).getQuestionID();
        List<String> listAnswer = GetRandomListAnswer(listQuestion, currentIndexQuestion);

        request.setAttribute("listAnswer", listAnswer);
        request.setAttribute("isCorrect", true);
        request.setAttribute("count", count);
        request.setAttribute("currentIndexQuestion", currentIndexQuestion);
        request.getSession().setAttribute("listQuestion", listQuestion);
        request.getRequestDispatcher("study.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean isCorrect = false;
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            String r_count = request.getParameter("count");
            String r_currentIndexQuestion = request.getParameter("currentIndexQuestion");
            String ans = request.getParameter("ans");
            
            int currentIndexQuestion = Integer.parseInt(r_currentIndexQuestion);
            
            List<Question> listQuestion = (List<Question>) request.getSession().getAttribute("listQuestion");
            List<String> listAnswer = new ArrayList<>();
            
            int count = Integer.parseInt(r_count);
            if (count == 10) {
                isCorrect = true;
                request.setAttribute("count", count);
                request.setAttribute("currentIndexQuestion", currentIndexQuestion);
                request.setAttribute("listAnswer", listAnswer);
                request.getRequestDispatcher("study.jsp").forward(request, response);
            }
            
            if (currentIndexQuestion==listQuestion.size()-1) {
                request.setAttribute("count", count);
                request.setAttribute("currentIndexQuestion", currentIndexQuestion);
                request.setAttribute("listAnswer", listAnswer);
                request.getRequestDispatcher("study.jsp").forward(request, response);
            }

            if (listQuestion.get(currentIndexQuestion).getDefinition().equalsIgnoreCase(ans)) {
                count++;
                currentIndexQuestion++;
                isCorrect = true;
            }else{
                isCorrect = false;
            }
            listAnswer = GetRandomListAnswer(listQuestion, currentIndexQuestion);
            request.setAttribute("isCorrect", isCorrect);
            request.setAttribute("count", count);
            request.setAttribute("currentIndexQuestion", currentIndexQuestion);
            request.setAttribute("listAnswer", listAnswer);
        } catch (Exception e) {
        }
        request.getRequestDispatcher("study.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

//Random 3 cau tra loi tu bo cau hoi de hien thi cho trang lam trac nghiem
    private List<String> GetRandomListAnswer(List<Question> listQuestion, int currentIndexQuestion) {
        List<String> listAnswer = new ArrayList<>();
        int size = listQuestion.size();
        Random rd = new Random();
        
        if (listQuestion.size()<=4) {
            for (Question q : listQuestion) {
                listAnswer.add(q.getDefinition());
            }
            while (listAnswer.size()!=4) {                
                listAnswer.add(listQuestion.get(rd.nextInt(size)).getDefinition());
            }
            return listAnswer;
        }
        
        int count = 0;
        int[] listIndexQuestion = new int[4];
        listIndexQuestion[0] = currentIndexQuestion;
        Question question = new Question();
        int temp = 0;
        while (true) {
            temp = rd.nextInt(size);
            if (!checkDuplicateValue(temp, listIndexQuestion)) {
                count++;
                listIndexQuestion[count] = temp;
            }
            if (count == 3) {
                break;
            }
        }
        //lỗi index liên quan tới id khi lấy dữ liệu: Da sua
        for (int i = 0; i < 4; i++) {
            listAnswer.add(listQuestion.get(listIndexQuestion[i]).getDefinition());
        }
        Collections.shuffle(listAnswer);
        return listAnswer;
    }

    //duplicate du lieu thi tra ve true nguoc lai thi false
    private boolean checkDuplicateValue(int temp, int[] listIndexQuestion) {
        boolean isDup = false;
        for (int i = 0; i < 4; i++) {
            if (temp == listIndexQuestion[i]) {
                return true;
            }
        }
        return isDup;
    }
}
