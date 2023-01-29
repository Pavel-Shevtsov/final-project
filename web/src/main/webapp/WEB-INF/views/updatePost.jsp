<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language = "Java" contentType = "text/html; charset = ISO-8859-1" pageEncoding = "ISO-8859-1" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
    <html>
    <head>
    <meta http-equiv = "Context-Type" context = "text/html charset = ISO-8859-1" >
    <style>

    </style>
    <title>Add Topic</title>
    </head>
            <body>


                     <form action="${pageContext.request.contextPath}/post/update" method = "post" >
                     <td><input type = "hidden" name= "id" value="${postForm.id}"/></td>
                     <td><input type = "hidden" name= "tag" value="${postForm.tag}"/></td>
                     <td><input type = "hidden" name= "text" value="${postForm.text}"/></td>
                     <td><input type = "hidden" name= "publicationDate" value="${postForm.publicationDate}"/></td>
                     <td><input type = "hidden" name= "lastUpdateDate" value="${postForm.lastUpdateDate}"/></td>
                     <td><input type = "hidden" name= "isApprovedForPublication" value="${postForm.isApprovedForPublication}"/></td>


                            	<h2> New Tag </h2>
                            	    <input type="text"  name="newTag" value =${postForm.tag}>
                            	<h2>New text </h2>

                            	<textarea name = "newText" >${postForm.text}</textarea>
                            	<h2> Make this post available only to you </h2>
                                <p><input name="isPrivate" type="radio" value="true">Yes</p>
                                <p><input name="isPrivate" type="radio" value="false">No</p>
                                <input type = "submit", value = "Update">
                     </form>
                                <button onclick = "location.href = '${pageContext.request.contextPath}/welcome' " >Back</button>


            </body>
    </html>