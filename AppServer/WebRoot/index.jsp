<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<html>
	<head>
    </head>
  
  <body>
    <form action="doLoginServlet" method="post">
    <table>
    <caption>�û���¼</caption>
    <tr><td>��¼��:</td>
    <td><input type="text" name="username"></td>
    </tr>
    <tr>
    <td>���룺</td>
    <td><input type="password" name="password"/></td>
    </tr>
    </table>
    <input type="submit" value="��½">
    <input type="reset" value="����"/>
    </form>
  </body>
</html>
