<%-- 
    Document   : manageUsers
    Created on : Dec 3, 2016, 8:33:15 PM
    Author     : apprentice
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Users</title>
        <!--Imports -->
        <%@include file="commonAdminLinksFragment.jsp" %>
        <link href="https://fonts.googleapis.com/css?family=Trirong" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Slabo+27px" rel="stylesheet">

        <!-- /Imports -->

        <style>
            .pad20 { 
                margin-top: 20px; 
            }
        </style>
    </head>

    <body>
        <%@include file="../adminTopNavBarFragment.jsp" %>
        <div class="container">
            <div class="panel panel-default panel-body">
                <h1 class="text-center">Manage User Accounts</h1>
                <a class="btn btn-success" href="${pageContext.request.contextPath}/admin/user/displayUserForm"><span class="glyphicon glyphicon-plus" aria-hidden='true'></span><span class="glyphicon glyphicon-user" aria-hidden='true'></span> User Account</a>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>User Name</th>
                            <th>Name</th>
                            <th>Access Permissions</th>
                            <th>Type</th>
                            <th>Login Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${userList}">
                            <s:url value="changeUserStatus" var="enableUser_url">
                                <s:param name="userName" value="${user.userName}"/>
                            </s:url>
                            <s:url value="changeUserRole" var="updateUser_url">
                                <s:param name="userName" value="${user.userName}"/>
                            </s:url>
                            <tr>
                                <td name="userName">${user.userName}</td>
                                <td>${user.firstName} ${user.lastName}</td>
                                <td>
                                    <c:forEach var="permission" items="${user.permissions}">
                                        <c:out value="${permission.description}"></c:out>
                                    </c:forEach>
                                </td>
                                <td>
                                    <c:if test="${user.permissionId==1}">
                                        <a class="btn btn-warning" href="#">Admin</a>
                                    </c:if>
                                    <c:if test="${user.permissionId==2}">
                                        <form role="form" action="${updateUser_url}" method="POST">
                                            <input id="newPermissionId" type="hidden" name="newPermissionId" value="3"/>
                                            <input class="btn btn-info" type="submit" value="Super"></input>
                                        </form>
                                    </c:if>
                                    <c:if test="${user.permissionId==3}">
                                        <form role="form" action="${updateUser_url}" method="POST">
                                            <input type="hidden" name="newPermissionId" value="2"/>
                                            <input class="btn btn-primary" type="submit" value="User"></input>
                                        </form>
                                    </c:if>
                                    <c:if test="${user.permissionId==4}">
                                        <a class="btn btn-default" href="#">Guest</a>
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${user.userName!='admin'}">
                                        <c:if test="${user.enabled==true}">
                                            <form role="form" action="${enableUser_url}" method="POST">
                                                <input class="btn btn-success" type="submit" value="Enabled"/>
                                            </form>
                                        </c:if>
                                        <c:if test="${user.enabled==false}">
                                            <form role="form" action="${enableUser_url}" method="POST">
                                                <input class="btn btn-danger" type="submit" value="Disabled"/>
                                            </form>
                                        </c:if>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        
		<%@include file="commonAdminJSFragment.jsp" %>
		
    </body>
</html>