<%--
  Created by IntelliJ IDEA.
  User: tatja
  Date: 15.04.2018
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CRUD api</title>
</head>
<body>
    <form method="post" action="post_data/parent">
        <h3>Adding a new parent </h3>
        <p>
            <label>Person id: </label>
            <input placeholder="not null" name="id" required>
        </p>
        <button type="submit">Add</button>
    </form>
</body>
</html>
