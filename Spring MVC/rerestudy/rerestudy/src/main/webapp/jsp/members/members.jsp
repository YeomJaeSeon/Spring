<%@ page import="java.util.List" %>
<%@ page import="mvc.rerestudy.domain.member.MemberRepository" %>
<%@ page import="mvc.rerestudy.domain.member.Member" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(((HttpServletRequest) request).getSession().getServletContext());
    MemberRepository memberRepository = (MemberRepository) wac.getBean("memberRepository");
    List<Member> members = memberRepository.findAll();
%>
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
    <tbody> <%
        for (Member member : members) {
            out.write("    <tr>");
            out.write("   <td>" + member.getId() + "</td>");
            out.write("   <td>" + member.getUsername() + "</td>");
            out.write("   <td>" + member.getAge() + "</td>");
            out.write("   </tr>");
        } %>
    </tbody>
</table>
</body>
</html>
