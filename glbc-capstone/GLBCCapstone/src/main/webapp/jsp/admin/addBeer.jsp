<%-- 
    Document   : addBeer
    Created on : Nov 28, 2016, 10:59:48 AM
    Author     : apprentice
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Beer</title>

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
                <h1 class="text-center">Add Beer</h1>
                <sf:form class="form-horizontal"
                         role="form" 
                         action="addBeer" 
                         method="POST" 
                         modelAttribute="beer">
                    <div class="form-group">
                        <label for="add-beer-beer-name" class="col-md-4 control-label">Name:</label>
                        <div class="col-md-4">
                            <sf:input id="add-beer-beer-name" 
                                      name="beerName" 
                                      placeholder="Name"
                                      path="beerName"
                                      class="form-control"/>
                        </div>
                        <div class="col-md-4">
                            <sf:errors path="beerName" cssClass="error alert alert-danger">
                            </sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-beer-brewery-id" class="col-md-4 control-label">Brewery:</label>
                        <div class="col-md-4">

                            <sf:select path="breweryId" id="add-beer-brewery-id" class="form-control">
                                <sf:option value="0"> --SELECT--</sf:option>
                                <c:forEach items="${breweryList}" var="brewery">
                                    <c:if test="${brewery.approval==true}">
                                        <sf:option value="${brewery.breweryId}">${brewery.breweryName}</sf:option>
                                    </c:if>
                                </c:forEach>
                            </sf:select>

                        </div>
                        <div class="col-md-4">
                            <sf:errors path="breweryId" cssClass="error alert alert-danger">
                            </sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-beer-style" class="col-md-4 control-label">Style:</label>
                        <div class="col-md-4">
                            <sf:input id="add-beer-style"
                                      name="style"
                                      placeholder="Style"
                                      path="style"
                                      class="form-control"/>
                        </div>
                        <div class="col-md-4">
                            <sf:errors path="style" cssClass="error alert alert-danger">
                            </sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-beer-hop" class="col-md-4 control-label">Hop:</label>
                        <div class="col-md-4">
                            <sf:input id="add-beer-hop"
                                      name="hop"
                                      placeholder="Hop"
                                      path="hop"
                                      class="form-control"/>
                        </div>
                        <div class="col-md-4">
                            <sf:errors path="hop" cssClass="error alert alert-danger">
                            </sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-beer-abv" class="col-md-4 control-label">ABV:</label>
                        <div class="col-md-4">
                            <sf:input id="add-beer-abv"
                                      name="abv"
                                      placeholder="ABV"
                                      path="abv"
                                      class="form-control"/>
                        </div>
                        <div class="col-md-4">
                            <sf:errors path="abv" cssClass="error alert alert-danger">
                            </sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-beer-ibu" class="col-md-4 control-label">IBU:</label>
                        <div class="col-md-4">
                            <sf:input id="add-beer-ibu"
                                      name="ibu"
                                      placeholder="Ibu"
                                      path="ibu"
                                      class="form-control"/>
                        </div>
                        <div class="col-md-4">
                            <sf:errors path="ibu" cssClass="error alert alert-danger">
                            </sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-beer-summary" class="col-md-4 control-label">Beer Summary:</label>
                        <div class="col-md-4">
                            <sf:input id="add-beer-summary"
                                      name="summary"
                                      placeholder="Summary"
                                      path="summary"
                                      class="form-control"/>
                        </div>
                        <div class="col-md-4">
                            <sf:errors path="summary" cssClass="error alert alert-danger">
                            </sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-beer-textBody" class="col-md-4 control-label">Beer Info:</label>
                        <div class="col-md-4">
                            <sf:textarea id="add-beer-textBody"
                                         name="textBody"
                                         rows="5" cols="30"
                                         path="textBody"
                                         class="form-control" />
                        </div>
                        <div class="col-md-4">
                            <sf:errors path="textBody" cssClass="error alert alert-danger">
                            </sf:errors>
                        </div>
                    </div>

                    <!-- Admin gets option -->  
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <div class="form-group">
                            <label for="add-beer-approval" class="col-md-4 control-label">Approval:</label>
                            <div class="col-md-4">
                                <sf:radiobutton id="add-beer-approval" 
                                                path="approval"
                                                value="true"/> True
                                <sf:radiobutton id="add-beer-approval" 
                                                path="approval"
                                                value="false"
                                                checked="checked"/> False
                            </div>
                        </div>
                    </sec:authorize> 

                    <div class="form-group">
                        <sf:hidden path="beerId" />
                    </div>
                    <div class="form-group">
                        <button class='btn btn-default' type="submit">Submit</button>
                        <a class="btn btn-default" href = "${pageContext.request.contextPath}/admin/beerList">Cancel</a>
                    </div>
                </sf:form>
            </div>
        </div>

        <%@include file="commonAdminJSFragment.jsp" %>

    </body>
</html>
