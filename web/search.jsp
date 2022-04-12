<%-- 
    Document   : home
    Created on : Nov 2, 2021, 10:35:16 PM
    Author     : Asus
--%>

<%@page import="entity.Course"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>QuizFAKE</title>

        <link rel="stylesheet" href="css/bootstrap.min.css">
        <!-- Icons -->
        <link rel="stylesheet" href="css/font-awesome.css">
        <link rel ="stylesheet" href="css/styleindex.css" >
    </head>
    <%
        List<Course> lsCourse = (List<Course>) request.getAttribute("listCourse");
    %>
    <body>
        <div class="container-fluid ">

            <!--menu content-->

            <!--slider content-->
            <%@include file="template/header.jsp" %>

            <div class="row">
                <div>
                    <h1>Result: <%=lsCourse.size()%></h1>
                </div>
            </div>
            <div class="row" class="col-sm-6">

                <c:forEach items="${listCourse}" var="c">

                    <div id="topsale" class="col-sm-5 col-md-4 col-lg-3">
                        <div class="grid">
                            <div class="portfolio app mix_all" data-cat="app" style="display: inline-block; opacity: 1;">
                                <div class="product">
                                    <div class="product-img">
                                        <a href="displayQuestion?courseid=${c.getCourseid()}">
                                            <img class="img-responsive" src="image/book.jpg" style="width: 300px; height: 250px"/>

                                        </a>
                                    </div>
                                </div>
                            </div>
                            <p class="text-center">
                                ${c.getTitle()}</p> 
                        </div>
                    </div>
                </c:forEach>
            </div>
            <%@include file="template/footer.jsp" %>
        </div>
        <script src="js/Jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/script.js"></script>
        <script src="js/jquery-1.12.4.js"></script>
        <script src="js/jquery-ui.js"></script>

    </body>
</html>
