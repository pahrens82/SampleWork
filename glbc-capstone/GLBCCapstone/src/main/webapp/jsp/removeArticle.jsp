<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Remove Page</title>
        <link href="${pageContext.request.contextPath}/css/GLBCAdminStyle.css" rel="stylesheet" type="text/css" ">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css?family=Trirong" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/img/icon-beer.png" rel="shortcut icon"/>
        <link href="https://fonts.googleapis.com/css?family=Slabo+27px" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src='//cdn.tinymce.com/4/tinymce.min.js'></script>
        <script type="text/javascript">
            tinymce.init({
                selector: '#myeditablediv',
                inline: true
            });
        </script>

    </head>
    <body>
        <div class="row">
            <%@include file="adminTopNavBarFragment.jsp" %>    
        </div>
        <div class="row">
            <div class="col-sm-2"></div>
            <div class="col-sm-5">
                <div class="row">
                    <sf:form class="form-horizontal"
                             role="form" 
                             action="removeArticle" 
                             method="POST" 
                             modelAttribute="article">                        
                        <div class="form-group">
                            <label for="remove-article-article-name" class="col-sm-4 control-label">Article Title:</label>
                            <div class="col-sm-4">
                                <sf:input id="remove-article-article-name" 
                                          name="articleName" 
                                          placeholder="Title"
                                          path="name"
                                          class="form-control"
                                          readonly="true"/>
                            </div>
                            <div class="col-sm-4">
                                <sf:errors path="name" cssClass="error">
                                </sf:errors>
                            </div>
                        </div>                        
                        <div class="form-group">
                            <label for="remove-article-summary" class="col-sm-4 control-label">Article Summary: </label>
                            <div class="col-sm-4">
                                <sf:input id="remove-article-summary" 
                                          name="articleSummary" 
                                          placeholder="Summary"
                                          path="summary"
                                          class="form-control"
                                          readonly="true"/>
                            </div>
                            <div class="col-sm-4">
                                <sf:errors path="summary" cssClass="error">
                                </sf:errors>
                            </div>
                        </div>                        
                        <div class="form-group">
                            <label for="remove-beer-approval" class="col-sm-4 control-label">Approval:</label>
                            <div class="col-sm-4">
                                <sf:radiobutton id="remove-beer-approval" 
                                                path="approval"
                                                value="true"/> True
                                <sf:radiobutton id="remove-beer-approval" 
                                                path="approval"
                                                value="false"/> False
                            </div>
                        </div>
                        <div class="form-group">
                            <sf:hidden path="articleId" />
                        </div>
                        <div class="form-group">
                            <button type="submit">Submit</button>
                            <input type="button" 
                                   onclick="location.href = '${pageContext.request.contextPath}/adminHome';" 
                                   value="Cancel" />
                        </div>

                    </div>
                </div>
                <div class="col-sm-5">
                    <h1>Write Your Article</h1>                   
                    <div id="myeditablediv" path="textBody">Click here to edit!</div>
                </sf:form>   
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/ArticleScripts.js"></script>s
    </body>
</html>
