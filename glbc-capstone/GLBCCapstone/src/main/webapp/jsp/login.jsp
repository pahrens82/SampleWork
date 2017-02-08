<%-- 
    Document   : login
    Created on : Nov 17, 2016, 3:59:42 PM
    Author     : apprentice
--%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link href="${pageContext.request.contextPath}/css/GLBCStyle.css" rel="stylesheet" type="text/css" ">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/img/icon-beer.png" rel="shortcut icon"/>
        <link href="https://fonts.googleapis.com/css?family=Trirong" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Slabo+27px" rel="stylesheet">
    </head>
    <body>
        <div id="formContainer" class="container-fluid">
            <div class="form-group row">
                <!-- #1 - If login_error == 1 then there was a failed login attempt -->
                <!-- so display an error message -->
                <div class="col-sm-offset-4 col-sm-8" id="user-login-form">
                    <c:if test="${param.login_error == 1}">
                        <h3 class="col-sm-offset-6 col-sm-4">Wrong username or password!</h3>
                    </c:if>
                    <c:url var="loginUrl" value="/j_spring_security_check"/>  
                    <form class="form-horizontal" method="post" action="${loginUrl}">
                        <div class="form-group">
                            <label for="username" class="col-sm-4 control-label">Username:</label>
                            <div class="col-sm-8">
                                <input class="form-control" id="username" type="text" name="username" placeholder="username"/><br>
                            </div>
                        </div>                        
                        <div class="form-group">
                            <label for="password" class="col-sm-4 control-label">Password:</label>
                            <div class="col-sm-8">
                                <input class='form-control' id="password" type="password" name="password" placeholder="password"/><br>    
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-offset-4 col-sm-8">
                                <input class="btn btn-default" type="submit" name="submit" value="Login"/>
                                <a class="btn btn-default" href="${pageContext.request.contextPath}/login/signup">Sign Up!</a>
                                <a class="btn btn-default" href="${pageContext.request.contextPath}/">
                                    <span class="glyphicon glyphicon-home" aria-hidden='true'></span>
                                </a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
