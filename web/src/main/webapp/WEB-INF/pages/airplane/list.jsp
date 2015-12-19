<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<tags:pagetemplate>

    <jsp:attribute name="body">

        <h1 class="page-header">Airplanes</h1>

        <div class="table-responsive">
            <table class="table table-striped table-hover listTable">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <th>Type</th>
                        <th>Capacity</th>
                            <sec:authorize ifAllGranted="ROLE_airport">
                            <th></th>
                            </sec:authorize>
                    </tr>
                </thead>
                <tbody>
                    <!-- Output all airplanes -->
                    <c:forEach items="${airplanes}" var="airplane">
                        <tr>
                            <td><c:out value="${airplane.id}"/></td>
                            <td><c:out value="${airplane.name}"/></td>
                            <td><c:out value="${airplane.type}"/></td>
                            <td><c:out value="${airplane.capacity}"/></td>
                            <sec:authorize ifAllGranted="ROLE_airport">
                                <td align="right">
                                    <button type="button" class="btn btn-info btn-xs" data-toggle="modal" data-target="#myModal">
                                        <span class="glyphicon glyphicon-remove"/>
                                    </button>

                                </sec:authorize>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <sec:authorize ifAllGranted="ROLE_airport">
        <div id="myModal" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Delete Airplane</h4>
                    </div>
                    <div class="modal-body">
                        <p>Are you sure you want to delete this airplane?</p>
                    </div>
                    <div class="modal-footer">
                        <a href="${pageContext.request.contextPath}/airplanes/delete/${airplane.id}"
                           type="button" class="btn btn-default"
                           onclick="resoudre(this)"
                           > OK</a>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>
    </sec:authorize>

</jsp:attribute>
</tags:pagetemplate>
