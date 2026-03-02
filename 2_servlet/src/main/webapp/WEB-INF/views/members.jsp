<%--
  Created by IntelliJ IDEA.
  User: mince
  Date: 26. 2. 21.
  Time: 오후 9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 템플릿에서 간단하게 반복문을 쓰기위해서 선언해야함--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <meta charset="UTF-8">
  <title>Title</title>
</head>
<body>
<a href="/index.html">메인</a>
<table>
  <thead>

  <th>id</th>
  <th>username</th>
  <th>age</th>
  </thead>
  <tbody>
  <c:forEach var = "item" items="${members}">
    <tr>
      <td>${item.id}</td>
      <td>${item.name}</td>
      <td>${item.age}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>