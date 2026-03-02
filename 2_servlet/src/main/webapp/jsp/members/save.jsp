<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
  Created by IntelliJ IDEA.
  User: mince
  Date: 26. 2. 21.
  Time: 오후 3:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();

    System.out.println("MemberSaveServlet.service");
    // post get 상관 없이 꺼낼 수 있습니다.
    String username = request.getParameter("username");
    // req.getPrameter String으로 가져오기 때문에 형변환 필요합니다.
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    memberRepository.save(member);
%>

<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id = <%=member.getId()%></li>
    <li>username = <%=member.getName() %></li>
    <li>age = <%=member.getAge()%></li>
</ul>
<a href = "/index.html">메인</a>
</body>
</html>
