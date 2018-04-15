<%--
  Created by IntelliJ IDEA.
  User: tatja
  Date: 16.04.2018
  Time: 1:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CRUD api</title>
</head>
<body>
    <form method="post" action="update_data/parent">
        <h3>Updating a person </h3>
        <p>
            <label>parent ID: </label>
            <input placeholder="not null" name="parent_id" required>
        </p>
        <p>
            <label>person ID: </label>
            <input placeholder="" name="person_id" required>
        </p>
        <button type="submit">Add</button>
    </form>
</body>
</html>
