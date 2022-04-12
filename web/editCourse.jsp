<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.Course"%>
<%@page import="entity.Question"%>
<%@page import="java.util.List"%>
<jsp:include page="template/header.jsp"></jsp:include>
<%
    Course course = (Course) request.getAttribute("course");
    List<Question> questions = (List<Question>) request.getAttribute("lsQuestion");
    int count = questions.size() + 1;
%>
<script>
    var count = <%=count%>;
    function addQuestionInput() {
        var element = document.getElementById("question");
        var x = document.createElement("INPUT");
        x.setAttribute("type", "text");
        x.setAttribute("placeholder", "Term " + count);
        x.setAttribute("name", "term" + count);
        x.setAttribute("delete", +count);
        element.appendChild(x);
        var y = document.createElement("INPUT");
        y.setAttribute("type", "text");
        y.setAttribute("placeholder", "Definition " + count);
        y.setAttribute("name", "def" + count);
        element.appendChild(y);
        var z = document.createElement("BR");
        element.appendChild(z);
        count += 1;
    }
</script>
<main>
    <section>
        <form action="editCourse" method="POST"  style="text-align: center">
            <input type="hidden" name="courseid" value="<%=course.getCourseid()%>">
            <input type="text" placeholder="Title" id="name-6797" name="title" required="" value="<%=course.getTitle()%>" style="padding: 20px">
            <input type="text" placeholder="Description" id="email-6797" name="description" required="" value="<%=course.getDescription()%>"style="padding: 20px">
            <select name="visibleto" style="margin: 10px" >
                <option value="0" <%=((course.isVisibleto() == false) ? "selected" : "")%>>Public</option>
                <option value="1" <%=((course.isVisibleto() == true) ? "selected" : "")%>>Private</option>
            </select>
            <input type="submit" value="Save"><br/>
            <button onclick="addQuestionInput(); return false;" style="margin: 10px">More question</button><br/>
            <div id="question" style="margin-bottom:  100px; padding: 20px">
                <%
                    int countQuestion = 1;  
                %>
                <c:forEach items="<%=questions%>" var="q">
                    <input type="hidden" name="questionid<%=countQuestion%>" value="${q.questionid}">
                    <input type="txt" name="term<%=countQuestion%>" placeholder="term <%=countQuestion%>" value="${q.term}">
                    <input type="txt" name="def<%=countQuestion%>" placeholder="definition <%=countQuestion%>" value="${q.definition}">
                    <a class="glyphicon glyphicon-trash" 
                       href="RemoveQuestionController?cid=${q.getCourseid()}&qid=${q.getQuestionid()}" 
                       onclick="return confirm('Do you want to delete this question with id ${q.getQuestionid()} ?');" 
                       class="btn btn-primary"></a><br/>
                    <%
                        countQuestion += 1;
                    %>
                </c:forEach>
            </div>
        </form>
    </section>
</main>
<%@include file="template/footer.jsp" %>