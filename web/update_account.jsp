<%--
  Created by IntelliJ IDEA.
  User: tatja
  Date: 19.04.2018
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CRUD api</title>
</head>
<body>
    <form method="post" action="update_data/account">
        <h3>Updating a kid account </h3>
        <p>
            <label>Account id: </label>
            <input placeholder="not null" name="account_id" required>
        </p>
        <p>
            <label>Kid id: </label>
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
        <button type="submit">Update</button>
    </form>
</body>
</html>
