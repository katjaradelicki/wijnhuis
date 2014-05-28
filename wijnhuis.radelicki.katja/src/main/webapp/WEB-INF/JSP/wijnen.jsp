<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="vdab" uri="http://vdab.be/tags"%>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}" />
<!doctype html>
<html lang="nl">
<head>
<vdab:menu />
<title>Het wijnhuis</title>
<link rel="stylesheet" href="${contextPath}/styles/default.css" />
</head>
<body>
	<header>
		<h1>Het wijnhuis</h1>
		<h2>Kies een land</h2>
	</header>
	<c:forEach items="${landen}" var="land">
		<c:url var="wijnenUrl" value="wijnen.htm">
			<c:param name="landNr" value="${land.landNr}"></c:param>
		</c:url>

		<a href="${wijnenUrl}"><img alt="${land.naam}"
			src="${contextPath}/images/${land.landNr}.png"></a>
	</c:forEach>



	<c:if test="${not empty gekozenLand}">
		<h2>Kies een soort uit ${gekozenLand.naam}</h2>
		<ul>
			<c:forEach items="${gekozenLand.soorten}" var="soort">
				<c:url var="soortenUrl" value="wijnen.htm">
					<c:param name="landNr" value="${gekozenLand.landNr}"></c:param>
					<c:param name="soortNr" value="${soort.soortNr}" />
				</c:url>
				<li><a href="${soortenUrl}">${soort.naam}</a></li>
			</c:forEach>
		</ul>
	</c:if>
	<c:if test="${not empty gekozenSoort}">
		<h2>Kies een wijn uit ${gekozenSoort.naam}</h2>
		<ul>
			<c:forEach items="${gekozenSoort.wijnen}" var="wijn">
				<c:url var="wijnDetailUrl" value="detail.htm">
					<c:param name="landNr" value="${gekozenLand.landNr}"></c:param>
					<c:param name="soortNr" value="${gekozenSoort.soortNr}" />
					<c:param name="wijnNr" value="${wijn.wijnNr}"></c:param>
				</c:url>
				<li><a href="${wijnDetailUrl}">${wijn.jaar}</a> <c:forEach
						begin="1" end="${wijn.beoordeling}">
						<div class=ster>&#9733</div>
					</c:forEach></li>
			</c:forEach>
		</ul>
	</c:if>

</body>
</html>