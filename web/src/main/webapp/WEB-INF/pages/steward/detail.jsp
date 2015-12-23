<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<tags:pagetemplate>

    <jsp:attribute name="body">

        <h1 class="page-header">
            Steward
            <sec:authorize access="hasRole('ROLE_airport')">
                <a href="${pageContext.request.contextPath}/stewards/new/${steward.id}"
                   class="btn btn-primary btn-sm navbut" role="button">Edit</a>
                <a class="btn btn-primary btn-sm navbut" role="button"
                   data-toggle="modal" data-target="#stewardDeleting">Delete</a>
            </sec:authorize>
        </h1>


        <div class="row">
            <div class="col-sm-5">
                <h4> First Name </h4>
                <div class="well">
                    <c:out value="${steward.firstName}"/>
                </div>
                <h4> Last Name </h4>
                <div class="well">
                    <c:out value="${steward.lastName}"/>
                </div>
                <h4> Flights attending </h4>
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
                                <c:forEach items="${steward.flights}" var="flight">
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
            </div>
        </div>

        <div id="stewardDeleting" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Delete Steward</h4>
                    </div>
                    <div class="modal-body">
                        <p>Are you sure you want to delete this steward?</p>
                    </div>
                    <div class="modal-footer">
                        <form method="POST" action="${pageContext.request.contextPath}/stewards/delete/${steward.id}">
                            <input value="OK" type="submit" class="btn btn-default" onclick="resoudre(this)"/>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </form>
                    </div>
                </div>

            </div>
        </div>

    </jsp:attribute>
</tags:pagetemplate>
