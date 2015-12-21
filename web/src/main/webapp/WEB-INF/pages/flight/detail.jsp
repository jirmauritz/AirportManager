<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:pagetemplate>

    <jsp:attribute name="body">

        <h1 class="page-header">Flight </h1>

        <div class="row">
            <div class="col-sm-5">
                <h4> Departure </h4>
                <div class="well">
                    <c:out value="${flight.departure}"/>
                </div>   
                <h4> Arrival </h4>
                <div class="well">
                    <c:out value="${flight.arrival}"/>
                </div>  
                <h4> From </h4>
                <div class="well">
                    <table class="table table-condensed">
                        <tr> <td> id: </td>  <td> <c:out value="${flight.from.id}"/> </td></tr>
                        <tr> <td> name: </td> <td><c:out value="${flight.from.name}"/> </td> </tr>
                        <tr> <td> city: </td> <td><c:out value="${flight.from.city}"/> </td> </tr>
                        <tr> <td> country: </td> <td><c:out value="${flight.from.country}"/> </td> </tr>
                    </table>

                </div>  
                <h4> To </h4>
                <div class="well">
                    <table class="table table-condensed">
                        <tr> <td> id: </td>  <td> <c:out value="${flight.to.id}"/> </td></tr>
                        <tr> <td> name: </td> <td><c:out value="${flight.to.name}"/> </td> </tr>
                        <tr> <td> city: </td> <td><c:out value="${flight.to.city}"/> </td> </tr>
                        <tr> <td> country: </td> <td><c:out value="${flight.to.country}"/> </td> </tr>
                    </table>

                </div>  
            </div>
            <div class="col-sm-7">
                <h4> Airplane </h4>
                <div class="well">
                    <table class="table table-condensed">
                        <tr> <td> id: </td>  <td> <c:out value="${flight.airplane.id}"/> </td></tr>
                        <tr> <td> name: </td> <td><c:out value="${flight.airplane.name}"/> </td> </tr>
                        <tr> <td> type: </td> <td><c:out value="${flight.airplane.type}"/> </td> </tr>
                        <tr> <td> capacity: </td> <td><c:out value="${flight.airplane.capacity}"/> </td> </tr>
                    </table>
                </div>  
                <h4> Stewards on board </h4>
                <div class="well">
                    <div class="table-responsive">
                        <table class="table table-striped table-hover listTable">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>First name</th>
                                    <th>Last name</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${flight.stewards}" var="steward">
                                    <tr class="listTable" onclick="location.href = '${pageContext.request.contextPath}/stewards/detail/${steward.id}';">
                                        <td><c:out value="${steward.id}"/></td>
                                        <td><c:out value="${steward.firstName}"/></td>
                                        <td><c:out value="${steward.lastName}"/></td>
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
