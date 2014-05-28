<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="vdab" uri="http://vdab.be/tags"%>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}" />
<!doctype html>
<html lang="nl">
<head>
<vdab:menu />
<title>Wijn toevoegen</title>
<link rel="stylesheet" href="${contextPath}/styles/default.css" />
</head>
<body>
	<header>
		<h1>Wijn toevoegen aan mandje</h1>

	</header>
	Land
	<span class="antwoord">${gekozenWijn.soort.land.naam}</span> Soort
	<span class="antwoord">${gekozenWijn.soort.naam}</span> Jaar
	<span class="antwoord">${gekozenWijn.jaar}</span> Beoordeling
	<span class="antwoord"><c:forEach begin="1"
			end="${gekozenWijn.beoordeling}">&#9733</c:forEach></span> Prijs
	<span class="antwoord">${gekozenWijn.prijs}</span>

	<c:url var="detailUrl" value="detail.htm">
		<c:param name="wijnNr" value="${gekozenWijn.wijnNr}"></c:param>
	</c:url>
	<form action="${detailUrl}" method="post">
		Aantal flessen
		<input autofocus="autofocus" name="aantalFlessen" value="${param.aantalFlessen}" type="number" min="1" max="1000"/>
		<c:import url="fouten.jsp"></c:import>
		<input type="submit" value="Toevoegen" />
	</form>

</body>
</html>