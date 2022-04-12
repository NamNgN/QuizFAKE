<%-- 
    Document   : index
    Created on : Mar 8, 2022, 7:37:41 AM
    Author     : ylbee
--%>

<%@page import="dao.CourseDAO"%>
<%@page import="entity.Course"%>
<%@page import="java.util.List"%>
<%@page import="entity.Account"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="template/header.jsp"></jsp:include>
        </head>  
    <body>
        <main>
            <section>

                <form style="border: 1px">
                    <c:forEach items="${crecourse}" var="c">
                        <div id="topsale" class="col-sm-5 col-md-4 col-lg-3">
                            <div class="grid">
                                <div class="portfolio app mix_all" data-cat="app" style="display: inline-block; opacity: 1;">
                                    <div class="product">
                                        <div class="product-img">
                                           <a href="displayQuestion?courseid=${c.courseid}">
                                                <img class="img-responsive" src="image/book.jpg" style="width: 300px; height: 150px"/>

                                            </a>
                                        </div>
                                    </div>
                                </div> 
                                          <p class="text-center" style="font-size: 18px">
                                    ${c.title} <c:if  test="${c.visibleto == true}">
                                        | Private Course
                                    </c:if>
                                    <c:if test="${c.visibleto == false}">
                                    </c:if></p>

                            </div>
                        </div>                
                    </c:forEach>  
                </form>                    
            </section>
        </main>
    </body>
</html>
