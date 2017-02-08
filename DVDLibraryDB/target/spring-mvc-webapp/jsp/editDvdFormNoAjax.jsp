<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

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
            <a href="displayDvdLibraryNoAjax">Dvd Library (No Ajax)</a>><br>
            <sf:form class="form-horizontal" role="form" modelAttribute="dvd" action="editNewDvdNoAjax" method="POST">
                <div class="form-group">
                    <label for="add-title" class="col-sm-4 control-label">Title:</label>
                    <div class="col-sm-8">
                        <sf:input type="text" class="form-control" id="edit-title" path="title" placeholder="Title:"/>
                        <sf:errors path="title" cssClass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-date" class="col-sm-4 control-label">Release Date:</label>
                    <div class="col-sm-8">
                        <sf:input type="text" class="form-control" id="edit-date" path="date" placeholder="Date:"/>
                        <sf:errors path="date" cssClass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-director" class="col-sm-4 control-label">Director:</label>
                    <div class="col-sm-8">
                        <sf:input type="text" class="form-control" id="edit-director" path="director" placeholder="Director:"/>
                        <sf:errors path="director" cssClass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-studio" class="col-sm-4 control-label">Production Studio:</label>
                    <div class="col-sm-8">
                        <sf:input type="text" class="form-control" id="edit-studio" path="studio" placeholder="Studio:"/>
                        <sf:errors path="studio" cssClass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-rating" class="col-sm-4 control-label">MPAA Rating:</label>
                    <div class="col-sm-8">
                        <sf:input type="text" class="form-control" id="edit-rating" path="rating" placeholder="Rating:"/>
                        <sf:errors path="rating" cssClass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-notes" class="col-sm-4 control-label">Notes:</label>
                    <div class="col-sm-8">
                        <sf:input type="text" class="form-control" id="edit-notes" path="notes" placeholder="Notes:"/>
                        <sf:errors path="notes" cssClass="error"></sf:errors>
                    </div>
                </div>
                    <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-8">
                            <button type="submit" id="add-button" class="btn btn-default">Submit edits</button>
                        </div>
                    </div>
            </sf:form>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>