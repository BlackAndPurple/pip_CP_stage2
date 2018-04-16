<%--
  Created by IntelliJ IDEA.
  User: tatja
  Date: 15.04.2018
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CRUD api</title>
</head>
<body>
    <form method="post" action="update_data/person">
        <h3>Updating a person </h3>
        <p>
            <label>ID: </label>
            <input placeholder="not null" name="id" required>
        </p>
        <p>
            <label>Name: </label>
            <input placeholder="not null" name="name" required>
        </p>
        <p>
            <label>Middle name: </label>
            <input name="middle_name">
        </p>
        <p>
            <label>Surname: </label>
            <input placeholder="not null" name="surname" required>
        </p>
        <p>
            <label>Sex: </label>
            <input type="radio" name="sex" value="true" checked>male
            <input type="radio" name="sex" value="false">female
        </p>
        <p>
            <label>Date of birth: </label>
            <input placeholder="01.01.2020" name="date_of_birth">
        </p>
        <button type="submit">Update</button>
    </form>
</body>
</html>
