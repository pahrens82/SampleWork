<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- Top Nav Bar -->
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" 
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/home">GLBC</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/adminHome" id="listBeerNav" style="color:red">Admin <span class="glyphicon glyphicon-home" style="color:red" aria-hidden="true"></span></a></li>
                <!-- Admin gets option -->  
                    <li><a href="${pageContext.request.contextPath}/admin/approveContent" id="addBeerNav">Approve Content</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
                       aria-haspopup="true" aria-expanded="false">Manage Content<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/admin/beerList">Beer</a></li>
                        <li class="divider"></li>
                        <li><a href="${pageContext.request.contextPath}/admin/breweryList">Brewery</a></li>
                        <li class="divider"></li>
                        <li><a href="${pageContext.request.contextPath}/admin/articleList">Article</a></li>
                    </ul>
                    <noscript>
                    <ul class="">
                        <li><a href="${pageContext.request.contextPath}/admin/beerList">Beer</a></li>
                        <li><a href="${pageContext.request.contextPath}/admin/breweryList">Brewery</a></li>
                        <li><a href="${pageContext.request.contextPath}/admin/articleList">Article</a></li>
                    </ul>
                    </noscript>
                </li>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li><a href="${pageContext.request.contextPath}/admin/user/manageUsers" id="listBeerNav">Manage Users</a></li>
                    </sec:authorize>
            </ul>
            <ul class="navbar-right">
                <li class="navbar-text"><sec:authentication property="principal.username"/></li>
                <a class="navbar-text" href="${pageContext.request.contextPath}/j_spring_security_logout">Log Out</a>
            </ul>

        </div>
        <!-- / Nav Link -->
    </div>
</nav>
