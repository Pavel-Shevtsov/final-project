<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language = "Java" contentType = "text/html charset = ISO-8859-1" pageEncoding = "ISO-8859-1" %>

    <html>
    <head>
    <meta http-equiv = "Context-Type" context = "text/html charset = ISO-8859-1" >
    <style>

    </style>
    <title>Start page</title>
    <head>
    </head>
    <body>
        <button onclick = "location.href='${pageContext.request.contextPath}/login'">Login </button>
    	<button onclick = "location.href='${pageContext.request.contextPath}/registration'">Registration </button>
    </body>
    </html>
