<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Новости</title>
</head>
<body>
<div>
    <h2>Новости <br> Только для залогинившихся пользователей.</h2>
    <a href="/">Главная</a>
</div>
<form:form method="POST" action="/article/create" modelAttribute="articleForm">
<form:input type="text" path="title"/>
<input type="hidden" name="user" value="${userId}"/>
<button type="submit">Создать статью</button>
</form:form>
</body>
</html>