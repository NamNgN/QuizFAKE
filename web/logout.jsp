<%@page import="entity.Account"%>
<%
    Account user = (Account) request.getSession().getAttribute("currAccount");
    if (user!= null) {
            session.removeAttribute("currAccount");
            response.sendRedirect("index.jsp");
        }
%>