<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language = "Java" contentType = "text/html charset = ISO-8859-1" pageEncoding = "ISO-8859-1" %>
    <html>
    <head>
    <meta http-equiv = "Context-Type" context = "text/html charset = ISO-8859-1" >

    <title>Update </title>
    </head>
            <body>
                                    <h3>${errorMessage}<h3>
                                    <form action="${pageContext.request.contextPath}/user/update" method = "post" >
                                            <td><input type = "hidden" name= "id" value="${userUpdateForm.id}"/></td>
                                            <td><input type = "hidden" name= "username" value="${userUpdateForm.username}"/></td>
                                            <td><input type = "hidden" name= "birthday" value="${userUpdateForm.birthday}"/></td>
                                            <td><input type = "hidden" name= "email" value="${userUpdateForm.email}"/></td>
                                            <td><input type = "hidden" name= "password" value="${userUpdateForm.password}"/></td>
                                            <td><input type = "hidden" name= "role" value="${userUpdateForm.role}"/></td>

                                            <p>New Name
                                            <input type="text" name = "newUsername" placeholder="${userUpdateForm.username}" ></p>
                                            <p>New Password
                                            <input type="password" name = "newPassword" ></p>
                                            <p> New Email
                                            <input type="email" name = "newEmail" placeholder="${userUpdateForm.email}" ></p>
                                            <p>New BirthDay
                                            <input type="date" id="start" name="newBirthday"
                                             min="1940-01-01" max="2010-31-12" placeholder="${userUpdateForm.birthday}" ></p>
                                        <input type = "submit", value = "Update">
                                    </form>
                            <button onclick = "location.href = '${pageContext.request.contextPath}/welcome' ">Back </button>
            </body>
    </html>
