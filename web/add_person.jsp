<%--
  Created by IntelliJ IDEA.
  User: tatja
  Date: 12.01.2018
  Time: 3:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add person</title>
</head>
<body>
    <form method="post" action="post_data/person">
        <h3>Adding a new person </h3>
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
        <button type="submit">Add</button>
    </form>
</body>
</html>
