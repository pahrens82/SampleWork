<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
        <link href="${pageContext.request.contextPath}/css/GLBCStyle.css" rel="stylesheet" type="text/css">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css?family=Trirong" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/img/icon-beer.png" rel="shortcut icon"/>
        <link href="https://fonts.googleapis.com/css?family=Slabo+27px" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    </head>
    <body>
        <div id="userTopNavbar" class="container-fluid">
            <%@include file="mainNavBarFragment.jsp" %>
        </div>
		
		<div class="row" style="padding-top: 50px">
            <div class="col-sm-8">
                <form role="form" class="form-horizontal">
                    <div class="form-group">
						<label for="article-search-criteria" class="col-md-4 control-label">Article Search:</label>
						<div class="col-md-8">
							<input type="text" class="form-control" id="article-search-criteria" placeholder="Name/Author">
						</div>
					</div>
                    <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-8">
                            <button type="submit" id="article-search-button" class="btn">Search</button>
                        </div>
                    </div>

                </form>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-8">
                <div id="articleDiv">
                    <div id="articleContent" class="table table-hover" style="display: none">                        
                        <div id="searchedArticle">
                            <div id="articleTitle"></div>
                            <div id="userName"></div>
                            <div id="publicationDate"></div>
                            <hr>
                            <div id="textBody"></div>
                        </div>
                    </div>
                </div>
            </div>
        
			
			<div class="col-sm-4">
				<div id="articleSearchDiv">
					<table id="articleSearchTable" class="table table-hover" style="display: none">
						<tr>
							<th colspan="3" width="100%">Articles</th>
						</tr>
						<tbody id="articleSearchResults"></tbody>
					</table>
				</div>
			</div>
		</div> 
		
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ArticleSearchScripts.js"></script>
		<script src="${pageContext.request.contextPath}/js/ArticleListScripts.js"></script>
    </body>
</html>
