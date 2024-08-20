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
    alert("게시글이 수정되었습니다");
    location.href="${root}board/read?board_info_idx=${modifyContentDTO.content_board_idx}&content_idx=${modifyContentDTO.content_idx}&page=${page}";
  </script>
</body>
</html>