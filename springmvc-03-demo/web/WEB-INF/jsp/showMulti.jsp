<%--
  Created by IntelliJ IDEA.
  User: lyf
  Date: 2021/12/5
  Time: 下午4:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>多文件上传显示</title>
</head>
<body>
<table border="1px">
    <tr>
        <td>详情</td>
        <td>文件名</td>
    </tr>
    <!-- 同时取两个数组的元素 -->
    <c:forEach items="${multiFileDomain.description}" var="description"
               varStatus="loop">
        <tr>
            <td>${description}</td>
            <td>${multiFileDomain.myfile[loop.count-1].originalFilename}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
