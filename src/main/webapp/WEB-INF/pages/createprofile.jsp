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
  <title>Group 7: Profile</title>
  <h1>profile</h1>
  <form action="/profile" method="post">
    <fieldset>
      <legend>User information:</legend>
      <table>
        <tr><td>ID: </td><td><input type="text" name="id" value="${id}"/></td></tr>
        <tr><td>First name: </td><td><input type="text" name="firstname" value="${firstname}"/></td></tr>
        <tr><td>Last name: </td><td><input type="text" name="lastname" value="${lastname}"/></td></tr>
        <tr><td>Email: </td><td><input type="text" name="email"value="${email}" /></td></tr>
        <tr><td>Address: </td><td><input type="text" name="address" value="${address}"/></td></tr>
        <tr><td>Organization: </td><td><input type="text" name="organization" value="${organization}"/></td></tr>
        <tr><td>About myself: </td><td><input type="text" name="aboutMyself" value="${aboutMyself}"/></td></tr>
      </table>
    </fieldset>
    <br>
    <input type="submit" value="Create" />
  </form>
</head>
<body>

</body>
</html>
