<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="root" value="${pageContext.request.contextPath}/" />     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>not_loggedin</title>
</head>
<body>
  <script>
    alert("로그인 후 이용해 주세요");
    location.href="${root}user/login";
  </script>
</body>
</html>