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
        <title>Edit Beer</title>
        <!--Imports -->
        <%@include file="commonAdminLinksFragment.jsp" %>
        <link href="https://fonts.googleapis.com/css?family=Trirong" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Slabo+27px" rel="stylesheet">

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
                <h1 class="text-center">Edit Beer</h1>
                <sf:form class="form-horizontal"
                         role="form" 
                         action="editBeer" 
                         method="POST" 
                         modelAttribute="beer">
                    <div class="form-group">
                        <label for="edit-beer-beer-name" class="col-md-4 control-label">Name:</label>
                        <div class="col-md-4">
                            <sf:input id="edit-beer-beer-name" 
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
                        <label for="edit-beer-brewery-id" class="col-md-4 control-label">Brewery:</label>
                        <div class="col-md-4">
                            <sf:select path="breweryId">
                                <sf:option value="0"> --SELECT--</sf:option>
                                <c:forEach items="${breweryList}" var="brewery">
                                    <sf:option value="${brewery.breweryId}">${brewery.breweryName}</sf:option>
                                </c:forEach>
                            </sf:select>
                        </div>
                        <div class="col-md-4">
                            <sf:errors path="breweryId" cssClass="error alert alert-danger">
                            </sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-beer-style" class="col-md-4 control-label">Style:</label>
                        <div class="col-md-4">
                            <sf:input id="edit-beer-style"
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
                        <label for="edit-beer-hop" class="col-md-4 control-label">Hop:</label>
                        <div class="col-md-4">
                            <sf:input id="edit-beer-hop"
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
                        <label for="edit-beer-abv" class="col-md-4 control-label">ABV:</label>
                        <div class="col-md-4">
                            <sf:input id="edit-beer-abv"
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
                        <label for="edit-beer-ibu" class="col-md-4 control-label">IBU:</label>
                        <div class="col-md-4">
                            <sf:input id="edit-beer-ibu"
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
                        <label for="edit-beer-summary" class="col-md-4 control-label">Beer Summary:</label>
                        <div class="col-md-4">
                            <sf:input id="edit-beer-summary"
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
                        <label for="edit-beer-textBody" class="col-md-4 control-label">Beer Info:</label>
                        <div class="col-md-4">
                            <sf:textarea id="edit-beer-textBody"
                                         name="textBody"
                                         rows="5" cols="30"
                                         path="textBody"
                                         class="form-control"/>
                        </div>
                        <div class="col-md-4">
                            <sf:errors path="textBody" cssClass="error alert alert-danger">
                            </sf:errors>
                        </div>
                    </div>
                        
                        <!-- Admin gets option -->  
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <div class="form-group">
                            <label for="edit-beer-approval" class="col-md-4 control-label">Approval:</label>
                            <div class="col-md-4">
                                <sf:radiobutton id="edit-beer-approval" 
                                                path="approval"
                                                value="true"/> True
                                <sf:radiobutton id="edit-beer-approval" 
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
                        <button class='btn btn-default' type="submit">Update</button>
                        <button class="btn btn-default" href = "${pageContext.request.contextPath}/admin/beerList">Cancel</button>
                    </div>
                </sf:form>
            </div>
        </div>
        
		<%@include file="commonAdminJSFragment.jsp" %>
		
    </body>
</html>
