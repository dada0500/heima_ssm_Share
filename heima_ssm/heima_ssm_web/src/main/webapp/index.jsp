<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:forward page="/pages/main.jsp" ></jsp:forward>
<a href="${pageContext.request.contextPath}/product/findAll">查询所有产品</a>


</body>
</html>
