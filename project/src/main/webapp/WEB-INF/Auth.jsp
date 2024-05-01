<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="Styles.css">
</head>
<h1 class="login-header">Authentification</h1>
<body>
    <form class="login-form" method="POST" action="Login">
        <table>
            <tr>
                <td>User name</td>
                <td><input type="text" name="login" required></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="pwd" required></td>
            </tr>
          <tr>
        <td colspan="2" class="create-account-cell">
            <a href="Index.jsp">Créer un nouveau compte</a>
        </td>
    </tr>
            
            <tr>
                <td colspan="2"><input type="submit" value="Se Connecter"></td>
            </tr>
             
        </table>
    </form>
</body>
</html>
