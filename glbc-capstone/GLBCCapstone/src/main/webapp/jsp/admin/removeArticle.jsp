<%-- 
    Document   : editBeer
    Created on : Nov 28, 2016, 10:59:48 AM
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
        <title>Remove Article</title>
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

            <h1 class="text-center">Remove Article</h1>
            <sf:form class="form-horizontal"
                     role="form" 
                     action="removeArticle" 
                     method="POST" 
                     modelAttribute="article">
                <div class="form-group">
                    <!--TODO: This is Admin only-->
                    <label for="remove-article-userName" class="col-sm-4 control-label">User Names FPO:</label>
                    <div class="col-sm-4">
                        <sf:input id="remove-article-userName" 
                                  name="name" 
                                  placeholder="User Name FPO"
                                  path="userName"
                                  class="form-control"
                                  readonly="true"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="remove-article-article-name" class="col-sm-4 control-label">Article Title:</label>
                    <div class="col-sm-4">
                        <sf:input id="remove-article-article-name" 
                                  name="articleName" 
                                  placeholder="Title"
                                  path="articleName"
                                  class="form-control"
                                  readonly="true"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="remove-article-summary" class="col-sm-4 control-label">Article Summary: </label>
                    <div class="col-sm-4">
                        <sf:input id="remove-article-summary" 
                                  name="articleSummary" 
                                  placeholder="Summary"
                                  path="summary"
                                  class="form-control"
                                  readonly="true"/>
                    </div>
                </div> 

                <div class="form-group">
                    <sf:hidden path="articleId" />
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-5 col-sm-3">
                    <button type="submit">Delete</button>
                    <input type="button" 
                           onclick="location.href = '${pageContext.request.contextPath}/adminHome';" 
                           value="Cancel" />
                    </div>
                </div>
            </sf:form>
        </div>

        <%@include file="commonAdminJSFragment.jsp" %>
		
    </body>
</html>
