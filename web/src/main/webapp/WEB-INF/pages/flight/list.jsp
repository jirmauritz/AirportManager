<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:pagetemplate>

    <jsp:attribute name="body">

        <h1 class="page-header">Flights</h1>

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
                        <sec:authorize access="hasRole('ROLE_flight')">
                            <th></th>
                        </sec:authorize>
                </tr>
                </thead>
                <tbody>
                    <!-- Output every flight in flights variable -->
                    <c:forEach items="${flights}" var="flight">
                        <tr class="listTable">
                            <td onclick="location.href = '${pageContext.request.contextPath}/flights/detail/${flight.id}';">
                                <c:out value="${flight.id}"/></td>
                            <td onclick="location.href = '${pageContext.request.contextPath}/flights/detail/${flight.id}';">
                                <c:out value="${flight.international ? 'Yes' : 'No'}"/></td>
                            <td onclick="location.href = '${pageContext.request.contextPath}/flights/detail/${flight.id}';">
                                <c:out value="${flight.airplane.name}"/></td>
                            <td onclick="location.href = '${pageContext.request.contextPath}/flights/detail/${flight.id}';">
                                <c:out value="${flight.departure}"/></td>
                            <td onclick="location.href = '${pageContext.request.contextPath}/flights/detail/${flight.id}';">
                                <c:out value="${flight.arrival}"/></td>
                            <td onclick="location.href = '${pageContext.request.contextPath}/flights/detail/${flight.id}';">
                                <c:out value="${flight.from.name}"/></td>
                            <td onclick="location.href = '${pageContext.request.contextPath}/flights/detail/${flight.id}';">
                                <c:out value="${flight.to.name}"/></td>
                    <sec:authorize access="hasRole('ROLE_flight')">
                        <td align="right">
                            <button type="button" class="btn btn-info btn-xs" data-toggle="modal"
                                                  data-target="#myModal_${flight.id}">
                                <span class="glyphicon glyphicon-remove"></span>
                            </button>
                        </td>
                    </sec:authorize>
                    </tr>
                    <sec:authorize access="hasRole('ROLE_flight')">
                        <div id="myModal_${flight.id}" class="modal fade" role="dialog">
                            <div class="modal-dialog">

                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Delete Flight</h4>
                                    </div>
                                    <div class="modal-body">
                                        <p>Are you sure you want to delete this flight?</p>
                                    </div>
                                    <div class="modal-footer">
                                        <a href="${pageContext.request.contextPath}/flights/delete/${flight.id}"
                                           type="button" class="btn btn-default"
                                           onclick="resoudre(this)"> OK</a>
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </sec:authorize>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</jsp:attribute>
</tags:pagetemplate>
