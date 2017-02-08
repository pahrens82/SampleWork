<%-- 
    Document   : removeBeer
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
        <title>Remove Beer</title>
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
                <h1 class="text-center">Confirm Beer Remove</h1>
                <sf:form class="form-horizontal"
                         role="form" 
                         action="removeBeer" 
                         method="POST" 
                         modelAttribute="beer">
                    <div class="form-group">
                        <label for="remove-beer-beer-name" class="col-md-4 control-label">Name:</label>
                        <div class="col-md-4">
                            <sf:input id="remove-beer-beer-name" 
                                      name="beerName" 
                                      placeholder="Name"
                                      path="beerName"
                                      class="form-control"
                                      readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="remove-beer-breweryName" class="col-md-4 control-label">Brewery:</label>
                        <div class="col-md-4">
                            <input id="remove-beer-breweryName" 
                                   name="brewery" 
                                   placeholder="Brewery"
                                   value="${brewery.breweryName}"
                                   class="form-control"
                                   readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="remove-beer-style" class="col-md-4 control-label">Style:</label>
                        <div class="col-md-4">
                            <sf:input id="remove-beer-style"
                                      name="style"
                                      placeholder="Style"
                                      path="style"
                                      class="form-control"
                                      readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="remove-beer-hop" class="col-md-4 control-label">Hop:</label>
                        <div class="col-md-4">
                            <sf:input id="remove-beer-hop"
                                      name="hop"
                                      placeholder="Hop"
                                      path="hop"
                                      class="form-control"
                                      readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="remove-beer-abv" class="col-md-4 control-label">ABV:</label>
                        <div class="col-md-4">
                            <sf:input id="remove-beer-abv"
                                      name="abv"
                                      placeholder="ABV"
                                      path="abv"
                                      class="form-control"
                                      readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="remove-beer-ibu" class="col-md-4 control-label">IBU:</label>
                        <div class="col-md-4">
                            <sf:input id="remove-beer-ibu"
                                      name="ibu"
                                      placeholder="Ibu"
                                      path="ibu"
                                      class="form-control"
                                      readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="remove-beer-summary" class="col-md-4 control-label">Beer Summary:</label>
                        <div class="col-md-4">
                            <sf:input id="remove-beer-summary"
                                      name="summary"
                                      placeholder="Summary"
                                      path="summary"
                                      class="form-control"
                                      readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="remove-beer-textBody" class="col-md-4 control-label">Beer Info:</label>
                        <div class="col-md-4">
                            <sf:textarea id="remove-beer-textBody"
                                         name="textBody"
                                         rows="5" cols="30"
                                         path="textBody"
                                         class="form-control"
                                         readonly="true"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <sf:hidden path="breweryId"/>
                        <sf:hidden path="approval"/>
                        <sf:hidden path="beerId" />
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