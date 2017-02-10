<%-- 
    Document   : FlooringCalculatorEntry
    Created on : Oct 21, 2016, 10:55:47 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Flooring Calculator</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/FloorCalcStyle.css">
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div id="title" class="col-sm-4" style="display: none; padding-bottom: 120px">
                    <h1>Flooring Calculator</h1>
                </div>
                <div class="col-lg-8"></div>
            </div>
            <form action="FlooringCalculatorServlet" method="post">
                <div id="form" class="form-group row" style="display: none; padding-bottom: 0px, 0px, 25px, 25px">
                    <div class="row">
                        <div class="col-sm-4">
                            <p>Please enter the length of the floor you are trying to cover</p>
                            <input type="number" required="true" min="1" max="1000" name="length" style="width: 100px"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-4">
                            <p>Please enter the width of the floor you are trying to cover</p>
                            <input type="number" required="true" min="1" max="1000" name="width" style="width: 100px"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-4">
                            <p>Please enter the price of the material you are purchasing</p>
                            <input type="number" required="true" step="any" min="1" max="100" name="materialCost" style="width: 100px"/>
                        </div>
                    </div>
                </div>
                <div id="submit" class="form-group row" style="display: none">
                    <div class="col-sm-4"></div>
                    <div  class="col-sm-4">
                        <input type="submit" name="Calculate"/>
                    </div>
                    <div class="col-sm-4"></div>
                </div>
            </form>
            <div class="col-sm-4"></div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script>
            $(document).ready(function () {
                $("#title").delay(750).fadeIn(3000);
            });
            $(document).ready(function () {
                $("#form").delay(2500).fadeIn(3000);
            });
            $(document).ready(function () {
                $("#submit").delay(2750).fadeIn(3000);
            });
        </script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
