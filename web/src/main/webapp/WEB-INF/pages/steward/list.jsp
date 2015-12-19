<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:pagetemplate>

    <jsp:attribute name="body">

        <h1 class="page-header">Stewards</h1>

        <div class="table-responsive">
            <table class="table table-striped table-hover listTable">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Output every flight in flights variable -->
                    <c:forEach items="${stewards}" var="steward">
                        <tr class="listTable" onclick="location.href = '${pageContext.request.contextPath}/stewards/detail/${steward.id}';">
                            <td><c:out value="${steward.id}"/></td>
                            <td><c:out value="${steward.firstName}"/></td>
                            <td><c:out value="${steward.lastName}"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

</jsp:attribute>
</tags:pagetemplate>
