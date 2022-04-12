<%-- 
    Document   : display_question
    Created on : Mar 5, 2022, 10:57:48 PM
    Author     : dinhthituoi
--%>


<!--<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">-->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="template/header.jsp"></jsp:include>
    <main>
        <section><div class="container">
                <h1>${courseTitle}</h1>
                <h3>Number answer correct ${correctAnswer}/${listQuestion.size()}</h3>
            <h1><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${score}" /></h1>
            <!--<h1>Score ${score}</h1>-->
            <div class="row">

                <c:forEach items="${listQuestion}" var="q"   >
                    <div class="col-md-9" style="margin: 20px">
                        <div class="card" >
                            <div style="border: solid graytext; padding: 10px 10px 10px 10px;">
                                <h5 class="card-title">
                                    <c:out value="${q.getTerm()}"></c:out>
                                    </h5>
                                    <p name="${q.getQuestionid()}" >
                                    Cau tra loi cua ban: ${requestScope[q.getIdString()]}
                                    <c:if test="${q.isIsCorrect()}">
                                        <span><img src="./image/check-mark.png"/></span>
                                        </c:if>
                                        <c:if test="${q.isIsCorrect()==false}">
                                        <span><img src="./image/close.png"/></span>
                                        </c:if>
                                </p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>
</main>
<%@include file="template/footer.jsp" %>