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

                                      <c:if test = "${visitCounter<2}">
                                      <h2>Welcome ${user.username}</h2>
                                      </c:if>


                <table>
                    <security:authorize access = "hasRole('ROLE_Admin')">
                        <thead>
                            <th >For approval</th>
                        </thead>
                            <tbody>
                                <c:forEach var="notApproved" items="${notApproved}">
                                    <tr>
                                        <td><c:out value ="${notApproved.tag}"/><td><a class="action" href ="${pageContext.request.contextPath}/post/read?id=${notApproved.id}">Read</a></td></td>
                                    </tr>
                            </tbody>
                                </c:forEach>
                   </security:authorize>

                                <security:authorize access = "hasRole('ROLE_User')">

                                          <thead>
                                              <th> Tag </th>
                                              <th> Date </th>
                                              <th> Last update date</th>
                                              <th> Action </th>
                                          </thead>
                                              <tbody>
                                                  <c:forEach var="userPost" items="${userPost}">
                                                      <tr>
                                                          <td>#<c:out value =" ${userPost.tag} "/></td>
                                                          <td><c:out value =" ${userPost.publicationDate} "/></td>
                                                          <td><c:out value =" ${userPost.lastUpdateDate} "/></td>
                                                          <td><a class="action" href ="${pageContext.request.contextPath}/post/read?id=${userPost.id}">Read</a></td>
                                                      </tr>
                                              </tbody>
                                                  </c:forEach>

                                </security:authorize>
                </table>
                <a class="action" href ="${pageContext.request.contextPath}/user/page?id=${user.id}"> My page </a></p>
                 <a class="action" href ="${pageContext.request.contextPath}/user/update?id=${user.id}"> Update </a></p>
                  <a class="action" href ="${pageContext.request.contextPath}/post/create?id=${user.id}"> Create post </a></p>

            </body>
    </html>