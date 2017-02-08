<%-- 
    Document   : approveContent
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
        <title>Approve Content</title>

        <!--Imports -->
        <%@include file="commonAdminLinksFragment.jsp" %>
        <link href="https://fonts.googleapis.com/css?family=Trirong" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Slabo+27px" rel="stylesheet">

        <!-- /Imports -->

        <style>
            .pad20 { 
                margin-bottom: 20px; 
            }
            h3 {
                margin: 0px;
            }
        </style>
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="../adminTopNavBarFragment.jsp" %>
        </div>
        <div class="container">
            <sf:form method="post" action="approveContent" modelAttribute="approvalForm">
                <h1 class="text-center">Approve Content</h1> <br>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <div class="pad20 btn-group col-md-offset-3" role="group">
                        <div class="btn-group" role="group">
                            <button type="submit" class="btn btn-default"><span aria-hidden="true" class="glyphicon glyphicon-approve"></span>Approve Selected Content</button>
                        </div>
                        <div class="btn-group" role="group">
                            <button type="button" class="btn btn-default" onclick="checkAll()">Select All Content</button>
                        </div>
                        <div class="btn-group" role="group">
                            <button type="button" class="btn btn-default" onclick="uncheckAll()">Un-select All Content</button>
                        </div>
                        <script>
                            function checkAll() {
                                // Iterate each checkbox
                                $(':checkbox').each(function () {
                                    this.checked = true;
                                });
                            }
                            function uncheckAll() {
                                // Iterate each checkbox
                                $(':checkbox').each(function () {
                                    this.checked = false;
                                });
                            }
                        </script>

                    </div>
                </sec:authorize>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="">Beer:</h3>
                    </div>
                    <div class="panel-body">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <th width="10%">Approve?</th>
                                        </sec:authorize>
                                    <th width="15%">Action</th>
                                    <th>Name</th>
                                    <th>Brewery</th>
                                    <th>Summary</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="beer" items="${approvalForm.beerList}" varStatus="status">

                                    <s:url value="removeBeer" var="deleteBeer_url">
                                        <s:param name="beerId" value="${beer.beerId}" />
                                    </s:url>
                                    <s:url value="editBeer" var="editBeer_url">
                                        <s:param name="beerId" value="${beer.beerId}" />
                                    </s:url>

                                    <tr>
                                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                                            <td>
                                                <sf:checkbox path="beerList[${status.index}].approval" />
                                            </td>
                                        </sec:authorize>
                                        <td>
                                            <a href="${editBeer_url}" class=""><span class="glyphicon glyphicon-list-alt" aria-hidden='true'></span><span class="glyphicon glyphicon-pencil" aria-hidden='true'></span></a> | <a href="${deleteBeer_url}" class=""><span class="glyphicon glyphicon-trash" style='color:red' aria-hidden='true'></span></a>
                                        </td>
                                        <td>${beer.beerName}</td>
                                        <c:forEach var="brewery" items="${allBreweryList}">
                                            <c:if test="${beer.breweryId == brewery.breweryId}">
                                                <td>${brewery.breweryName}</td>
                                            </c:if>
                                        </c:forEach>
                                        <td>${beer.summary}</td>
                                    </tr>

                                    <!--Hidden fields for model binding-->
                                    <sf:hidden path="beerList[${status.index}].beerName" />
                                    <sf:hidden path="beerList[${status.index}].beerId" />
                                    <sf:hidden path="beerList[${status.index}].breweryId" />
                                    <sf:hidden path="beerList[${status.index}].style" />
                                    <sf:hidden path="beerList[${status.index}].hop" />
                                    <sf:hidden path="beerList[${status.index}].abv" />
                                    <sf:hidden path="beerList[${status.index}].ibu" />
                                    <sf:hidden path="beerList[${status.index}].summary" />
                                    <sf:hidden path="beerList[${status.index}].textBody" />
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="">Brewery:</h3>
                    </div>
                    <div class="panel-body">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <th width="10%">Approve?</th>
                                        </sec:authorize>
                                    <th width="15%">Action</th>
                                    <th>Name</th>
                                    <th>Location</th>
                                    <th>Summary</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="brewery" items="${approvalForm.breweryList}" varStatus="status">

                                    <s:url value="removeBrewery" var="deleteBrewery_url">
                                        <s:param name="breweryId" value="${brewery.breweryId}" />
                                    </s:url>
                                    <s:url value="editBrewery" var="editBrewery_url">
                                        <s:param name="breweryId" value="${brewery.breweryId}" />
                                    </s:url>

                                    <tr>
                                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                                            <td>
                                                <sf:checkbox path="breweryList[${status.index}].approval" />
                                            </td>
                                        </sec:authorize>
                                        <td>
                                            <a href="${editBrewery_url}" class=""><span class="glyphicon glyphicon-list-alt" aria-hidden='true'></span><span class="glyphicon glyphicon-pencil" aria-hidden='true'></span></a> | <a href="${deleteBrewery_url}" class=""><span class="glyphicon glyphicon-trash" style='color:red' aria-hidden='true'></span></a>
                                        </td>
                                        <td>${brewery.breweryName}</td>
                                        <td>${brewery.city}, ${brewery.state}</td>
                                        <td>${brewery.summary}</td>
                                    </tr>

                                    <!--Hidden fields for model binding-->
                                    <sf:hidden path="breweryList[${status.index}].breweryName" />
                                    <sf:hidden path="breweryList[${status.index}].city" />
                                    <sf:hidden path="breweryList[${status.index}].state" />
                                    <sf:hidden path="breweryList[${status.index}].brewmaster" />
                                    <sf:hidden path="breweryList[${status.index}].summary" />
                                    <sf:hidden path="breweryList[${status.index}].textBody" />
                                    <sf:hidden path="breweryList[${status.index}].breweryId" />
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="">Article:</h3>
                    </div>
                    <div class="panel-body">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <th width="10%">Approve?</th>
                                        </sec:authorize>
                                    <th width="15%">Action</th>
                                    <th>Name</th>
                                    <th>User Name</th>
                                    <th>Summary</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="article" items="${approvalForm.articleList}" varStatus="status">

                                    <s:url value="removeArticle" var="deleteArticle_url">
                                        <s:param name="articleId" value="${article.articleId}" />
                                    </s:url>
                                    <s:url value="editArticle" var="editArticle_url">
                                        <s:param name="articleId" value="${article.articleId}" />
                                    </s:url>

                                    <tr>
                                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                                            <td>
                                                <sf:checkbox path="articleList[${status.index}].approval" />
                                            </td>
                                        </sec:authorize>
                                        <td>
                                            <a href="${editArticle_url}" class=""><span class="glyphicon glyphicon-list-alt" aria-hidden='true'></span><span class="glyphicon glyphicon-pencil" aria-hidden='true'></span></a> | <a href="${deleteArticle_url}" class=""><span class="glyphicon glyphicon-trash" style='color:red' aria-hidden='true'></span></a>
                                        </td>
                                        <td>${article.articleName}</td>
                                        <td>${article.userName}</td>
                                        <td>${article.summary}</td>
                                    </tr>

                                    <!--Hidden fields for model binding-->
                                    <sf:hidden path="articleList[${status.index}].articleId" />
                                    <sf:hidden path="articleList[${status.index}].userName" />
                                    <sf:hidden path="articleList[${status.index}].articleName" />
                                    <sf:hidden path="articleList[${status.index}].summary" />
                                    <sf:hidden path="articleList[${status.index}].textBody" />
                                    <sf:hidden path="articleList[${status.index}].createDate" />
                                    <sf:hidden path="articleList[${status.index}].editDate" />
                                    <sf:hidden path="articleList[${status.index}].approveDate" />
                                    <sf:hidden path="articleList[${status.index}].publishDate" />
                                    <sf:hidden path="articleList[${status.index}].categoryId" />

                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>


            </sf:form>

        </div>
        
		<%@include file="commonAdminJSFragment.jsp" %>
		
    </body>
</html>
