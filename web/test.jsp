<%-- 
    Document   : display_question
    Created on : Mar 5, 2022, 10:57:48 PM
    Author     : dinhthituoi
--%>

<%@page import="dao.CourseDAO"%>
<%@page import="entity.Course"%>
<!--<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">-->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="template/header.jsp"></jsp:include>
    <main>
        <section>
            <div class="container">
                <h1>${courseTitle}</h1>
                <h3>Number of question: ${listQuestion.size()}</h3>
            <div class="row">
                <form action="test?courseid=${listQuestion.get(0).getCourseid()}" method="POST">
                    <c:forEach items="${listQuestion}" var="q"   >
                        <div class="col-md-9" style="margin: 20px">
                            <div style="border: solid graytext; padding: 10px 10px 10px 10px;">
                                <div class="card-body">
                                    <h5 class="card-title"><c:out value="${q.getTerm()}"></c:out></h5>
                                    <input name="${q.getQuestionid()}"/>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    </section>
</main>
<%@include file="template/footer.jsp" %>