<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link href="${pageContext.request.contextPath}/css/GLBCStyle.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/img/icon-beer.png" rel="shortcut icon"/>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css?family=Slabo+27px" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    </head>
    <body>
        <div id="userTopNavbar" class="container-fluid">
            <%@include file="mainNavBarFragment.jsp" %>
        </div>
        <div class="row">
            <div class="col-sm-6"></div>
            <div id="articleList" class="col-sm-6">
                <table id="articleTable" class="table table-hover" style="display: none">
                    <tr>
                        <th width="25%">Author</th>
                        <th width="25%">Title</th>
                        <th width="25%">Summary</th>
                        <th width="25%">Date Created</th>
                    </tr>
                    <tbody id="contentRows"></tbody>
                </table>
            </div>
        </div>        

        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/ArticleScripts.js"></script>
    </body>
</html>
