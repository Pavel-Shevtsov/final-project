<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language = "Java" contentType = "text/html; charset = ISO-8859-1" pageEncoding = "ISO-8859-1" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
    <html>
    <head>
    <meta http-equiv = "Context-Type" context = "text/html charset = ISO-8859-1" >
    <style>
    <%@include file="/WEB-INF/style/readPostPage.css"%>
    </style>
    <title>Read Post</title>
    </head>
            <body>
            <div class = "readPostPage">
             <form action="${pageContext.request.contextPath}/post/approved" method = "post" >
             <td><input type = "hidden" name= "id" value="${postForm.id}"/></td>
             <td><input type = "hidden" name= "isPrivate" value="${postForm.isPrivate}"/></td>
             <td><input type = "hidden" name= "publicationDate" value="${postForm.publicationDate}"/></td>
             <td><input type = "hidden" name= "lastUpdateDate" value="${postForm.lastUpdateDate}"/></td>
                <h2>Tag
                    <input type="text" name = "tag" value = ${postForm.tag} readonly ></h2>
                <h2>Text</h2>
                    <textarea   name = "text" readonly> ${postForm.text}</textarea>
                <h2>Author: <a class="action" href = "${pageContext.request.contextPath}/user/page?id=${author.id}">${author.username}</a></h2>
                <security:authorize access = "hasRole('ROLE_Admin')">
                    <p><input name="isApprovedForPublication" type="radio" value="true">Yes</p>
                    <p><input name="isApprovedForPublication" type="radio" value="false">No</p>
                <input type = "submit" value = Approved>
                </security:authorize>
                </form>
                <c:if test="${access.equals('limited')}">
                <button onclick = "location.href = '${pageContext.request.contextPath}/post/public' " >Back</button>
                </c:if>
                <c:if test="${access.equals('preLimited')}">
                <button onclick = "location.href = '${pageContext.request.contextPath}/welcome' " >Back</button>
                </c:if>
                </div>
            </body>
    </html>