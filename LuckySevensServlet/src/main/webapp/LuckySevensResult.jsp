<%-- 
    Document   : LuckySevensResult
    Created on : Oct 20, 2016, 2:33:55 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LuckySevensResult</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/LuckySevensStyle.css">
        <style>
            a:link, a:hover, a:visited, a:active {
                color:#DAA520;
            }
        </style>
    </head>
    <body>
        <div id="hidden" class="container-fluid" style="display: none">
            <div class="form-group row">
                <div class="col-sm-3"></div>
                <div class="col-sm-6">
                    <h1>Lucky Sevens: The Servlet</h1>
                </div>
                <div class="col-sm-3"></div>
            </div>
            <div class="form-group row">
                <div class="col-sm-3"></div>
                <div class="col-sm-6">
                    <h1>The Results:</h1>
                    ${message}<br/>
                </div>
                <div class="col-sm-3"></div>
            </div>
            <div class="form-group row">
                <div class="col-sm-3"></div>
                <div class="col-sm-6">
                    <a href="LuckySevensServlet">Play again?</a>
                </div>
                <div class="col-sm-3"></div>
            </div>
        </div>
        <script type="text/javascript" src="jquery-3.1.1.min.js"></script>
        <script>
            $(document).ready(function() {
                $("#hidden").delay(2000).fadeIn(3000);
            });
        </script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>