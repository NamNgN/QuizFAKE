<%-- 
    Document   : display_question
    Created on : Mar 5, 2022, 10:57:48 PM
    Author     : dinhthituoi
--%>
<%@page import="entity.Account"%>
<%@page import="entity.Question"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dao.CourseDAO"%>
<%@page import="entity.Course"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="template/header.jsp"></jsp:include>
    <!--<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">-->
    <main>
    <%
        List<Question> lsQuestion = (List<Question>) request.getAttribute("listQuestion");
        Account account = (Account) request.getSession().getAttribute("currAccount");
        String username = (String) request.getAttribute("usernamecurr");
        int courseid = Integer.parseInt(request.getParameter("courseid"));
        Course course = new CourseDAO().getCourseById(courseid);
    %>
    <section>
        <div class="container">
            <h1>Course name: <%=course.getTitle()%></h1>
            <h2>Description: <%=course.getDescription()%></h2>
            <h2>Number of question: ${listQuestion.size()}</h2>
            <a href="study?courseid=${courseid}">Study course</a> |
            <a href="test?courseid=${courseid}">Test course</a> |
            <%if (account != null && account.getUsername().equals(username)) {%>
            <a href="editCourse?courseid=${courseid}">Edit course</a> |
            <%}%>
            <a href="profile?username=${usernamecurr}">${usernamecurr}</a>
            <%if (lsQuestion == null || lsQuestion.size() == 0) {%>
            <h4 style='color:red'>This course dont have any question</h4>
            <%}%>
            <div class="row">
                <c:forEach items="${listQuestion}" var="q" varStatus="s"  >
                    <div class="col-md-9" style="margin: 20px">
                        <div>
                            <div class="card-body">
                                <h5 class="card-title">
                                    <c:out value="${q.getTerm()}"></c:out>
                                    </h5>
                                    <a href="#" class="btn btn-primary"><c:out value="${q.getDefinition()}"></c:out></a>
                                </div>
                            </div>
                        </div>
                </c:forEach>
            </div>
        </div>
    </section>
</main>
<%@include file="template/footer.jsp" %>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>