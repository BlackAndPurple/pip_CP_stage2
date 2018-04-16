<%--
  Created by IntelliJ IDEA.
  User: tatja
  Date: 16.04.2018
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CRUD api</title>
</head>
<body>
    <form method="post" action="update_data/contacts">
        <h3>Updating parent contacts </h3>
        <p>
            <label>contacts ID: </label>
            <input placeholder="not null" name="contacts_id" required>
        </p>
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
        <button type="submit">Update</button>
    </form>
</body>
</html>
