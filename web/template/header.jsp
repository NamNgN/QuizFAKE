<%-- 
    Document   : header
    Created on : Oct 23, 2021, 3:00:39 PM
    Author     : Asus
--%>


<%@page import="entity.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <!-- Icons -->
        <link rel="stylesheet" href="css/font-awesome.css">
        <link rel ="stylesheet" href="css/styleindex.css" >
        <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
        <title>JSP Page</title>
    </head>
    <script>
        function search_word() {
            $(document).ready(function () {
                $('#keySearch').autocomplete({
                    source: `${pageContext.request.contextPath}/search`,
                    select: function (event, ui) {
                        window.location = `${pageContext.request.contextPath}/search?term=` + ui.item.value + '&result=true';
                    }
                });
            });
        }
    </script>
    <%
        Account account = (Account) request.getSession().getAttribute("currAccount");
    %>
    <body>
        <header>
                <div class="container-fluid " >
                    <!--menu content-->
                    <div class="row">
                        <nav class="navbar navbar-default">
                            <!-- Brand and toggle get grouped for better mobile display -->
                            <div class="navbar-header">
                                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                                    <span class="sr-only">Toggle navigation</span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                </button>
                            </div>
                            <!-- Collect the nav links, forms, and other content for toggling -->
                            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                                <ul class="nav navbar-nav">
                                    <li><a style="font-size: 25px;" href="home">QuizFAKE</a></li>
                                    <li><a href="home">Home <span class="sr-only">(current)</span></a></li>
                                    <li><a href="createCourse">Create</a></li>
                                </ul>
                                <form action="search" method="post">
                                    <div class="form-group">
                                        <input type="search" id="keySearch" class="form-control" placeholder="Search" name="key" class="textbox" oninput="search_word()">
                                    </div>
                                    <button type="submit" class="btn btn-default" onclick="this.form.submit">Search</button>
                                </form>
                                <form class="navbar-form navbar-right" method="get">
                                    <% if (account != null) {%>
                                    Welcome: <%=account.getUsername()%> | 
                                    <a href="logout">Log out</a> | 
                                    <a href="profile" >Profile page</a>
                                    <%} else {%>
                                    <a href="login">Login</a> | 
                                    <a href="register">Sign up</a>
                                    <%}%>
                                </form>
                            </div>
                        </nav>
                    </div>
                </div>
        </header>

    <script src="js/Jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/script.js"></script>
    <script src="js/jquery-1.12.4.js"></script>
    <script src="js/jquery-ui.js"></script>

</html>
