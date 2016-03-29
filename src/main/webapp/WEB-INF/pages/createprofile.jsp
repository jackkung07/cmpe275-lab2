<%--
  Created by IntelliJ IDEA.
  User: cheyikung
  Date: 3/28/16
  Time: 6:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <h1>profile</h1>
  <form action="/profile" method="post">
    ID: <input type="text" name="id" value="${id}"/><br/>
    First name: <input type="text" name="firstname" value="${firstname}"/><br>
    Last name: <input type="text" name="lastname" value="${lastname}"/><br>
    Email: <input type="text" name="email" value="${email}"/><br>
    Address: <input type="text" name="address" value="${address}"/><br>
    Organization: <input type="text" name="organization" value="${organization}"/><br>
    About myself: <input type="text" name="aboutmyself" value="${aboutmyself}"/><br>
    <input type="submit" value="Submit" />
  </form>
</head>
<body>

</body>
</html>
