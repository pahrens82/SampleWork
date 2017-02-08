<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Beer Details</title>

        <%@include file="commonLinksFragment.jsp" %>
        <link href="https://fonts.googleapis.com/css?family=Trirong" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Slabo+27px" rel="stylesheet">
        <script>function goBack() {
                window.history.back();
            }
        </script>
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="mainNavBarFragment.jsp" %>
            <div class="col-md-1">
            </div>
            <div id="breweryDetails" class="col-md-7">
                <div class="row">
                    <!--                    <div class="col-sm-2">
                                        </div>-->
                    <div class="col-sm-12">
                        <h2><c:out value="${brewery.breweryName}"/></h2>
                    </div>
                </div>
                <div class="row">

                    <div class="col-sm-12">
                        <div class="row">
                            <div class="col-sm-3">
                                City: 
                            </div>
                            <div class="col-sm-9">
                                <c:out value="${brewery.city}"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-3">
                                State: 
                            </div>
                            <div class="col-sm-9">
                                <c:out value="${brewery.state}"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-3">
                                Brewmaster: </div>
                            <div class="col-sm-9">
                                <c:out value="${brewery.brewmaster}"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-3">
                                Summary: </div>
                            <div class="col-sm-9">
                                <c:out value="${brewery.summary}"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-3">
                                Description: 
                            </div>
                            <div class="col-sm-9">
                                <c:out value="${brewery.textBody}"/>
                            </div>
                        </div>
                        <!--                    <div class="back">
                                                <button onclick="goBack()">Go Back To Brewery List</button>
                                            </div>-->
                        <h5>Beers:</h5>
                        <ul>
                            <c:forEach var="beer" items="${beers}">
                                <s:url value="viewBeer" var="viewBeer_url">
                                    <s:param name="beerId" value="${beer.beerId}"/>
                                </s:url>
                                <c:if test="${beer.approval==true}">
                                    <li><a href="${viewBeer_url}">${beer.beerName}</a></li>
                                    </c:if>
                                </c:forEach>
                        </ul>
                    </div>
                    <div class="back">
                        <button class="btn btn-default" onclick="goBack()"><span class="glyphicon glyphicon-chevron-left"></span>Back</button>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="searchResultFragment.jsp" %>

    </div>

    <%@include file="commonJSFragment.jsp" %>

</body>
</html>