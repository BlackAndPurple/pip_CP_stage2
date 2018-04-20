<%--
  Created by IntelliJ IDEA.
  User: tatja
  Date: 20.04.2018
  Time: 0:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CRUD api</title>
</head>
<body>
    <form method="post" action="update_data/med">
        <h3>Updating a med record </h3>
        <p>
            <label>MedInfo id: </label>
            <input placeholder="not null" name="med_id" required>
        </p>
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
        <button type="submit">Update</button>
    </form>
</body>
</html>
