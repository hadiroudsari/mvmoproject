<%--
  Created by IntelliJ IDEA.
  User: hvr
  Date: 3/3/21
  Time: 5:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%
 String name = (String) session.getAttribute("name");
 out.print("<p> welcome "+name);
%>
</body>
</html>
