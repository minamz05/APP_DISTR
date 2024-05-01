<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Candidats</title>
</head>
<body>

<br>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">List of Users</h3>
        <hr>
        <div class="container text-left">
            <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New User</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Position</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="candidat" items="${listCandidat}">
                <tr>
                    <td><c:out value="${candidat.IdCandidat}" /></td>
                    <td><c:out value="${candidat.Name}" /></td>
                    <td><c:out value="${candidat.Surname}" /></td>
                    <td><c:out value="${candidat.Position}" /></td>
                    <td>
                        <a href="edit?IdCandidat=<c:out value='${candidat.IdCandidat}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="delete?IdCandidat=<c:out value='${candidat.IdCandidat}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
