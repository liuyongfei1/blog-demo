<%--
  Created by IntelliJ IDEA.
  User: lyf
  Date: 2021/12/5
  Time: 下午5:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<table>
    <tr>
        <td>被下载的文件名</td>
    </tr>
    <!--遍历 model中的 files-->
    <c:forEach items="${files}" var="filename">
        <tr>
            <td>
                <a href="${pageContext.request.contextPath }/down?filename=${filename}">${filename}</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
