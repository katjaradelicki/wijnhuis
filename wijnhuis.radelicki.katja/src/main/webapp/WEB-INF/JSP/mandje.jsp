<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="vdab" uri="http://vdab.be/tags"%>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}" />
<!doctype html>
<html lang="nl">
<head>
	<vdab:menu />
	<title>Winkelmandje</title>
	<link rel="stylesheet" href="${contextPath}/styles/default.css" />
</head>
<body>
	<header>
		<h1>Mandje</h1>

	</header>
	<c:if test="${empty mandje}">Mandje is leeg</c:if>
	<c:if test="${not empty mandje}">
		<table>
			<thead>
				<tr>
					<th>Wijn</th>
					<th>Prijs</th>
					<th>Aantal</th>
					<th>Te betalen</th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<td />
					<td />
					<td>Totaal:</td>
					<td>${totaal}</td>
				</tr>
			</tfoot>
			<tbody>


				<c:forEach items="${mandje}" var="bestelbonlijn">
					<%-- door LAZY uit te zetten, krijg je hier geen fout meer --%>

					<tr>
						<td>${bestelbonlijn.wijn.soort.land.naam}
							${bestelbonlijn.wijn.soort.naam} ${bestelbonlijn.wijn.jaar}</td>
						<td>${bestelbonlijn.wijn.prijs}</td>
						<td align="right">${bestelbonlijn.aantal}</td>
						<td align="right">${bestelbonlijn.getKost()}</td>
					</tr>
				</c:forEach>
			</tbody>

		</table>

		<c:url var="mandjeUrl" value="mandje.htm" />
		<form action="${mandjeUrl}" method="post">
			<dl>
				<dd>Naam</dd>
				<dt>
					<input type="text" autofocus="autofocus" name="naam"
						value="${param.naam}" class="lijn" />
					<div class="fout">${foutNaam}</div>
				</dt>
				<dd>Straat</dd>
				<dt>
					<input type="text" name="straat" value="${param.straat}"
						class="lijn" />
					<div class="fout">${foutStraat}</div>
				</dt>
				<dd>Huisnummer</dd>
				<dt>
					<input type="text" name="huisnr" value="${param.huisnr}"
						class="lijn" />
					<div class="fout">${foutHuisnr}</div>
				</dt>
				<dd>Postcode</dd>
				<dt>
					<input type="text" name="postcode" value="${param.postcode}"
						class="lijn" />
					<div class="fout">${foutPostcode}</div>
				</dt>
				<dd>Gemeente</dd>
				<dt>
					<input type="text" name="gemeente" value="${param.gemeente}"
						class="lijn" />
					<div class="fout">${foutGemeente}</div>
				</dt>
			</dl>
			<label><input type="radio" name="bezorging" value="afhalen"
				${param.bezorging=="afhalen"? 'checked' : '' } />Afhalen</label> <label><input
				type="radio" name="bezorging" value="opsturen"
				${param.bezorging=="opsturen"? 'checked' : '' } />Opsturen</label> <span
				class="fout">${foutBezorging}</span> <input type="submit"
				value="Als bestelbon bevestigen">

		</form>
		<%-- <c:import url="/WEB-INF/JSP/fouten.jsp"/> --%>
	</c:if>


</body>
</html>