<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Breweries</title>

        <%@include file="commonLinksFragment.jsp" %>
        <link href="https://fonts.googleapis.com/css?family=Trirong" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Slabo+27px" rel="stylesheet">

    </head>
    <body>
        <div class="container-fluid">
            <div>
                <%@include file="mainNavBarFragment.jsp" %>
            </div>
            <div class="col-sm-7"></div>
            <div id="breweryList" class="col-sm-4">
                <h2>Brewery Listing</h2>
                <div class="row">
                    <c:forEach var="brewery" items="${breweryList}">
                        <s:url value="viewBrewery" var="viewBrewery_url">
                            <s:param name="breweryId" value="${brewery.breweryId}"/>
                        </s:url>
                        <b>Name: <a href="${viewBrewery_url}">${brewery.breweryName}</a></b><br>                        
                        Location: ${brewery.city}, ${brewery.state}

                        <br>
                        <hr/>
                    </c:forEach>
                </div>
            </div>

            <%@include file="searchResultFragment.jsp" %>

        </div>

        <%@include file="commonJSFragment.jsp" %>

    </body>
</html>