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
                <h2>Tag </h2>
                    <input type="text" value = ${postForm.tag} readonly >
                <h2>Text</h2>
                    <textarea name = "text" readonly> ${postForm.text}</textarea>
                <h2> Author : <a class="action" href = "${pageContext.request.contextPath}/user/page?id=${author.id}">${author.username}</a></h2>
                    <button onclick = "location.href = '${pageContext.request.contextPath}/post/public'">Back</button>
            </body>
    </html>