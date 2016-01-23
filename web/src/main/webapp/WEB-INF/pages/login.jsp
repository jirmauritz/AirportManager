<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" session="false" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Airport Manager</title>

        <!-- Latest compiled and minified Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" crossorigin="anonymous">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet">


        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/flights/list">Airport Manager</a>
                </div>
            </div>
        </nav>

        <div class="container">
            <img class="img-responsive center-block img-airplane" src="${pageContext.request.contextPath}/resources/images/airplane.gif" />
            <c:if test="${param.error != null}">
                <div class="alert alert-danger">
                    Bad credentials.
                </div>
            </c:if>
            <c:if test="${param.logout != null}">
                <div class="alert alert-success">
                    You have been logged out.
                </div>
            </c:if>
            <form name="f" class="form-signin" action="login" method="post">
                <input class="form-control" type="text" id="username" name="username" placeholder="Username"/>
                <input class="form-control" type="password" id="password" name="password" placeholder="Password">
                <button type="submit" class="btn btn-md btn-primary btn-block">Log in</button>
                <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />
            </form>
            <p class="text-center">
                For PA165 project needs, there are three users:admin/admin, flight/flight, airport/airport</p>
        </div> <!-- /container -->

    </body>

</html>
