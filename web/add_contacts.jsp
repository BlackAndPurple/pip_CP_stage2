<%--
  Created by IntelliJ IDEA.
  User: tatja
  Date: 16.04.2018
  Time: 2:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CRUD api</title>
</head>
<body>
    <form method="post" action="post_data/contacts">
        <h3>Adding new parent contacts </h3>
        <p>
            <label>parent ID: </label>
            <input placeholder="not null" name="parent_id" required>
        </p>
        <p>
            <label>Home address: </label>
            <input name="address">
        </p>
        <p>
            <label>Job: </label>
            <input name="job" >
        </p>
        <p>
            <label>Job phone number: </label>
            <input name="job_phone_number" >
        </p>
        <p>
            <label>Cell phone number: </label>
            <input name="cell_phone_number" >
        </p>
        <button type="submit">Add</button>
    </form>
</body>
</html>
