<%--
  Created by IntelliJ IDEA.
  User: tatja
  Date: 17.04.2018
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CRUD api</title>
</head>
<body>
    <form method="post" action="post_data/account">
        <h3>Adding a new kid account </h3>
        <p>
            <label>KId id: </label>
            <input placeholder="not null" name="kid_id" required>
        </p>
        <p>
            <label>Group id: </label>
            <input placeholder="not null" name="group_id" required>
        </p>
        <p>
            <label>Date of creating: </label>
            <input placeholder="01.01.2000" name="date_of_creating" >
        </p>
        <p>
            <label>Date of leaving: </label>
            <input placeholder="01.01.2020" name="date_of_leaving" >
        </p>
        <button type="submit">Add</button>
    </form>
</body>
</html>
