<%--
  Created by IntelliJ IDEA.
  User: tatja
  Date: 20.04.2018
  Time: 23:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CRUD api</title>
</head>
<body>
    <form method="post" action="update_data/sg">
        <h3>Updating staff </h3>
        <p>
            <label>Id: </label>
            <input placeholder="not null" name="id" required>
        </p>
        <p>
            <label>Staff id: </label>
            <input placeholder="not null" name="staff_id" required>
        </p>
        <p>
            <label>Group id: </label>
            <input placeholder="not null" name="group_id" required>
        </p>
        <p>
            <label>Date of beginning: </label>
            <input placeholder="01.01.2000" name="since" >
        </p>
        <p>
            <label>Date of end: </label>
            <input placeholder="01.01.2020" name="until" >
        </p>
        <button type="submit">Update</button>
    </form>
</body>
</html>
