<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language = "Java" contentType = "text/html charset = ISO-8859-1" pageEncoding = "ISO-8859-1" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
    <html>
    <head>
    <style>
    <%@include file="/WEB-INF/style/usersPage.css"%>
    </style>
    <meta http-equiv = "Context-Type" context = "text/html charset = ISO-8859-1" >
    <title>All Users</title>
    </head>
            <body>
             <button onclick = "location.href='${pageContext.request.contextPath}/welcome'">Back</button>
            <form action="${pageContext.request.contextPath}/user/search" method = "post" >
            <br><input type="text" name = "searchParameter" required = "required" placeholder="Search"> <input type = "submit", value = "Search"></br>
            </form>
             <h3>${notFound}<h3>
                  <table align = "center">
                          <tbody>
                            <c:forEach var="user" items="${otherUsers}">
                                <tr>
                                    <td><c:out value ="${user.username}"/></td>
                                    <td><c:out value ="${user.email}"/></td>
                                    <td><c:out value ="${user.role}"/></td>
                                    <td><c:out value ="${user.dateOfBirth}"/></td>
                                <td><a class="action" href = "${pageContext.request.contextPath}/user/delete?id=${user.id}" >Delete</a>
                                <a class="action" href ="${pageContext.request.contextPath}/user/page?id=${user.id}"> User page </a></p></td>
                                 </tr>
                            </c:forEach>
                          </tbody>
                  </table>

            </body>
    </html>