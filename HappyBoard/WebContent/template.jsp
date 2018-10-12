<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/template.css?ver=5.1">
</head>
<body>
<header>
	<jsp:include page="header.jsp"/>
</header>
<nav>
	<jsp:include page="nav.jsp"/>
</nav>
<section>
	<jsp:include page="${content }.jsp"/>
</section>
<footer>
	<jsp:include page="footer.jsp"/>
</footer>
</body>
</html>