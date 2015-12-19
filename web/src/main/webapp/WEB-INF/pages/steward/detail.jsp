<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:pagetemplate>

    <jsp:attribute name="body">

        <h1 class="page-header">Steward </h1>

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


    </jsp:attribute>
</tags:pagetemplate>
