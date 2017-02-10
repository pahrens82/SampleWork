<%-- 
    Document   : FlooringCalculatorResult
    Created on : Oct 21, 2016, 11:38:56 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Flooring Calculator Result</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/FloorCalcStyle.css">
    </head>
    <body>
        <h1 id="title" style="padding-bottom: 235px; display: none">Flooring Calculator Result</h1>
        <p id="result" style="display: none">${message1}<br/>
            ${message2}<br/>
            ${message3}<br/>
            ${message4}</p>
        <a id="doAgain" style="display: none" href="FlooringCalculatorServlet">Run a another calculation?</a>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script>
            $(document).ready(function () {
                $("#title").delay(750).fadeIn(3000);
            });
            $(document).ready(function () {
                $("#result").delay(2500).fadeIn(3000);
            });
            $(document).ready(function () {
                $("#doAgain").delay(3500).fadeIn(3000);
            });
        </script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
