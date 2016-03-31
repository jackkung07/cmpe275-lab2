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
  <style type="text/css">
    input.left{width:150px;height:3em;margin-left:40px;}
    input.right{width:150px;height:3em;margin-left:60px;}
  </style>
  <script>
    /*
    function Update(){
      alert("");
      var fname = document.getElementsByName("firstname").value；
      ${firstname} = fname;
      var lname = document.getElementsByName("lastname").value；
      ${lastname} = lname;
      var email = document.getElementsByName("email").value；
      ${email} = email;
      var addr = document.getElementsByName("address").value；
      ${address} = addr;
      var org = document.getElementsByName("organization").value；
      ${organization} = org;
      var aboutm = document.getElementsByName("aboutMyself").value；
      ${aboutmyself} = aboutm;aboutm
    }
    */
    function Delete(){
      var form = document.getElementById("deleteProfileForm");
      form.submit();
      //alert("ok");
    }
  </script>
  <h1>Group 7: Profile</h1>

</head>
<body>
<form action="/profile" method="post">
  <fieldset>
    <legend>User information:</legend>
    <table>
      <tr><td>ID: </td><td><input type="text" name="id" READONLY value="${profile.id}"/></td></tr>
      <tr><td>First name: </td><td><input type="text" name="firstname" value="${profile.firstname}"/></td></tr>
      <tr><td>Last name: </td><td><input type="text" name="lastname" value="${profile.lastname}"/></td></tr>
      <tr><td>Email: </td><td><input type="text" name="email"value="${profile.email}" /></td></tr>
      <tr><td>Address: </td><td><input type="text" name="address" value="${profile.address}"/></td></tr>
      <tr><td>Organization: </td><td><input type="text" name="organization" value="${profile.organization}"/></td></tr>
      <tr><td>About myself: </td><td><input type="text" name="aboutMyself" value="${profile.aboutMyself}"/></td></tr>
    </table>
  </fieldset>
  <br>
  <input class="left" type="submit" value="Update" onclick="Update()"/>
  <input class="right" type="button" value="Delete" onclick="Delete()"/>
</form>

<form id="deleteProfileForm" action="/profile/${profile.id}" method= "post">
  <input type="hidden" name ="_method" value ="delete">
</form>



</body>
</html>
