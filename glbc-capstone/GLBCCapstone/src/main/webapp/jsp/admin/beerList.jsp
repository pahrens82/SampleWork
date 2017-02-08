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
        <title>Manage Beer</title>
        <!--Imports -->
        <%@include file="commonAdminLinksFragment.jsp" %>
        <link href="https://fonts.googleapis.com/css?family=Trirong" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Slabo+27px" rel="stylesheet">

        <!-- /Imports -->

        <style>
            .pad20 { 
                margin-top: 20px; 
            }
            h3 {
                margin: 0px;
            }
        </style>
    </head>
    <title>Manage Beer</title>
    <body>
        <%@include file="../adminTopNavBarFragment.jsp" %>

        <div class="container">
            <div class="row col-sm-offset-4 col-sm-4">
                <h1 class="text-center">Manage Beer<span class="glyphicon glyphicons-beer"></span></h1>
            </div>
            <div class="row col-sm-offset-5 col-sm-1">
                <div class="col-sm-offset-0col-sm-1 center-block">
                    <a href = "${pageContext.request.contextPath}/admin/addBeer" 
                            class="btn btn-default"
                            style="width: 150px">Add Beer</a>
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
                                    <th>Brewery</th>
                                    <th>Summary</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="beer" items="${beerList}" varStatus="status">

                                    <s:url value="removeBeer" var="deleteBeer_url">
                                        <s:param name="beerId" value="${beer.beerId}" />
                                    </s:url>
                                    <s:url value="editBeer" var="editBeer_url">
                                        <s:param name="beerId" value="${beer.beerId}" />
                                    </s:url>

                                    <tr <c:if test="${beer.approval==false}"> class='danger'</c:if>>
                                        <td>
                                            <a href="${editBeer_url}" class=""><span class="glyphicon glyphicon-list-alt" aria-hidden='true'></span><span class="glyphicon glyphicon-pencil" aria-hidden='true'></span></a> | <a href="${deleteBeer_url}" class=""><span class="glyphicon glyphicon-trash" style='color:red' aria-hidden='true'></span></a>
                                        </td>
                                        <td>${beer.beerName}</td>
                                        <c:forEach var="brewery" items="${breweryList}">
                                            <c:if test="${beer.breweryId == brewery.breweryId}">
                                                <td>${brewery.breweryName}</td>
                                            </c:if>
                                        </c:forEach>
                                        <td>${beer.summary}</td>
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
