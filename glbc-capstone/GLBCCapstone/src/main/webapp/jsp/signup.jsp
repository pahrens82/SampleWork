<%-- 
    Document   : signup
    Created on : Dec 1, 2016, 4:06:55 PM
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
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sign Up</title>
        <link href="${pageContext.request.contextPath}/css/GLBCStyle.css" rel="stylesheet" type="text/css">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css?family=Trirong" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/img/icon-beer.png" rel="shortcut icon"/>
        <link href="https://fonts.googleapis.com/css?family=Slabo+27px" rel="stylesheet">
    </head>
    <body>
        <!--        <div class="col-md-offset-8 control-label label"><h1>Sign Up</h1></div>-->
        <sf:form class="form-horizontal" method="POST" action="createUser" modelAttribute="user">
            <div class="form-group">
                <h3><label for="signup-username" class="col-md-4 col-md-offset-3 control-label label">User Name:</label></h3>
                <div class="col-md-4">
                    <sf:input id="signup-username" 
                              name="userName" 
                              placeholder="User Name"
                              path="userName"
                              class="form-control"/>
                </div>
                <div class="col-md-4 col-md-offset-7">
                    <sf:errors path="userName" cssClass="error">
                    </sf:errors>
                </div>
            </div>
            <div class="form-group">
                <h3><label for="signup-password" class="col-md-4 col-md-offset-3 control-label label">Password:</label></h3>
                <div class="col-md-4">
                    <sf:password id="signup-password" 
                                 name="password" 
                                 placeholder="Password"
                                 path="password"
                                 class="form-control"/>
                </div>
                <div class="col-md-4 col-md-offset-7">
                    <sf:errors path="password" cssClass="error">
                    </sf:errors>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-offset-8 col-sm-4">
                    <sf:checkbox id = "signup-enabled"
                                 name="enabled"
                                 path="enabled"
                                 required="true"/>
                    <label for="signup-enabled" class="label">
                        I am at least 21 years old.
                    </label>
                    <sf:errors path="enabled" cssClass="error">
                    </sf:errors>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-offset-8 col-sm-4">
                    <input class="btn btn-default" type="submit"/>
                    <a class="btn btn-default" href="${pageContext.request.contextPath}/login">Cancel</a>
                </div>
            </div>
        </sf:form>
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
