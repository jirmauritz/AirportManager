<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:pagetemplate>
    <jsp:attribute name="body">

        <h2 class="sub-header">Flights</h2>
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>International</th>
                        <th>Departure</th>
                        <th>Arrival</th>
                        <th>Airplane</th>
                        <th>From</th>
                        <th>To</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Output every flight in flights variable -->
                    <c:forEach items="${flights}" var="flight">
                        <tr>
                            <td><c:out value="${flight.id}"/></td>
                            <td><c:out value="${flight.international}"/></td>
                            <td><c:out value="${flight.departure}"/></td>
                            <td><c:out value="${flight.arrival}"/></td>
                            <td><c:out value="${flight.from}"/></td>
                            <td><c:out value="${flight.to}"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>


</jsp:attribute>
</tags:pagetemplate>
