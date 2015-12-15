<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" required="true" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Airport Manager</title>

        <!-- Latest compiled and minified Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" 
              integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

        <!-- Custom styles for this template -->
        <link href="<%=request.getContextPath()%>/resources/css/dashboard.css" rel="stylesheet">

        <!-- Bootstrap select css -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.8.1/css/bootstrap-select.min.css">



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
                    <a class="navbar-brand" href="">Airport Manager</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse navbar-right">
                    <a href="logout" class="btn btn-primary btn-sm navbut" class="" role="button">Log Out</a>
                </div>
            </div>
        </nav>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3 col-md-2 sidebar">
                    <ul class="nav nav-sidebar">
                        <li class="${fn:contains(pageContext.request.requestURI, 'home') ? ' active' : ''}"><a href="${pageContext.request.contextPath}">Overview </a></li>
                        <li class="${fn:contains(pageContext.request.requestURI, 'flight') ? ' active' : ''}"><a href="${pageContext.request.contextPath}/flights/list">Flights</a></li>
                        <li class="${fn:contains(pageContext.request.requestURI, 'airplane') ? ' active' : ''}"><a href="">Airplanes</a></li>
                        <li class="${fn:contains(pageContext.request.requestURI, 'steward') ? ' active' : ''}"><a href="">Stewards</a></li>
                        <li class="${fn:contains(pageContext.request.requestURI, 'destination') ? ' active' : ''}"><a href="">Destinations</a></li>
                    </ul>

                    <!-- Submenu is different in every page -->
                    <!-- FLIGHT submenu -->
                    <c:if test="${fn:contains(pageContext.request.requestURI, 'flight')}">
                        <ul class="nav nav-sidebar">
                            <li><a href="${pageContext.request.contextPath}/flights/new"><b>New flight</b></a></li>
                            <li><a href="${pageContext.request.contextPath}/flights/list"><b>List all flights</b></a></li>
                            <li><a href="${pageContext.request.contextPath}/flights/search"><b>Search for a flight</b></a></li>
                        </ul>
                    </c:if>

                    <!-- AIRPLANE submenu -->
                    <c:if test="${fn:contains(pageContext.request.requestURI, 'airplane')}">
                        <ul class="nav nav-sidebar">
                            <li><a href="${pageContext.request.contextPath}/airplanes/new"><b>New airplane</b></a></li>
                            <li><a href="${pageContext.request.contextPath}/airplanes/list"><b>List all airplanes</b></a></li>
                            <li><a href="${pageContext.request.contextPath}/airplanes/search"><b>Search for an airplane</b></a></li>
                        </ul> 
                    </c:if>

                    <!-- DESTINATION submenu -->
                    <c:if test="${fn:contains(pageContext.request.requestURI, 'destination')}">
                        <ul class="nav nav-sidebar">
                            <li><a href="${pageContext.request.contextPath}/destinations/new"><b>New destination</b></a></li>
                            <li><a href="${pageContext.request.contextPath}/destinations/list"><b>List all destinations</b></a></li>
                            <li><a href="${pageContext.request.contextPath}/destinations/search"><b>Search for a destination</b></a></li>
                        </ul> 
                    </c:if>

                    <!-- STEWARD submenu -->
                    <c:if test="${fn:contains(pageContext.request.requestURI, 'steward')}">
                        <ul class="nav nav-sidebar">
                            <li><a href="${pageContext.request.contextPath}/stewards/new"><b>New steward</b></a></li>
                            <li><a href="${pageContext.request.contextPath}/stewards/list"><b>List all stewards</b></a></li>
                            <li><a href="${pageContext.request.contextPath}/stewards/search"><b>Search for a steward</b></a></li>
                        </ul> 
                    </c:if>

                </div>

                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

                    <!-- page body -->
                    <jsp:invoke fragment="body"/>

                </div>
            </div>
        </div>
    </body>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Latest compiled and minified Bootstrap JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" 
    integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
    <!-- Bootstrap select js -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.8.1/js/bootstrap-select.min.js"></script>
    <!-- Custom js file -->
    <script src="<%=request.getContextPath()%>/resources/js/dashboard.js"></script>
</html>