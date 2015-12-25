<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" crossorigin="anonymous">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/resources/css/dashboard.css" rel="stylesheet">

        <!-- Bootstrap select css -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.8.1/css/bootstrap-select.min.css">

        <!-- Bootstrap datetime picker css -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css">

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
                <div id="navbar" class="navbar-collapse collapse navbar-right">
                    <a href="${pageContext.request.contextPath}/logout" class="btn btn-primary btn-sm navbut" role="button">Log Out</a>
                </div>
            </div>
        </nav>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3 col-md-2 sidebar">
                    <ul class="nav nav-sidebar">

                        <li><a href="${pageContext.request.contextPath}/flights/list">Flights</a></li>
                        <c:if test="${fn:contains(pageContext.request.requestURI, 'flight')}">
                            <li class="${fn:contains(pageContext.request.requestURI, 'flight/list') ? ' active' : ''}">
                                <a href="${pageContext.request.contextPath}/flights/list">&nbsp;&nbsp;&nbsp;&nbsp;<b>List all flights</b></a>
                            </li>
                            <sec:authorize access="hasRole('ROLE_flight')">
                                <li class="${fn:contains(pageContext.request.requestURI, 'flight/new') ? ' active' : ''}">
                                    <a href="${pageContext.request.contextPath}/flights/new">&nbsp;&nbsp;&nbsp;&nbsp;<b>New flight</b></a>
                                </li>
                            </sec:authorize>
                        </c:if>

                        <li><a href="${pageContext.request.contextPath}/airplanes/list">Airplanes</a></li>
                        <c:if test="${fn:contains(pageContext.request.requestURI, 'airplane')}">
                            <li class="${fn:contains(pageContext.request.requestURI, 'airplane/list') ? ' active' : ''}">
                                <a href="${pageContext.request.contextPath}/airplanes/list">&nbsp;&nbsp;&nbsp;&nbsp;<b>List all airplanes</b></a>
                            </li>
                            <sec:authorize access="hasRole('ROLE_airport')">
                                <li class="${fn:contains(pageContext.request.requestURI, 'airplane/new') ? ' active' : ''}">
                                    <a href="${pageContext.request.contextPath}/airplanes/new">&nbsp;&nbsp;&nbsp;&nbsp;<b>New airplane</b></a>
                                </li>
                            </sec:authorize>
                        </c:if>

                        <li><a href="${pageContext.request.contextPath}/stewards/list">Stewards</a></li>
                        <c:if test="${fn:contains(pageContext.request.requestURI, 'steward')}">
                            <li class="${fn:contains(pageContext.request.requestURI, 'steward/list') ? ' active' : ''}">
                                <a href="${pageContext.request.contextPath}/stewards/list">&nbsp;&nbsp;&nbsp;&nbsp;<b>List all stewards</b></a>
                            </li>
                            <sec:authorize access="hasRole('ROLE_airport')">
                                <li class="${fn:contains(pageContext.request.requestURI, 'steward/new') ? ' active' : ''}">
                                    <a href="${pageContext.request.contextPath}/stewards/new">&nbsp;&nbsp;&nbsp;&nbsp;<b>New steward</b></a>
                                </li>
                            </sec:authorize>
                        </c:if>

                        <li><a href="${pageContext.request.contextPath}/destinations/list">Destinations</a></li>
                        <c:if test="${fn:contains(pageContext.request.requestURI, 'destination')}">
                            <li class="${fn:contains(pageContext.request.requestURI, 'destination/list') ? ' active' : ''}">
                                <a href="${pageContext.request.contextPath}/destinations/list">&nbsp;&nbsp;&nbsp;&nbsp;<b>List all destinations</b></a>
                            </li>
                            <sec:authorize access="hasRole('ROLE_airport')">
                                <li class="${fn:contains(pageContext.request.requestURI, 'destination/new') ? ' active' : ''}">
                                    <a href="${pageContext.request.contextPath}/destinations/new">&nbsp;&nbsp;&nbsp;&nbsp;<b>New destination</b></a>
                                </li>
                            </sec:authorize>
                        </c:if>

                    </ul>
                </div>

                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

                    <c:if test="${not empty error}">
                        <div class="alert alert-danger" role="alert">
                            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                            <strong>Error: </strong><c:out value="${error}"/>
                        </div>
                    </c:if>

                    <c:if test="${not empty warning}">
                        <div class="alert alert-warning" role="alert">
                            <span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>
                            <strong>Warning: </strong><c:out value="${warning}"/>
                        </div>
                    </c:if>

                    <c:if test="${not empty success}">
                        <div class="alert alert-success" role="alert">
                            <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            <span class="sr-only">Success:</span>
                            <strong>Success: </strong><c:out value="${success}"/>
                        </div>
                    </c:if>

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
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" crossorigin="anonymous"></script>
    <!-- Bootstrap select js -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.8.1/js/bootstrap-select.min.js"></script>
    <!-- Bootstrap datetimepicker js -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
    <!-- Custom js file -->
    <script src="${pageContext.request.contextPath}/resources/js/dashboard.js"></script>
</html>