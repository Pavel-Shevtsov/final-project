<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language = "Java" contentType = "text/html charset = ISO-8859-1" pageEncoding = "ISO-8859-1" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
    <html>
    <head>
    <meta http-equiv = "Context-Type" context = "text/html charset = ISO-8859-1" >
    <style><%@include file="/WEB-INF/style/styleBody.css"%></style>
    <style><%@include file="/WEB-INF/style/styleWelcomePage.css"%></style>
    <style><%@include file="/WEB-INF/style/stylePanel.css"%></style>
    <style><%@include file="/WEB-INF/style/styleDropDownUser.css"%></style>
    <style><%@include file="/WEB-INF/style/styleTable.css"%></style>
    <style><%@include file="/WEB-INF/style/photoViews.css"%></style>
    <title>All Users</title>

    </head>
            <body>


                <div class= "message">
                    ${updateUserPassword}
                </div>
                        <table>

                                    <tbody>
                                      <c:forEach var="user" items="${otherUsers}">
                                          <tr>
                                              <td><c:out value ="${user.userName}"/></td>
                                              <td><c:out value ="${user.password}"/></td>
                                              <td><c:out value ="${user.email}"/></td>
                                              <td><c:out value ="${user.role}"/></td>
                                          <td><a class="action" href = "${pageContext.request.contextPath}/user/update?id=${user.id}" > Update
                                          <a class="action" href = "${pageContext.request.contextPath}/user/delete?id=${user.id}" >   Delete</td>
                                           </tr>
                                      </c:forEach>
                                    </tbody>
                        </table>
                    </form>
            </body>
    </html>