<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:pagetemplate>

    <jsp:attribute name="body">

        <h1 class="page-header">
            Destination
            <a href="${pageContext.request.contextPath}/destinations/updating/${destination.id}"
               class="btn btn-primary btn-sm navbut" role="button">Edit</a>
            <a class="btn btn-primary btn-sm navbut" role="button"
               data-toggle="modal" data-target="#destinationDeleting">Delete</a>
        </h1>

        <div class="row">
            <div class="col-sm-5">
                <h4>First Name</h4>
                <div class="well">
                    <c:out value="${destination.name}"/>
                </div>
                <h4>Last Name</h4>
                <div class="well">
                    <c:out value="${destination.city}"/>
                </div>
                <h4>Flights attending</h4>
                <div class="well">
                    <c:out value="${destination.country}"/>
                </div>
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
                        <a href="${pageContext.request.contextPath}/destinations/delete/${destination.id}"
                           type="button" class="btn btn-default"
                           onclick="resoudre(this)"> OK</a>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
</tags:pagetemplate>