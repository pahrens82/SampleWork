<%-- 
    Document   : adminHome
    Created on : Nov 28, 2016, 11:11:30 AM
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
        <title>Manage Articles</title>
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
            <div class="row col-sm-offset-4 col-sm-4">
                <h1 class="text-center">Manage Article</h1>
            </div>
            <div class="row col-sm-offset-5 col-sm-1">
                <div class="col-sm-offset-0col-sm-1 center-block">
                    <a href = '${pageContext.request.contextPath}/admin/addArticle' 
                            class="btn btn-default"
                            style="width: 150px">Add Article</a>
                </div>
            </div>

            <div class="pad20 row col-sm-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="">Edit / Remove:</h3>
                    </div>
                    <div class="panel-body">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Action</th>
                                    <th>Name</th>
                                    <th>Category</th>
                                    <th>Summary</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="article" items="${articleList}" varStatus="status">

                                    <s:url value="removeArticle" var="deleteArticle_url">
                                        <s:param name="articleId" value="${article.articleId}" />
                                    </s:url>

                                    <s:url value="editArticle" var="editArticle_url">
                                        <s:param name="articleId" value="${article.articleId}" />
                                    </s:url>

                                    <tr <c:if test="${article.approval==false}"> class='danger'</c:if>>
                                        <td>
											<a href="${editArticle_url}" class=""><span class="glyphicon glyphicon-list-alt" aria-hidden='true'></span><span class="glyphicon glyphicon-pencil" aria-hidden='true'></span></a> | <a href="${deleteArticle_url}" class=""><span class="glyphicon glyphicon-trash" style='color:red' aria-hidden='true'></span></a> 
                                        </td>
                                        <td>${article.articleName}</td>
                                        <c:forEach var="category" items="${categoryList}">
                                            <c:if test="${article.categoryId == category.categoryId}">
                                                <td>${category.categoryName}</td>
                                            </c:if>
                                        </c:forEach>
                                        <td>${article.summary}</td>
                                    </tr>

                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="commonAdminJSFragment.jsp" %>
		
    </body>
</html>
