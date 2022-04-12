<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.Question"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="template/header.jsp"></jsp:include>
    <main>
        <style>
            section{
                margin: auto;
                width: 50%;
                padding: 10px 10px 10px 10px;
            }
        </style>
        <section>
        <%
            String quiz = "";
            String ans = "";
            List<Question> listQuestion = (List<Question>) request.getSession().getAttribute("listQuestion");
            
            List<String> listAnswer = (List<String>) request.getAttribute("listAnswer");
            int count = (Integer) request.getAttribute("count");
            int currentIndexQuestion = (Integer) request.getAttribute("currentIndexQuestion");
//        int count = Integer.parseInt((String) request.getAttribute("count"));
//        int currentIndexQuestion = Integer.parseInt((String) request.getAttribute("currentIndexQuestion"));
            quiz = listQuestion.get(currentIndexQuestion).getTerm();
            ans = listQuestion.get(currentIndexQuestion).getDefinition();
            Boolean isCorrect = (Boolean) request.getAttribute("isCorrect");
        %>
        <div>
            <h1 style="color: gray">${currCourse.title}</h1>
            <%if (count == 10) {
                currentIndexQuestion-=1;
            %>
            <form action="study" method="POST">
                <input value="<%=currentIndexQuestion%>" name="currentIndexQuestion" hidden="">
                <p><%=currentIndexQuestion+1%> / <%=listQuestion.size()-1%> terms</p>
                <%for (int i = currentIndexQuestion; count != 0; i--) {
                        count--;
                        quiz = listQuestion.get(i).getTerm();
                        ans = listQuestion.get(i).getDefinition();
                %>
                <p><%=quiz%> | <%=ans%></p>
                <%}%>
                <%count-=1;%>
                <input value="<%=count%>" name="count" value="<%=count%>" hidden="">
                <input type="hidden" name="ans" value="<%=listQuestion.get(currentIndexQuestion).getDefinition()%>">
                <input type="submit" value="Continue">
            </form>
            <%} else if (currentIndexQuestion == listQuestion.size()-1) {%>
            <a href="displayQuestion?courseid=<%=listQuestion.get(0).getCourseid()%>">Back to course</a> | 
            <a href="study?courseid=${currCourse.courseid}">Study again</a>
            <p><%=currentIndexQuestion%> / <%=listQuestion.size()-1%> terms</p>
            <%listQuestion.remove(listQuestion.size()-1);%>
            <c:forEach items="<%=listQuestion%>" var="q">
                <p>${q.term} | ${q.definition}</p>
            </c:forEach>
            <%} else {%>
            <form action="study" method="POST">
                <p><%=currentIndexQuestion + 1%> / <%=listQuestion.size()-1%> terms</p>
                <p style="color: red" <%=((isCorrect) ? "hidden" : "")%>>Your Answer is incorrect. Try again!</p>
                <p style="color: green" <%=((isCorrect) ? "hidden" : "")%>>Correct Answer is <%=ans%></p>
                <input value="<%=count%>" name="count" hidden="">
                <input value="<%=currentIndexQuestion%>" name="currentIndexQuestion" hidden="">
                <%=quiz%><br/>
                <c:forEach items="${listAnswer}" var="ans" >  
                    <input type="radio" name="ans" value="${ans}"> ${ans}<br/>
                </c:forEach>
                <input type="submit" value="Submit">
                <br/>
            </form>
            <%}%>
        </div>
    </section>
</main>
<%@include file="template/footer.jsp" %>
