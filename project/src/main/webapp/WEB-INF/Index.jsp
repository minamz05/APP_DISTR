<%@ page language="java"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Formulaire</title>
<link rel="stylesheet" type="text/css" href="Styles.css">
</head>
<body>

<%
String login = "";
 if(session.getAttribute("login")!=null){
     login = session.getAttribute("login").toString();
 }else{
     response.sendRedirect("Auth.jsp");
 }
%>

<h1 class="add-header">Formulaire</h1>
<form class="add-form" method="POST" action=Add>
    <label for="nom">Nom :</label>
    <input type="text" id="Nom" name="Name" required><br>
    
    <label for="nom">Prénom :</label>
    <input type="text" id="Prénom" name="Surname" required><br>
    
    <label for="date">Date : </label>
    <input type="date" id="Date" name="Date" required><br>
    
    <label for="nom">Email :</label>
    <input type="text" id="Email" name="Email" required><br>
     
     <label for="nom">Password :</label>
    <input type="password" id="Password" name="Password" required><br>
    
    <input type="submit" value="Ajouter">
</form>

</body>
</html>
