<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language = "Java" contentType = "text/html; charset = ISO-8859-1" pageEncoding = "UTF-8" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
    <html>
    <head>
    <meta http-equiv = "Context-Type" context = "text/html charset = ISO-8859-1" >
    <style>

    </style>
    <title>Welcome</title>
    </head>
            <body>
                                 <div class ="welcomeMessage">
                                      <c:if test = "${visitCounter<1}">
                                      <h1>Welcome ${user.username}</h1>
                                      </c:if>
                                 </div>
                <table>
                    <security:authorize access = "hasRole('ROLE_Admin')">
                        <thead>
                            <th >Not approved</th>
                        </thead>
                            <tbody>
                                <c:forEach var="notApproved" items="${notApproved}">
                                    <tr>
                                        <td><c:out value ="${notApproved.tag}"/> <a class="action" href ="${pageContext.request.contextPath}/topic/delete?id=${notApproved.id}">Go to</a></td>
                                    </tr>
                            </tbody>
                                </c:forEach>
                   </security:authorize>

                                <security:authorize access = "hasRole('ROLE_User')">
                                      <form action="${pageContext.request.contextPath}/welcome" method = "get" >
                                          <thead>
                                              <th >All topics</th>
                                              <th >Action</th>
                                          </thead>
                                              <tbody>
                                                  <c:forEach var="userMomentOfLife" items="${userMomentOfLife}">
                                                      <tr>
                                                          <td><c:out value ="${userMomentOfLife.tag}"/></td>
                                                          <td><p><a class="action" href ="${pageContext.request.contextPath}/topic/delete?id=${userMomentOfLife.id}">Delete</a></p>
                                                          <p><a class="action" href ="${pageContext.request.contextPath}/post/posts?id=${userMomentOfLife.id}">Go to</a></p></td>
                                                      </tr>
                                              </tbody>
                                                  </c:forEach>
                                      </form>
                                </security:authorize>
                </table>
            </body>
    </html>