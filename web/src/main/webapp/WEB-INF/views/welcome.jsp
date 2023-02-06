<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language = "Java" contentType = "text/html; charset = ISO-8859-1" pageEncoding = "UTF-8" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
    <html>
    <head>
    <meta http-equiv = "Context-Type" context = "text/html charset = ISO-8859-1" >
    <style>
    <%@include file="/WEB-INF/style/welcomePage.css"%>
    </style>
    <title>Welcome</title>
    </head>
            <body>
                <div class = "welcomePage">
                    <c:if test = "${visitCounter<2}">
                        <h2>Welcome ${user.username}</h2>
                    </c:if>
                        <table>
                            <security:authorize access = "hasRole('ROLE_Admin')">
                                    <th>For approval</th>
                                    <th>Action</th>

                                        <c:forEach var="notApproved" items="${notApproved}">
                                            <tr>
                                                <td><c:out value ="${notApproved.tag}"/></td>
                                                <td><a class="action" href ="${pageContext.request.contextPath}/post/read?id=${notApproved.id}">Read</a></td>
                                            </tr>
                                        </c:forEach>
                                        <div class = "navigationBar">
                                            <button onclick = "location.href = '${pageContext.request.contextPath}/user/users' " >My users</button>
                            </security:authorize>
                            <button onclick = "location.href='${pageContext.request.contextPath}/user/logout'">Logout </button>
                            <a class="action" href ="${pageContext.request.contextPath}/user/page?id=${user.id}"> My page </a>
                                              <a class="action" href ="${pageContext.request.contextPath}/post/create?id=${user.id}"> Create post </a>
                                         </div>
                                        <security:authorize access = "hasRole('ROLE_User')">
                                        <form action="${pageContext.request.contextPath}/post/search" method = "post" >
                                        <br><input type="text" name = "searchParameter" required = "required" placeholder="Search"> <input type = "submit", value = "Search"></br>
                                        </form>
                                        <h3>${notFound}<h3>
                                                      <th> Tag </th>
                                                      <th> Date </th>
                                                      <th> Last update date</th>
                                                      <th> Action </th>
                                                          <c:forEach var="userPost" items="${userPost}">
                                                              <tr>
                                                                  <td>#<c:out value =" ${userPost.tag} "/></td>
                                                                  <td><c:out value =" ${userPost.publicationDate} "/></td>
                                                                  <td><c:out value =" ${userPost.lastUpdateDate} "/></td>
                                                                  <td><a class="action" href ="${pageContext.request.contextPath}/post/read?id=${userPost.id}">Read</a></td>
                                                              </tr>
                                                          </c:forEach>
                                        </security:authorize>
                        </table>
                </div>
            </body>
    </html>