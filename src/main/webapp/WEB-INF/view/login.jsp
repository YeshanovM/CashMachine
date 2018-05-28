<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Login</title>
   </head>
   <body>

      <h3>Login</h3>
      <form method="POST" action="/login">
         <table border="0">
            <tr>
               <td>User ID</td>
               <td><input type="text" name="uid" value="${ uid }" /> </td>
            </tr>
            <tr>
               <td>Password</td>
               <td><input type="password" name="password"/> </td>
            </tr>
            <tr>
               <td colspan="2">
                  <input type="submit" value="Login" />
               </td>
            </tr>
         </table>
      </form>

      <p style="color: red;">${ error }</p>
   </body>
</html>