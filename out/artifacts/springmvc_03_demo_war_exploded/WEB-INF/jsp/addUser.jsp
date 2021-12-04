<%--
  Created by IntelliJ IDEA.
  User: lyf
  Date: 2021/12/4
  Time: 上午7:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>添加用户</title>
</head>
<body>
    <form action="${pageContext.request.contextPath }/user/validate" method="post">
        用户id：<input type="text" name="id" />
        <br>
        用户名：<input type="text" name="name" />
        <br>
        邮箱：<input type="text" name="email" />
        <br>
        <input type="submit" value="提交">
    </form>
</body>
</html>
