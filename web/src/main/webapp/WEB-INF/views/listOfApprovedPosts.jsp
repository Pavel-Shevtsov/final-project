<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language = "Java" contentType = "text/html charset = ISO-8859-1" pageEncoding = "ISO-8859-1" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
    <html>
    <head>
    <style>
    <%@include file="/WEB-INF/style/tablePublicPost.css"%>
    </style>
    <meta http-equiv = "Context-Type" context = "text/html charset = ISO-8859-1" >
    <title>Public posts</title>
    </head>
            <body>
            <button onclick = "location.href = '${pageContext.request.contextPath}/' " >Back</button>
            <h2>${message}</h2>
                        <table align ="center">
                               <th> Tag </th>
                               <th> Publication date </th>
                               <th> Action </th>
                               <c:forEach var="publicPosts" items="${publicPosts}">
                                   <tr>
                                       <td><c:out value ="${publicPosts.tag}"/></td>
                                       <td><c:out value ="${publicPosts.publicationDate}"/></td>
                                   <td> <a class="action" href = "${pageContext.request.contextPath}/post/free/read?id=${publicPosts.id}" >Read</td>
                               </c:forEach>
                        </table>

            </body>
    </html>