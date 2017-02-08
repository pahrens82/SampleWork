<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container-fluid">
            <h1>DVD Library - Home</h1>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/home">Home</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/search">Search</a>
                    </li>                    
                </ul>
            </div>
            <div class="row">
                <div class="col-sm-6">
                    <h2>DVDs</h2>
                    <%@include file="dvdTableFragment.jsp" %>
                </div>
                <div class="col-sm-6">
                    <h2>Add New DVD</h2>
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="add-title" class="col-sm-4 control-label">
                                Title:
                            </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="add-title" placeholder="Title:"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-date" class="col-sm-4 control-label">
                                Release Date:
                            </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="add-date" placeholder="Release Date:"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-director" class="col-sm-4 control-label">
                                Director:
                            </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="add-director" placeholder="Director:"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-studio" class="col-sm-4 control-label">
                                Production Studio:
                            </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="add-studio" placeholder="Studio:"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-rating" class="col-sm-4 control-label">
                                MPAA Rating:
                            </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="add-rating" placeholder="MPAA Rating:"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-notes" class="col-sm-4 control-label">
                                Notes:
                            </label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="add-notes" placeholder="Notes:"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-4 col-sm-8">
                                <button type="submit" id="add-button" class="btn btn-default">Add DVD</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <%@include file="detailsEditModalFragment.jsp" %>

        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/dvdLibrary.js"></script>
    </body>
</html>
