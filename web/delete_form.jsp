<%--
  Created by IntelliJ IDEA.
  User: tatja
  Date: 13.04.2018
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CRUD api</title>
</head>
<body>
    <form method="post" action="../delete_data/people">
        <h3>Enter Id of record you want to delete </h3>
        <p>
            <label>ID: </label>
            <input placeholder="not null" name="id" required>
        </p>
        <button type="submit">Delete</button>
    </form>
</body>
</html>
