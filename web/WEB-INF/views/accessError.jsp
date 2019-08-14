<%--
  Created by IntelliJ IDEA.
  User: lctopia1
  Date: 2019-08-12
  Time: 오후 8:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    ${msg}
<h1>
    <c:out value="${SPRING_SECURITY_403_EXCEPTION.getMessage()}"/>
</h1>
</body>
</html>
