<%--
  Created by IntelliJ IDEA.
  User: lyf
  Date: 2021/12/5
  Time: 下午4:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="fm" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>多文件上传</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/multifileUpload"
      method="post" enctype="multipart/form-data">
    选择文件1：<input type="file" name="myfile"><br>
    文件描述1：<input type="text" name="description"><br>
    选择文件2：<input type="file" name="myfile"><br>
    文件描述2：<input type="text" name="description"><br>
    选择文件3：<input type="file" name="myfile"><br>
    文件描述3：<input type="text" name="description"><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
