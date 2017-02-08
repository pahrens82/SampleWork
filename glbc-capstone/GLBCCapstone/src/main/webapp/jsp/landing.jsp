<%-- 
    Document   : landing
    Created on : Dec 2, 2016, 3:48:12 PM
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
        <title>Landing</title>
        <link href="${pageContext.request.contextPath}/css/GLBCStyle.css" rel="stylesheet" type="text/css" ">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css?family=Cinzel" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Slabo+27px" rel="stylesheet">
    <i class="fa fa-beer" rel="shortcut icon" aria-hidden="true"></i>
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-offset-6 col-sm-8">
                <h1 id="title">The<br> Beer Blog</h1>
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-offset-6 col-sm-6">
                <strong>Are you at least 21 years old?</strong>
                <div class="dropdown-toggle"></div>
                <div class="dropdown-content"></div>
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-offset-6 col-sm-5">
                <a class="btn btn-default" href="${pageContext.request.contextPath}/home">Yes</a>
                <a class="btn btn-default" href="http://www.warnerbros.com/archive/spacejam/movie/jam.htm">No</a>
            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>    
</body>
</html>
