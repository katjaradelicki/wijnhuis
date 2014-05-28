<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="vdab" uri="http://vdab.be/tags"%>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}" />
<!doctype html>
<html lang="nl">
<head>
<vdab:menu />
<title>Bevestiging</title>
<link rel="stylesheet" href="${contextPath}/styles/default.css" />
</head>
<body>
	<header>
		<h1>Je mandje is bevestigd als bestelbon ${param.bestelbonNr}</h1>

	</header>


</body>
</html>