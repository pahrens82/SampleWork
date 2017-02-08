<%-- 
    Document   : removeBrewery
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
        <title>Remove Brewery</title>
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
            <div class="panel panel-default panel-body text-center">
                <h1 class="text-center">Confirm Brewery Remove</h1>

                <sf:form class="form-horizontal"
                         role="form" 
                         action="removeBrewery" 
                         method="POST" 
                         modelAttribute="brewery">

                    <div class="form-group">
                        <label for="edit-brewery-brewery-name" 
                               class="col-md-4 
                               control-label">Name:</label>
                        <div class="col-md-4">
                            <sf:input id="edit-brewery-brewery-name" 
                                      name="breweryName" 
                                      placeholder="Name"
                                      path="breweryName"
                                      class="form-control"
                                      readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-brewery-city" 
                               class="col-md-4 
                               control-label">City:</label>
                        <div class="col-md-4">
                            <sf:input id="edit-brewery-city" 
                                      name="city" 
                                      placeholder="City"
                                      path="city"
                                      class="form-control"
                                      readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-brewery-state" 
                               class="col-md-4 
                               control-label">State:</label>
                        <div class="col-md-4">
                            <sf:input id="edit-brewery-state" 
                                      name="state" 
                                      placeholder="State"
                                      path="state"
                                      class="form-control"
                                      readonly="true"/>
                        </div>
                    </div>

                    <c:if test="${not empty beerList}">
                        <div class="col-md-offset-4 col-md-4">
                            <br><br>

                            <h4>Warning: The following Beers will also be removed</h4>
                            <c:forEach var="beer" items="${beerList}">
                                <s:url value="../beerDetails" var="beerDetails_url">
                                    <s:param name="beerId" value="${beer.beerId}"/>
                                </s:url>

                                <a href="${beerDetails_url}">${beer.beerName}</a><br>
                            </c:forEach>

                            <br><br>
                        </div>
                    </c:if>

                    <div class="form-group">
                        <sf:hidden path="brewmaster"/>
                        <sf:hidden path="summary"/>
                        <sf:hidden path="textBody"/>
                        <sf:hidden path="breweryId" />
                        <sf:hidden path="approval"/>
                    </div>

                    <div class="form-group">
                        <button type="submit">Delete</button>
                        <input type="button" 
                               onclick="location.href = '${pageContext.request.contextPath}/adminHome';" 
                               value="Cancel" />
                    </div>
                </sf:form>
            </div>
        </div>
        
		<%@include file="commonAdminJSFragment.jsp" %>
		
    </body>
</html>