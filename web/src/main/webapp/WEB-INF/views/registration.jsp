<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language = "Java" contentType = "text/html charset = ISO-8859-1" pageEncoding = "ISO-8859-1" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
    <html>
    <head>
    <meta http-equiv = "Context-Type" context = "text/html charset = ISO-8859-1" >
     <style>
     <%@include file="/WEB-INF/style/registrationStyle.css"%>
     </style>
    <title>Account registration</title>
    </head>
            <body
                                            <h3>${errorMessage}<h3>

                                   <form action="${pageContext.request.contextPath}/registration" method = "post" >
                                   <div class = "registrationPage">
                                    <h3> Registration </h3>
                                                 <br><input type="text" name = "username" required = "required" placeholder="Username"></br>
                                                 <br><input type="password" name = "password" required = "required" placeholder="Password"></br>
                                                 <br><input type="password" name = "confirmPassword" required = "required"  placeholder="Confirm Password"></br>
                                                 <br><input type="date" id="start" name="dateOfBirth"
                                                     value="2010-01-01"   min="1940-01-01" max="2010-01-01" placeholder="eMail"> </br>
                                                </br> <input type="email" name = "email" required = "required" placeholder="eMail"></br>
                                                  <br><input type = "submit", value = "Registration">
                                        </form>

                            <button onclick = "location.href = '${pageContext.request.contextPath}' " >Back</button></br>
                   </div>          </div>
            </body>
    </html>