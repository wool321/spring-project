<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="root" value="${pageContext.request.contextPath}/" /> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modify_success</title>
</head>
<body>
  <script>
    alert("회원정보가 수정되었습니다");
    location.href="${root}user/modify";
  </script>
</body>
</html>