<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hello.servlet.domain.member.Member" %><%--
  Created by IntelliJ IDEA.
  User: mince
  Date: 26. 2. 21.
  Time: 오후 5:20
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
<%--    <li>id = <%=((Member)request.getAttribute("member")).getId()%></li>--%>
<%--    <li>username = <%=((Member)request.getAttribute("member")).getName() %></li>--%>
<%--    <li>age = <%=((Member)request.getAttribute("member")).getAge()%></li>--%>
    <li>id =${member.id}</li>
    <li>username =${member.name}</li>
    <li>age = ${member.age}</li>
</ul>
<a href = "/index.html">메인</a>
</body>
</html>
