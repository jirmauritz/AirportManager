<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<tags:pagetemplate>

    <jsp:attribute name="body">

        <h1 class="page-header">
            Airplane
            <sec:authorize ifAllGranted="ROLE_airport">
                <a href="${pageContext.request.contextPath}/airplanes/updating/${airplane.id}"
                   class="btn btn-primary btn-sm navbut" role="button">Edit</a>
                <a class="btn btn-primary btn-sm navbut" role="button"
                   data-toggle="modal" data-target="#airplaneToDelete">Delete</a>
            </sec:authorize>
        </h1>


        <div class="row">
            <div class="col-sm-5">
                <h4> Airplane Name </h4>
                <div class="well">
                    <c:out value="${airplane.name}"/>
                </div>
                <h4> Airplane Type </h4>
                <div class="well">
                    <c:out value="${airplane.type}"/>
                </div>
                <h4> Airplane Capacity </h4>
                <div class="well">
                    <c:out value="${airplane.capacity}"/>
                </div>
            </div>
        </div>

        <sec:authorize ifAllGranted="ROLE_airport">
            <div id="airplaneToDelete" class="modal fade" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Delete Airplane</h4>
                        </div>
                        <div class="modal-body">
                            <p>Are you sure you want to delete this airplane?</p>
                        </div>
                        <div class="modal-footer">
                            <form method="POST" action="${pageContext.request.contextPath}/airplanes/delete/${airplane.id}">
                                <input value="OK" type="submit" class="btn btn-default" onclick="resoudre(this)"/>
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            </form>
                        </div>
                    </div>

                </div>
        </sec:authorize>
    </div>

</jsp:attribute>
</tags:pagetemplate>
