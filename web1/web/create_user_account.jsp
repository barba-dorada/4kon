<%@ page import="ru.cwl.kon.dao.jdbctemplate.UserDao" %>
<%@ page import="ru.cwl.kon.model.User" %>
<%@ page import="org.springframework.jdbc.datasource.SimpleDriverDataSource" %>
<%@ page import="org.springframework.jdbc.core.JdbcTemplate" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 07.02.2016
  Time: 7:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>create user account</title>
</head>
<body>
<%=request.getMethod()%><br/>
<form action="create_user_account.jsp" method="post">
    логин:<input name="login" type="text">
    пароль:<input name="password" type="password">
    <input type="submit">

    <table>
        <%
            SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
            dataSource.setDriverClass(org.h2.Driver.class);
            dataSource.setUsername("sa");
            dataSource.setUrl("jdbc:h2:~/4kon");
            dataSource.setPassword("");

            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            UserDao dao = new UserDao(jdbcTemplate);

            if ("POST".equalsIgnoreCase(request.getMethod())) {
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                User newUser = new User(login, password);
                newUser = dao.create(newUser);
                System.out.println(newUser.getId());
            }

            for (User user : dao.getAll()) {
        %>

        <tr>
            <td><%=user.getId()%>
            </td>
            <td><%=user.getLogin()%>
            </td>
            <td><%=user.getPassword()%>
            </td>
        </tr>
        <%
            }
        %>
    </table>

</form>

</body>
</html>
