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
        <title>Manage Brewery</title>
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
    <title>Manage Brewery</title>
    <body>
        <%@include file="../adminTopNavBarFragment.jsp" %>


        <div class="container">
            <div class="row col-sm-offset-4 col-sm-4">
                <h1 class="text-center">Manage Brewery</h1>
            </div>
            <div class="row col-sm-offset-5 col-sm-1">
                <div class="col-sm-offset-0col-sm-1 center-block">
                    <a href = "${pageContext.request.contextPath}/admin/addBrewery" 
                            class="btn btn-default"
                            style="width: 150px">Add Brewery</a>
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
                                    <th>Location</th>
                                    <th>Summary</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="brewery" items="${breweryList}" varStatus="status">

                                    <s:url value="removeBrewery" var="deleteBrewery_url">
                                        <s:param name="breweryId" value="${brewery.breweryId}" />
                                    </s:url>
                                    <s:url value="editBrewery" var="editBrewery_url">
                                        <s:param name="breweryId" value="${brewery.breweryId}" />
                                    </s:url>

                                    <tr <c:if test="${brewery.approval==false}"> class='danger'</c:if>>
                                        <td>
											<a href="${editBrewery_url}" class=""><span class="glyphicon glyphicon-list-alt" aria-hidden='true'></span><span class="glyphicon glyphicon-pencil" aria-hidden='true'></span></a> | <a href="${deleteBrewery_url}" class=""><span class="glyphicon glyphicon-trash" style='color:red' aria-hidden='true'></span></a>
                                        </td>
                                        <td>${brewery.breweryName}</td>
                                        <td>${brewery.city}, ${brewery.state}</td>
                                        <td>${brewery.summary}</td>
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
