<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/common/head.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/common/body.jsp"></jsp:include>
<c:if test="${sessionScope.user != null}">
	${user.uiId} 님 환영합니다.
</c:if>
</body>
</html>