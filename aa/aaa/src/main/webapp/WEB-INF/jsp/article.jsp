<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.boots.entity.Article" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Articles</title>
  <% List<Article> articles = (List<Article>) request.getAttribute("allArticles"); %>
</head>
<body>
<h1> Create Article </h1>
<form:form method="POST" action="/article/create" modelAttribute="articleForm">
        <form:input type="text" path="title" placeholder="Name"></form:input>
        <form:input type="text" path="text" placeholder="Description"></form:input>
        <button type="submit">Создать статью</button>
    </form:form>
    <form:errors path="name"></form:errors>
    ${nameError}

    <table>
        <thead>
        <tr>
             -- <th>Title</th>
              -- <th>Article</th>
              -- <th>Text</th>
             -- <th>Comment</th>
        </tr>
        </thead>
        <tbody>
        <% for (Article article : articles) { %>
        <tr>
            <td><%= article.getTitle() %>
            </td>
            <td>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                       <form action="/article/delete/<%= article.getId() %>" method="POST">
                                                       <button type="submit">Delete</button>
                                                   </form>
                    </sec:authorize>


            </td>
            <td>
            <%= article.getText() %>
            </td>
            <td>
 <sec:authorize access="hasRole('ROLE_ADMIN')">
                 <form method="POST" action="article/update/<%= article.getId() %>">
                                          <div>
                                              <input type="text" name="updatename" placeholder="Updatename">
                                              <button type="submit">Update</button>
                                          </div>
                      </form>
         </sec:authorize>


            </td>
        </tr>
        <% } %>

        </tbody>
    </table>

    <!-- Добавляем кнопку выхода из аккаунта -->
    <form action="/logout" method="post">
        <button type="submit">Logout</button>
    </form>
</body>
</html>