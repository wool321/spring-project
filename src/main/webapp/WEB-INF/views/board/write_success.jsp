<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<c:set var="root" value="${pageContext.request.contextPath}/" /> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>success</title>
</head>
<body>
  <script>
    alert("게시글이 등록되었습니다");
    location.href="${root}board/read?board_info_idx=${writeContentDTO.content_board_idx}&content_idx=${writeContentDTO.content_idx}&page=1";
  </script>
</body>
</html>


