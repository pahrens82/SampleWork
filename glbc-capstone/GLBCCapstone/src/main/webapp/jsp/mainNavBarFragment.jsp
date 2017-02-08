<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- Top Nav Bar -->
<div class="row">
    <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" 
                        data-target="#bs-example-navbar-collapse-2" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath}/home"><span class="glyphicon glyphicon-home" aria-hidden="true"></span></a>
                    <sec:authorize access="hasRole('ROLE_SUPER')">
                    <a class="navbar-brand nav-link" style="color:red" href="${pageContext.request.contextPath}/adminHome">Admin <span class="glyphicon glyphicon-home" style="color:red" aria-hidden="true"></span></a>
                    </sec:authorize>
            </div>

            <div class="navbar-collapse collapse" id="bs-example-navbar-collapse-2">
                <ul class="nav navbar-nav">
                    <li><a class="nav-link" href="${pageContext.request.contextPath}/articleArchive"><img style="filter: invert(1)" src="${pageContext.request.contextPath}/img/icon-duplicate.png" rel=""/> Article Archive</a></li>
                    <li><a class="nav-link" href="${pageContext.request.contextPath}/beerList"><img style="filter: invert(1)" src="${pageContext.request.contextPath}/img/icon-beer.png" rel=""/> Beer</a></li>
                    <li><a class="nav-link" href="${pageContext.request.contextPath}/breweryList"><img style="filter: invert(1)" src="${pageContext.request.contextPath}/img/icon-factory.png" rel=""/> Breweries</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <form class="navbar-form navbar-left form-horizontal" role="search">
                            <label for="search-criteria-box" class="form-horizontal">
                                <select class="form-control" id="select-search-type">
                                    <option value="" selected>-- Search For --</option>
                                    <option value="Articles">Articles</option>
                                    <option value="Beer">Beer</option>
                                    <option value="Brewery">Brewery</option>
                                </select>
                            </label>
                            <div class="input-group ">
                                <input type="text" class="form-control form-horizontal" id="search-criteria-box" placeholder="Search Box">
                                <div class="input-group-btn">
                                    <button class="btn btn-default" type="submit" id="search-button"><i class="glyphicon glyphicon-search"></i></button>
                                </div>
                            </div>
                        </form>
                    </li>
                    <sec:authorize access="!isAuthenticated()">
                        <li>
                            <div>
                                <a class="navbar-link navbar-text" href="${pageContext.request.contextPath}/login">Login <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span></a>
                            </div>
                        </li>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <li>
                            <div>
                                <div class="navbar-text">
                                    <sec:authentication property="principal.username"/> <span class="glyphicon glyphicon-user"></span>
                                </div>
                                <a class="navbar-link navbar-text" href="${pageContext.request.contextPath}/j_spring_security_logout">Log Out <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span></a>
                            </div>
                        </li>
                    </sec:authorize>
                </ul>

            </div>
        </div>
    </div>
</div>