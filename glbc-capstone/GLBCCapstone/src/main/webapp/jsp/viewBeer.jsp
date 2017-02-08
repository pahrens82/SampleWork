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
            <div class="col-sm-1"></div>
            <div id="beerDetails" class="col-sm-7">
                <div class="row">
                    <h2><c:out value="${beer.beerName}"/></h2>
                    <div class="row">
                        <div class="col-sm-3">
                            Style: 
                        </div>
                        <div class="col-sm-9">
                            <c:out value="${beer.style}"/><br>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-3">
                            Hops: 
                        </div>
                        <div class="col-sm-9">
                            <c:out value="${beer.hop}"/><br>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-3">
                            ABV: 
                        </div>
                        <div class="col-sm-9">
                            <c:out value="${beer.abv}"/><br>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-3">
                            IBU: 
                        </div>
                        <div class="col-sm-9">
                            <c:out value="${beer.ibu}"/><br>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-3">
                            Summary: 
                        </div>
                        <div class="col-sm-9">
                            <c:out value="${beer.summary}"/><br>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-3">
                            Description: 
                        </div>
                        <div class="col-sm-9">
                            <c:out value="${beer.textBody}"/><br/>
                        </div>
                    </div>
                    <div class="row">
                        <div class=" col-sm-12 back">
                            <button class="btn btn-default" onclick="goBack()"><span class="glyphicon glyphicon-chevron-left"></span>Back</button>
                        </div>
                    </div>
                    <%@include file="searchResultFragment.jsp" %>

                </div>
            </div>

            <%@include file="commonJSFragment.jsp" %>

    </body>
</html>