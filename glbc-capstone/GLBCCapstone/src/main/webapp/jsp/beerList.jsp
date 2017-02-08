<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Beer Listing</title>

        <%@include file="commonLinksFragment.jsp" %>
        <link href="https://fonts.googleapis.com/css?family=Trirong" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Slabo+27px" rel="stylesheet">

    </head>
    <body>
        <div  class="container-fluid">
            <%@include file="mainNavBarFragment.jsp" %>
            <div class="row">
                <div class="col-sm-7"></div>
                <div id="beerList" class="col-sm-4">
                    <h2>Beer Listing</h2>
                    <div class="row">
                        <c:forEach var="beer" items="${beerList}">
                            <s:url value="viewBeer" var="viewBeer_url">
                                <s:param name="beerId" value="${beer.beerId}"/>
                            </s:url>
                            <b>Name: <a href="${viewBeer_url}">${beer.beerName}</a></b><br>                
                            Style: ${beer.style}<br>
                            Primary Hop: ${beer.hop}
                            <br>
                            <br>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <%@include file="searchResultFragment.jsp" %>
        </div>
        <%@include file="commonJSFragment.jsp" %>
    </body>
</html>