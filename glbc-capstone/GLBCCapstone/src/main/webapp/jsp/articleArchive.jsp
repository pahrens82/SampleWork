<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Article Archive</title>

        <%@include file="commonLinksFragment.jsp" %>
        <link href="https://fonts.googleapis.com/css?family=Cinzel" rel="stylesheet">

    </head>
    <body>
        <div id="userTopNavbar" class="container-fluid">
            <%@include file="mainNavBarFragment.jsp" %>
            <div class="row">
                <div class="col-sm-7">
<!-- -->            <div id="articleArchiveDiv">
<!-- -->                <div id="articleContent" class="table table-hover" style="display: none">                        
                            <div id="searchedArticle">
                                
                            </div>
                        </div>
                    </div>
                </div>
                <div id="articleList" class="col-sm-4">
                    <table id="articleTitleList" class="table table-hover">
                        <thead id="thead"></thead>
                        <tbody id="articleArchiveList"></tbody>
                    </table>
                </div>
            </div>

            <%@include file="searchResultFragment.jsp" %>

        </div>
        <%@include file="commonJSFragment.jsp" %>
    </body>
</html>
