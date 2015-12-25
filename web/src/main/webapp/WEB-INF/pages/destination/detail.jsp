<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<tags:pagetemplate>

    <jsp:attribute name="body">

        <h1 class="page-header">
            Destination
            <sec:authorize ifAllGranted="ROLE_airport">
            <a href="${pageContext.request.contextPath}/destinations/updating/${destination.id}"
               class="btn btn-primary btn-sm navbut" role="button">Edit</a>
            <a class="btn btn-primary btn-sm navbut" role="button"
               data-toggle="modal" data-target="#destinationDeleting">Delete</a>
            </sec:authorize>
        </h1>

        <div class="row">
            <div class="col-sm-5">
                <h4>Name</h4>
                <div class="well">
                    <c:out value="${destination.name}"/>
                </div>
                <h4>City</h4>
                <div class="well">
                    <c:out value="${destination.city}"/>
                </div>
                <h4>Country</h4>
                <div class="well">
                    <c:out value="${destination.country}"/>
                </div>
            </div>
        </div>

         <h4> Flights assigned</h4>
                <div class="well">
                    <div class="table-responsive">
                        <table class="table table-striped table-hover listTable">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>International</th>
                                <th>Airplane</th>
                                <th>Departure</th>
                                <th>Arrival</th>
                                <th>From</th>
                                <th>To</th>
                            </tr>
                            </thead>
                            <tbody>
                            <!-- Output every flight in flights variable -->
                            <c:forEach items="${flights}" var="flight">
                                <tr class="listTable" onclick="location.href = '${pageContext.request.contextPath}/flights/detail/${flight.id}';">
                                    <td><c:out value="${flight.id}"/></td>
                                    <td><c:out value="${flight.international ? 'Yes' : 'No'}"/></td>
                                    <td><c:out value="${flight.airplane.name}"/></td>
                                    <td><c:out value="${flight.departure}"/></td>
                                    <td><c:out value="${flight.arrival}"/></td>
                                    <td><c:out value="${flight.from.name}"/></td>
                                    <td><c:out value="${flight.to.name}"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

        <div id="destinationDeleting" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Delete Destination</h4>
                    </div>
                    <div class="modal-body">
                        <p>Are you sure you want to delete this destination?</p>
                    </div>
                    <div class="modal-footer">
                        <form method="POST" action="${pageContext.request.contextPath}/destinations/delete/${destination.id}">
                            <input value="OK" type="submit" class="btn btn-default" onclick="resoudre(this)"/>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
</tags:pagetemplate>