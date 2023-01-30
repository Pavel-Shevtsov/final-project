<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language = "Java" contentType = "text/html; charset = ISO-8859-1" pageEncoding = "ISO-8859-1" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
    <html>
    <head>
    <meta http-equiv = "Context-Type" context = "text/html charset = ISO-8859-1" >
    <style>
    <%@include file="/WEB-INF/style/creatPostPage.css"%>
    </style>
    <title>Add Topic</title>
    </head>
            <body>
            <button onclick = "location.href = '${pageContext.request.contextPath}/welcome' " >Back</button>
            <div class = "creatPostPage">
                     <form action="${pageContext.request.contextPath}/post/create" method = "post" >
                      <td><input type = "hidden" name="userId" value="${userId}"/></td>
                        <h1>Create Post</h1>
                            	<h2>Tag </h2>
                            	<input type="text"name="Tag" >
                            	<h3> Make this post available only to you </h3>
                                <p><input name="isPrivate" type="radio" value="true">Yes
                                <input name="isPrivate" type="radio" value="false">No</p>
                            	<h2>Text </h2>
                            	<textarea name = "text" ></textarea>

                            	<br><input type = "submit", value = "Add"></br>
                      </form>
            </div>
            </body>
    </html>