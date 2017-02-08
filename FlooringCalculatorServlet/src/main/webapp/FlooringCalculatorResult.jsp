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
        <h1 style="padding-bottom: 190px">Flooring Calculator Result</h1>
        <p>${message1}<br/>
            ${message2}<br/>
            ${message3}<br/>
            ${message4}</p>
        <a href="FlooringCalculatorServlet"><p>Run a another calculation?</p></a>
    </body>
</html>
