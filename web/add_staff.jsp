<%--
  Created by IntelliJ IDEA.
  User: tatja
  Date: 20.04.2018
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CRUD api</title>
</head>
<body>
    <form method="post" action="post_data/staff">
        <h3>Adding staff </h3>
        <p>
            <label>Person id: </label>
            <input placeholder="not null" name="person_id" required>
        </p>
        <p>
            <label>Function: </label>
            <input type="radio" name="function" value="Morning Kindergartner" checked>Morning Kindergartner
            <input type="radio" name="function" value="Evening Kindergartner">Evening Kindergartner
            <input type="radio" name="function" value="Nanny">Nanny
        </p>
        <p>
            <label>Experience: </label>
            <input placeholder="" name="experience" >
        </p>
        <button type="submit">Add</button>
    </form>
</body>
</html>
