<%-- 
    Document   : LuckySevensEntry
    Created on : Oct 20, 2016, 2:04:56 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lucky Sevens</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/LuckySevensStyle.css">
    </head>
    <body>
        <div class="container-fluid">
            <div class="form-group row">
                <div class="col-sm-3"></div>
                <div class="col-sm-6">
                    <h1>Lucky Sevens: The Servlet</h1>
                </div>
                <div class="col-sm-3"></div>
            </div>
            <div id="bet" class="form-group row">
                <div class="col-sm-3"></div>
                <div class="col-sm-6">
                    <form id="submit" action="LuckySevensServlet" method="post">
                        <p>Please enter your initial bet <br/>(must be between 1 and 1000, no comma): </p>
                        <input style="width: 250px" required type="number" min="1" max="1000" name="myBet"/>
                        <input style="width: 175px" type="submit" />
                    </form>
                </div>
                <div class="col-sm-3"></div>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
