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
        <title>Add Article</title>

        <%@include file="commonAdminLinksFragment.jsp" %>
        <link href="https://fonts.googleapis.com/css?family=Trirong" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Slabo+27px" rel="stylesheet">
        <script src='//cdn.tinymce.com/4/tinymce.min.js'></script>


    </head>
    <body>
        <%@include file="../adminTopNavBarFragment.jsp" %>

        <div class="row">
            <h1 class="text-center">Add Article</h1>
            <br>
            <div class="col-sm-offset-1 col-sm-5">
                <div class="row">
                    <!--Get username for form-->
                    <sec:authentication property="principal.username" var="username" />

                    <sf:form class="form-horizontal"
                             role="form" 
                             action="addArticle" 
                             method="POST" 
                             modelAttribute="article">                        
                        <div class="form-group">
                            <label for="add-article-userName" class="col-sm-4 control-label">User Name:</label>
                            <div class="col-sm-4">
                                <c:out value="${username}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-article-article-name" class="col-sm-4 control-label">Article Title:</label>
                            <div class="col-sm-4">
                                <sf:input id="add-article-article-name" 
                                          name="articleName" 
                                          placeholder="Title"
                                          path="articleName"
                                          class="form-control"/>
                            </div>
                            <div class="col-sm-4">
                                <sf:errors path="articleName" cssClass="error alert alert-danger">
                                </sf:errors>
                            </div>

                        </div>                        
                        <div class="form-group">
                            <label for="add-article-summary" class="col-sm-4 control-label">Article Summary: </label>
                            <div class="col-sm-4">
                                <sf:input id="add-article-summary" 
                                          name="articleSummary" 
                                          placeholder="Summary"
                                          path="summary"
                                          class="form-control"/>
                            </div>
                            <div class="col-sm-4">
                                <sf:errors path="summary" cssClass="error alert alert-danger">
                                </sf:errors>
                            </div>

                        </div>  

                        <div class="form-group">
                            <label for="add-article-category" class="col-sm-4 control-label">Category:</label>
                            <div class="col-sm-4">
                                <sf:select path="categoryId">
                                    <sf:option value="0"> --SELECT--</sf:option>
                                    <c:forEach items="${categoryList}" var="category">
                                        <sf:option value="${category.categoryId}">${category.categoryName}</sf:option>
                                    </c:forEach>
                                </sf:select>
                            </div>
                            <div class="col-sm-4">
                                <sf:errors path="categoryId" cssClass="error alert alert-danger">
                                </sf:errors>
                            </div>

                        </div>

                        <!-- Admin gets option -->  
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <div class="form-group">
                                <label for="add-article-approval" class="col-sm-4 control-label">Approval:</label>
                                <div class="col-sm-4">
                                    <sf:radiobutton id="add-article-approval" 
                                                    path="approval"
                                                    value="true"/> True
                                    <sf:radiobutton id="add-article-approval" 
                                                    path="approval"
                                                    value="false"
                                                    checked="checked"/> False
                                </div>
                            </div>
                        </sec:authorize> 


                        <div class="form-group">
                            <sf:hidden path="articleId" />
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-3 col-sm-4">
                                <button type="submit">Submit</button>
                                <input type="button" 
                                       onclick="location.href = '${pageContext.request.contextPath}/adminHome';" 
                                       value="Cancel" />
                            </div>
                        </div>

                    </div>
                </div>
                <div class="form-group" display='none'>
                    <label for="add-article-textBody" class="col-sm-offset-1 col-sm-4 control-label">Text Body </label>
                    <div class="col-sm-offset-1 col-sm-4">
                        <sf:textarea id="add-article-textBody" 
                                     name="articletextBody" 
                                     placeholder="Content"
                                     path="textBody"
                                     class="form-control"/>
                    </div>
                    <div class="col-sm-offset-1 col-sm-4">
                        <br>
                        <sf:errors path="textBody" cssClass="error alert alert-danger">
                        </sf:errors>
                    </div>

                </div>



                <sf:hidden path="userName" value="${username}" />
                <sf:hidden path="publishDate" />
                <sf:hidden path="approveDate" />
                <sf:hidden path="editDate" />
                <sf:hidden path="createDate" />
            </sf:form>   
        </div>
        <script type="text/javascript">
            tinymce.init({
                selector: 'textarea',
                inline: false
            });
        </script>

        <%@include file="commonAdminJSFragment.jsp" %>

    </body>
</html>
