<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language = "Java" contentType = "text/html charset = ISO-8859-1" pageEncoding = "ISO-8859-1" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
    <html>
    <head>
    <meta http-equiv = "Context-Type" context = "text/html charset = ISO-8859-1" >

    <title>Account registration</title>
    </head>
            <body
                                            ${errorMessage}
                                       <h1> Registration <h1>
                                   <form action="${pageContext.request.contextPath}/addUser" method = "post" >
                                                 <p>UserName</p>
                                                 <h2><input type="text" name = "username" required = "required"></h2>
                                                 <p>Password</p>
                                                 <input type="password" name = "password" required = "required">
                                                 <p> Confirm Password </p>
                                                 <input type="password" name = "confirmPassword" required = "required">
                                                 <p>  email </p>
                                                 <p><input type="email" name = "email" required = "required"><p>
                                                  <input type = "submit", value = "Registration">
                                        </form>

                            <button onclick = "location.href = '${pageContext.request.contextPath}' " >Back</button>
                   </div>
            </body>
    </html>