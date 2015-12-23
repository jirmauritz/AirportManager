<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<tags:pagetemplate>

    <jsp:attribute name="body">

        <h1 class="page-header">Destination</h1>

        <div class="table-responsive">
            <table class="table table-striped table-hover listTable">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>City</th>
                    <th>Country</th>
                </tr>
                </thead>
                <tbody>
                <!-- Output every destination in destinations variable -->
                <c:forEach items="${destinations}" var="destination">
                    <tr class="listTable">
                        <td onclick="location.href = '${pageContext.request.contextPath}/destinations/detail/${destination.id}';">
                            <c:out value="${destination.id}"/></td>
                        <td onclick="location.href = '${pageContext.request.contextPath}/destinations/detail/${destination.id}';">
                            <c:out value="${destination.name}"/></td>
                        <td onclick="location.href = '${pageContext.request.contextPath}/destinations/detail/${destination.id}';">
                            <c:out value="${destination.city}"/></td>
                        <td onclick="location.href = '${pageContext.request.contextPath}/destinations/detail/${destination.id}';">
                            <c:out value="${destination.country}"/></td>
                        <sec:authorize ifAllGranted="ROLE_airport">
                        <td align="right">
                            <button type="button" class="btn btn-info btn-xs" data-toggle="modal"
                                    data-target="#myModal_${destination.id}">
                                <span class="glyphicon glyphicon-remove"></span>
                            </button>
                        </td>
                    </sec:authorize>
                    </tr>
                    <sec:authorize access="hasRole('ROLE_flight')">
                        <div id="myModal_${destination.id}" class="modal fade" role="dialog">
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
                                        <a href="${pageContext.request.contextPath}/destinations/delete/${destination.id}"
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
