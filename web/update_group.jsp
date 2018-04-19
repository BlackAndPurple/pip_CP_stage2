<%--
  Created by IntelliJ IDEA.
  User: tatja
  Date: 19.04.2018
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CRUD api</title>
</head>
<body>
    <form method="post" action="update_data/group">
        <h3>Updating a group </h3>
        <p>
            <label>Group id: </label>
            <input placeholder="not null" name="group_id" required>
        </p>
        <p>
            <label>Group name: </label>
            <input placeholder="" name="group_name" >
        </p>
        <p>
            <label>Group type: </label>
            <input placeholder="" name="group_type" >
        </p>
        <button type="submit">Update</button>
    </form>
</body>
</html>
