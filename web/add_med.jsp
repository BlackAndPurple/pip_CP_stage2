<%--
  Created by IntelliJ IDEA.
  User: tatja
  Date: 19.04.2018
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CRUD api</title>
</head>
<body>
    <form method="post" action="post_data/med">
        <h3>Adding a new med info </h3>
        <p>
            <label>Kid id: </label>
            <input placeholder="not null" name="kid_id" required>
        </p>
        <p>
            <label>Date of creating: </label>
            <input placeholder="01.01.2000" name="date_of_creating" >
        </p>
        <p>
            <label>Height: </label>
            <input placeholder="100" name="height" >
        </p>
        <p>
            <label>Weight: </label>
            <input placeholder="30" name="weight" >
        </p>
        <p>
            <label>Inoculations: </label>
            <input placeholder="" name="inoculations" >
        </p>
        <p>
            <label>Current diseases: </label>
            <input placeholder="" name="diseases" >
        </p>
        <button type="submit">Add</button>
    </form>
</body>
</html>
