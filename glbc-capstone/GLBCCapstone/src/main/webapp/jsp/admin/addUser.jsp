<%-- 
    Document   : addUser
    Created on : Dec 5, 2016, 12:29:04 PM
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
        <title>Add User</title>
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
                <h1 class="text-center">Add New User Account</h1>

                <sf:form class="form-horizontal" role="form" action="addUser" modelAttribute="user" method="POST">
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="admin-add-username">
                            Username:
                        </label>
                        <div class="col-md-8">
                            <sf:input path="userName" class="form-control" id="admin-add-username" name="userName" placeholder="username"/>
                            <sf:errors path="userName" cssClass="bg-danger"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="admin-add-password">
                            Password:
                        </label>
                        <div class="col-md-8">
                            <sf:input path="password" class="form-control" id="admin-add-password" name="password" placeholder="password"/>
                            <sf:errors path="password" cssClass="bg-danger"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="admin-add-first-name">
                            First Name:
                        </label>
                        <div class="col-md-8">
                            <sf:input path="firstName" class="form-control" id="admin-add-first-name" name="firstName" placeholder="First Name"/>
                            <sf:errors path="" cssClass="bg-danger"/>
                        </div>
                    </div>
                    <div class="form-group" >
                        <label class="col-md-4 control-label" for="admin-add-last-name">
                            Last Name:
                        </label>
                        <div class="col-md-8">
                            <sf:input path="lastName" class="form-control" id="admin-add-last-name" name="lastName" placeholder="Last Name"/>
                            <sf:errors path="" cssClass="bg-danger"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="admin-add-email">
                            Email:
                        </label>
                        <div class="col-md-8" for="admin-add-email">
                            <sf:input path="email" class="form-control" id="admin-add-email" name="email" placeholder="Example@Beer.com"/>
                            <sf:errors path="email" cssClass="bg-danger"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="admin-add-permissionId">
                            Account type:
                        </label>
                        <div class="col-md-8">
                            <sf:radiobutton path="permissionId" id="admin-add-permissionId-2" name="permissionId" value="2" label="Super"/>
                            <sf:radiobutton path="permissionId" id="admin-add-permissionId-3" name="permissionId" value="3" label="User" required="true" />
                            <sf:errors path="permissionId" cssClass="bg-danger"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="admin-add-enabled">
                            Enable access:
                        </label>
                        <div class="col-md-8">
                            <sf:radiobutton path="enabled" id="admin-add-enabled" name="enabled" value="true" label="Yes"/>
                            <sf:radiobutton path="enabled" id="admin-add-disabled" name="enabled" value="false" label="No"/>
                            <sf:errors path="enabled" cssClass="bg-danger"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4">
                            <input type="submit" id="admin-add-user-button" class="btn btn-success" value="Add User"/>
                            <a href="${pageContext.request.contextPath}/admin/user/manageUsers" class="btn btn-danger">Cancel</a>
                        </div>
                    </div>
                </sf:form>
            </div>
        </div>
        
		<%@include file="commonAdminJSFragment.jsp" %>
		
    </body>
</html>