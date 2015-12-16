<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                    </tr>
                </thead>
                <tbody>
                    <!-- Output all airplanes -->
                    <c:forEach items="${airplanes}" var="airplane">
                        <tr class="listTable">
                            <td><c:out value="${airplane.id}"/></td>
                            <td><c:out value="${airplane.name}"/></td>
                            <td><c:out value="${airplane.type}"/></td>
                            <td><c:out value="${airplane.capacity}"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <req:isUserInRole role="flight">
            Only sees Flight Manager
        </req:isUserInRole>
    </div>

</jsp:attribute>
</tags:pagetemplate>
