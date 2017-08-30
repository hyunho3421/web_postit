<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<form action="/logout" method="post" id="logout">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="/postit/">POST-IT</a>
		</div>
		<ul class="nav navbar-nav">
			<li <c:out value="${root eq 'postit' ? 'class=active' : ''}" />><a href="/postit/">포스트잇</a></li>
			<%--<li><a href="#">Page 2</a></li>--%>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<sec:authorize access="isAuthenticated()">
				<sec:authentication property="principal.username" var="loginId"></sec:authentication>
				<li><a href="#" onclick="return false;" style="cursor: default;">${loginId}</a></li>
				<li><a href="javascript:logout()"><span class="glyphicon glyphicon-log-out"></span></a></li>
			</sec:authorize>
		</ul>
	</div>
</nav>