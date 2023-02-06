<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language = "Java" contentType = "text/html charset = ISO-8859-1" pageEncoding = "ISO-8859-1" %>
    <html>
    <head>
    <meta http-equiv = "Context-Type" context = "text/html charset = ISO-8859-1" >
    <style>
    <%@include file="/WEB-INF/style/userPage.css"%>
    </style>
    <title>User Page</title>
    </head>
            <body>
                <c:if test="${access.equals('full')}">
                    <button onclick = "location.href='${pageContext.request.contextPath}/welcome'">Back</button>
                    <button onclick = "location.href='${pageContext.request.contextPath}/user/logout'">Logout </button>
                </c:if>
                        <c:if test="${access.equals('semi-restricted')}">
                            <button onclick = "location.href='${pageContext.request.contextPath}/welcome'">Back</button>
                        </c:if>
                                <c:if test="${access.equals('limited')}">
                                    <button onclick = "location.href='${pageContext.request.contextPath}/post/public'">Back</button>
                                </c:if>

                                    <form action="${pageContext.request.contextPath}/user/update" method = "post" >
                                    <h2>${errorMessage}</h2>
                                        <div class = "page">
                                            <td><input type = "hidden" name= "id" value="${userForm.id}"/></td>
                                            <td><input type = "hidden" name= "password" value="${userForm.password}"/></td>
                                            <td><input type = "hidden" name= "dateOfBirth" value="${userForm.dateOfBirth}"/></td>
                                            <td><input type = "hidden" name= "role" value="${userForm.role}"/></td>

                                            <p>Name</p>
                                            <input type="text" readonly name ="username"value=${userForm.username}></p>
                                            <p>Email</p>
                                            <input type="email" readonly name = "email" value=${userForm.email}></p><td></tr>
                                            <p>BirthDay</p>
                                            <input type="date" readonly name = "dateOfBirth" value=${userForm.dateOfBirth}>
                                        </div>

                                            <c:if test="${access.equals('full')}">
                                        <div class = "page">
                                                    <p>New name</p>
                                                    <input type="text" name ="newUsername"></p>
                                                    <p>New Password</p>
                                                    <input type="password" name ="newPassword"></p>
                                                    <p> New Email</p>
                                                    <input type="email" name = "newEmail"></p>
                                                    <p>New BirthDay</p>
                                                    <input type="date" id="start" name="newDateOfBirth"
                                                     value="2010-01-01"   min="1940-01-01" max="2010-01-01">
                                             <p><input type = "submit", value = "Update"><p>
                                        </div>
                                        </c:if>
                                    </form>
                                        <c:if test="${access.equals('full')}">
                                    <table align ="center">
                                                <th> Tag </th>
                                                <th> Date </th>
                                                <th> Last update date</th>
                                                <th> Action </th>
                                            <c:forEach var="myPosts" items="${myPosts}">
                                                    <tr><td>#<c:out value =" ${myPosts.tag} "/></td>
                                                    <td><c:out value = "${myPosts.publicationDate}"/></td>
                                                    <td><c:out value ="${myPosts.lastUpdateDate}"/></td>
                                                    <td><a class="action" href ="${pageContext.request.contextPath}/post/delete?id=${myPosts.id}">Delete</a>
                                                    <a class="action" href ="${pageContext.request.contextPath}/post/update?id=${myPosts.id}"> Update</a>
                                                    <a class="action" href ="${pageContext.request.contextPath}/post/read?id=${myPosts.id}">Read</a></td></tr>
                                            </c:forEach>
                                    </table>
                                        </c:if>

                                        <c:if test="${access.equals('semi-restricted')}">
                                         <table align ="center">
                                                     <th> Tag </th>
                                                     <th> Date </th>
                                                     <th> Last update date</th>
                                                     <th> Action </th>
                                                 <c:forEach var="allRelatedPostsOfAUser" items="${allRelatedPostsOfAUser}">
                                                         <tr><td>#<c:out value =" ${allRelatedPostsOfAUser.tag} "/></td>
                                                         <td><c:out value = "${allRelatedPostsOfAUser.publicationDate}"/></td>
                                                         <td><c:out value ="${allRelatedPostsOfAUser.lastUpdateDate}"/></td>
                                                         <td><a class="action" href ="${pageContext.request.contextPath}/post/read?id=${allRelatedPostsOfAUser.id}">Read</a></td></tr>
                                                 </c:forEach>
                                         </table>
                                        </c:if>

                                            <c:if test="${access.equals('limited')}">
                                            <table align ="center">
                                                        <tr>
                                                        <th> Tag </th>
                                                        <th> Date </th>
                                                        <th> Last update date</th>
                                                        <th> Action </th>
                                                        </tr>
                                                    <c:forEach var="allRelatedPostsOfAUser" items="${allRelatedPostsOfAUser}">
                                                            <tr><td>#<c:out value =" ${allRelatedPostsOfAUser.tag} "/></td>
                                                            <td><c:out value = "${allRelatedPostsOfAUser.publicationDate}"/></td>
                                                            <td><c:out value ="${allRelatedPostsOfAUser.lastUpdateDate}"/></td>
                                                            <td><a class="action" href ="${pageContext.request.contextPath}/post/free/read?id=${allRelatedPostsOfAUser.id}">Read</a></td></tr>
                                                    </c:forEach>
                                            </table>
                                            </c:if>
            </body>
    </html>