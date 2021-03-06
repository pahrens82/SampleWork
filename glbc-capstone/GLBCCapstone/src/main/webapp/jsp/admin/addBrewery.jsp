<%-- 
    Document   : addBrewery
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
        <title>Add Brewery</title>

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
                <h1 class="text-center">Add Brewery</h1>

                <sf:form class="form-horizontal"
                         role="form" 
                         action="addBrewery" 
                         method="POST" 
                         modelAttribute="brewery">

                    <div class="form-group">
                        <label for="add-brewery-brewery-name" 
                               class="col-md-4 
                               control-label">Name:</label>
                        <div class="col-md-4">
                            <sf:input id="add-brewery-brewery-name" 
                                      name="breweryName" 
                                      placeholder="Name"
                                      path="breweryName"
                                      class="form-control"/>
                        </div>
                        <div class="col-md-4">
                            <sf:errors path="breweryName" cssClass="error alert alert-danger">
                            </sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-brewery-city" 
                               class="col-md-4 
                               control-label">City:</label>
                        <div class="col-md-4">
                            <sf:input id="add-brewery-city" 
                                      name="city" 
                                      placeholder="City"
                                      path="city"
                                      class="form-control"/>
                        </div>
                        <div class="col-md-4">
                            <sf:errors path="city" cssClass="error alert alert-danger">
                            </sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-brewery-state" 
                               class="col-md-4 
                               control-label">State:</label>
                        <div class="col-md-4">
                            <sf:select path="state" id="add-brewery-state" class="form-control" name="state">
                                <sf:option value=""> --SELECT STATE--</sf:option>
                            <c:forEach items="${states}" var="state">
                                <sf:option value="${state.name}">${state.abbreviation} - ${state.name}</sf:option>
                            </c:forEach>
                            </sf:select>
                        </div>
                        <div class="col-md-4">
                            <sf:errors path="state" cssClass="error alert alert-danger">
                            </sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-brewery-brewmaster" 
                               class="col-md-4 
                               control-label">Brewmaster:</label>
                        <div class="col-md-4">
                            <sf:input id="add-brewery-brewmaster" 
                                      name="brewmaster" 
                                      placeholder="Brewmaster"
                                      path="brewmaster"
                                      class="form-control"/>
                        </div>
                        <div class="col-md-4">
                            <sf:errors path="brewmaster" cssClass="error alert alert-danger">
                            </sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-brewery-summary" 
                               class="col-md-4 
                               control-label">Summary:</label>
                        <div class="col-md-4">
                            <sf:input id="add-brewery-summary" 
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
                        <label for="add-brewery-textBody"
                               class="col-md-4 
                               control-label">Brewery Info:</label>
                        <div class="col-md-4">
                            <sf:textarea id="add-brewery-textBody"
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
                            <label for="add-brewery-approval" class="col-md-4 control-label">Approval:</label>
                            <div class="col-md-4">
                                <sf:radiobutton id="add-brewery-approval" 
                                                path="approval"
                                                value="true"/> True
                                <sf:radiobutton id="add-brewery-approval" 
                                                path="approval"
                                                value="false"
                                                checked="checked"/> False
                            </div>
                        </div>
                    </sec:authorize> 


                    <div class="form-group">
                        <sf:hidden path="breweryId" />
                    </div>

                    <div class="form-group">
                        <button class='btn btn-default' type="submit">Submit</button>
                        <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/breweryList">Cancel</a>
                    </div>

                </sf:form>

            </div>
        </div>
		
        <%@include file="commonAdminJSFragment.jsp" %>

    </body>
</html>
