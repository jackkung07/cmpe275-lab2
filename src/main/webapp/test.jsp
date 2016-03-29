<%--
  Created by IntelliJ IDEA.
  User: archer
  Date: 3/28/16
  Time: 5:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Group of 3: Yuebiao Ma, Che-Yi Kung, Minglu Liu</title>
  <script>
    function getUserInfo(){
      document.getElementById("id").value = ；
      document.getElementById("fname").value = ；
      document.getElementById("lname").value = ；
      document.getElementById("email").value = ；
      document.getElementById("addr").value = ；
      document.getElementById("org").value = ；
      document.getElementById("about").value = ；
    }
  </script>
</head>
<body onload="getUserInfo()">
<form name="form1" action="" method="POST">
  <fieldset>
    <legend>User information:</legend>
    <table>
      <tr><td>User ID:</td><td><input type="text" id="id" READONLY></td></tr>
      <tr><td>First name:</td><td><input type="text" id="fname"></td></tr>
      <tr><td>Last name:</td><td><input type="text" id="lname"></td></tr>
      <tr><td>Email:</td><td><input type="text" id="email"></td></tr>
      <tr><td>Address:</td><td><input type="text" id="addr"></td></tr>
      <tr><td>Organization:</td><td><input type="text" id="org"></td></tr>
      <tr><td>About myself:</td><td><input type="text" id="about"></td></tr>
    </table>
  </fieldset>
      <input class="left" type="submit" value="Update"/>
      <input class="right" type="submit" value="Delete"/>
</form>



</body>
</html>
