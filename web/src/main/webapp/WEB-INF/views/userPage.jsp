<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language = "Java" contentType = "text/html charset = ISO-8859-1" pageEncoding = "ISO-8859-1" %>
    <html>
    <head>
    <meta http-equiv = "Context-Type" context = "text/html charset = ISO-8859-1" >

    <title>User Page</title>
    </head>
            <body>
                                    <form action="${pageContext.request.contextPath}/user/update" method = "post" >
                                            <td><input type = "hidden" name= "id" value="${userForm.id}"/></td>
                                            <td><input type = "hidden" name= "password" value="${userForm.password}"/></td>
                                            <td><input type = "hidden" name= "birthday" value="${userForm.birthday}"/></td>
                                            <td><input type = "hidden" name= "role" value="${userForm.role}"/></td>


                                            <tr><td><p>Name
                                            <input type="text" readonly name ="username"value=${userForm.username}></p>
                                            <p>Email
                                            <input type="email" readonly name = "email" value=${userForm.email}></p><td></tr>
                                            <p>BirthDay
                                            <input type="text" readonly name = "birthday" value=${userForm.birthday}></p><td></tr>

                                            <c:if test="${access.equals('full')}">
                                            <table>
                                                    <tr><td><p>New name
                                                    <input type="text" name ="newUsername"></p>
                                                    <p>New Password
                                                    <input type="password" name ="newPassword"></p>
                                                    <p> New Email
                                                    <input type="email" name = "newEmail"></p>
                                                    <p>New BirthDay
                                                    <input type="date" id="start" name="newBirthday"
                                                     min="1940-01-01" max="2010-31-12" ></p><td></tr>
                                            </table>

                                             <input type = "submit", value = "Update">


                                    </form>
                                    <h2 align = "center">All my posts<h2>
                                    <table align = "center">

                                                <tr>
                                                <th> Tag </th>
                                                <th> Date </th>
                                                <th> Last update date</th>
                                                <th> Action </th>
                                                </tr>
                                            <td><c:forEach var="myPosts" items="${myPosts}"><td>

                                                    <tr><td>#<c:out value =" ${myPosts.tag} "/></td>
                                                    <td><c:out value = "${myPosts.publicationDate}"/></td>
                                                    <td><c:out value ="${myPosts.lastUpdateDate}"/></td>
                                                    <td><a class="action" href ="${pageContext.request.contextPath}/post/delete?id=${myPosts.id}">Delete</a>
                                                    <a class="action" href ="${pageContext.request.contextPath}/post/update?id=${myPosts.id}"> Update</a>
                                                    <a class="action" href ="${pageContext.request.contextPath}/post/read?id=${myPosts.id}">Read</a></td></tr>

                                            </c:forEach>
                                    </table>
                                     <td> <a class="action" href = "${pageContext.request.contextPath}/welcome">Back</td>
                                     <td> <a class="action" href = "${pageContext.request.contextPath}/user/logout">Logout</td>
                                        </c:if>


                                            <c:if test="${access.equals('limited')}">


                                             <td> <a class="action" href = "${pageContext.request.contextPath}/post/public">Back</td>


                                            </c:if>
            </body>
    </html>