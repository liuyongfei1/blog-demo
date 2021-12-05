<%--
  Created by IntelliJ IDEA.
  User: lyf
  Date: 2021/12/5
  Time: 下午12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>文件上传</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/fileupload"
      method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="myfile"><br>
    文件描述：<input type="text" name="description"><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
