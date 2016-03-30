<%--
  Created by IntelliJ IDEA.
  User: archer
  Date: 3/28/16
  Time: 6:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Group of 3: Yuebiao Ma, Che-Yi Kung, Minglu Liu</title>
  <script>
    function getPlainText(){
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
<body onload="getPlainText()">
<pre>
  UserID: <p id="id"></p>
  First name: <p id="fname"></p>
  Last name: <p id="lname"></p>
  Email:<p id="email"></p>
  Address:<p id="addr"></p>
  Organization:<p id="org"></p>
  About myself:<p id="about"></p>
</pre>

</body>
</html>
