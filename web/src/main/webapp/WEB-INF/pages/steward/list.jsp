<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
                        <sec:authorize access="hasRole('ROLE_airport')"><th></th></sec:authorize>
                    </tr>
                </thead>
                <tbody>
                    <!-- Output every flight in flights variable -->
                    <c:forEach items="${stewards}" var="steward">
                        <tr class="listTable">
                            <td onclick="location.href = '${pageContext.request.contextPath}/stewards/detail/${steward.id}';">
                                <c:out value="${steward.id}"/>
                            </td>
                            <td onclick="location.href = '${pageContext.request.contextPath}/stewards/detail/${steward.id}';">
                                <c:out value="${steward.firstName}"/>
                            </td>
                            <td onclick="location.href = '${pageContext.request.contextPath}/stewards/detail/${steward.id}';">
                                <c:out value="${steward.lastName}"/>
                            </td>
                            <sec:authorize access="hasRole('ROLE_airport')">
                                <td align="right"><button type="button" class="btn btn-info btn-xs" data-toggle="modal"
                                                          data-target="#stewardDeleting_${steward.id}">
                                        <span class="glyphicon glyphicon-remove"></span>
                                </button></td>
                            </sec:authorize>
                        </tr>
                        <sec:authorize access="hasRole('ROLE_airport')">
                            <div id="stewardDeleting_${steward.id}" class="modal fade" role="dialog">
                                <div class="modal-dialog">

                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            <h4 class="modal-title">Delete Steward</h4>
                                        </div>
                                        <div class="modal-body">
                                            <p>Are you sure you want to delete this steward?</p>
                                        </div>
                                        <div class="modal-footer">
                                            <form method="POST" action="${pageContext.request.contextPath}/stewards/delete/${steward.id}">
                                                <input value="OK" type="submit" class="btn btn-default" onclick="resoudre(this)"/>
                                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                            </form>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </sec:authorize>
                    </c:forEach>
                </tbody>
            </table>
        </div>

</jsp:attribute>
</tags:pagetemplate>
