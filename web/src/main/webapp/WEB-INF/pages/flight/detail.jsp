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
                    <c:choose>
                        <c:when test="${not empty flight.airplane}">
                            <table class="table table-condensed">
                                <tr> <td> id: </td>  <td> <c:out value="${flight.airplane.id}"/> </td></tr>
                                <tr> <td> name: </td> <td><c:out value="${flight.airplane.name}"/> </td> </tr>
                                <tr> <td> type: </td> <td><c:out value="${flight.airplane.type}"/> </td> </tr>
                                <tr> <td> capacity: </td> <td><c:out value="${flight.airplane.capacity}"/> </td> </tr>
                            </table>
                            <button type="button" class="btn btn-info btn-xs" data-toggle="modal"
                                    data-target="#removeAirplaneModal_${flight.id}">
                                <span class="btn">Remove airplane </span>
                            </button>

                            <div id="removeAirplaneModal_${flight.id}" class="modal fade" role="dialog">
                                <div class="modal-dialog">

                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            <h4 class="modal-title">Remove Airplane from Flight</h4>
                                        </div>
                                        <div class="modal-body">
                                            <p>Are you sure you want to remove airplane from the flight?</p>
                                        </div>
                                        <div class="modal-footer">
                                            <form method="POST" action="${pageContext.request.contextPath}/flights/${flight.id}/removeAirplane">
                                                <input type="submit" value="OK" class="btn btn-default" onclick="resoudre(this)"/>
                                            </form>
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                        </div>
                                    </div>

                                </div>
                            </div>


                        </c:when>
                        <c:otherwise>
                            <p> No airplane is assigned to this flight. </p>
                            <hr/>
                            <h5> <b> Available airplanes </b> </h5>
                            <c:choose>
                                <c:when test="${not empty availableAirplanes}">
                                    <table class="table table-condensed listTable">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>name</th>
                                                <th>type</th>
                                                <th>capacity</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${availableAirplanes}" var="airplane">
                                                <tr class="listTable">
                                                    <td onclick="location.href = '${pageContext.request.contextPath}/airplanes/detail/${aiplane.id}';">
                                                        <c:out value="${airplane.id}"/>
                                                    </td>
                                                    <td onclick="location.href = '${pageContext.request.contextPath}/airplanes/detail/${aiplane.id}';">
                                                        <c:out value="${airplane.name}"/>
                                                    </td>
                                                    <td onclick="location.href = '${pageContext.request.contextPath}/airplanes/detail/${aiplane.id}';">
                                                        <c:out value="${airplane.type}"/>
                                                    </td>
                                                    <td onclick="location.href = '${pageContext.request.contextPath}/airplanes/detail/${aiplane.id}';">
                                                        <c:out value="${airplane.capacity}"/>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-info btn-xs" data-toggle="modal"
                                                                data-target="#addAirplaneModal_${flight.id}_${airplane.id}">
                                                            <span class="glyphicon glyphicon-plus"></span>
                                                        </button>
                                                    </td>
                                                </tr>
                                            <div id="addAirplaneModal_${flight.id}_${airplane.id}" class="modal fade" role="dialog">
                                                <div class="modal-dialog">

                                                    <!-- Modal content-->
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                            <h4 class="modal-title">Add Airplane to Flight</h4>
                                                        </div>
                                                        <div class="modal-body">
                                                            <p>Are you sure you want to add this airplane to the flight?</p>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <form method="POST" action="${pageContext.request.contextPath}/flights/${flight.id}/addAirplane/${airplane.id}">
                                                                <input type="submit" value="OK" class="btn btn-default" onclick="resoudre(this)"/>
                                                            </form>
                                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </c:when>
                                <c:otherwise>
                                    <p> No airplane is available at the time of flight </p>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
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
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${flight.stewards}" var="steward">
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
                                        <td>
                                            <button type="button" class="btn btn-info btn-xs" data-toggle="modal"
                                                    data-target="#removeStewardModal_${flight.id}_${steward.id}">
                                                <span class="glyphicon glyphicon-minus"></span>
                                            </button>
                                        </td>
                                    </tr>
                                <div id="removeStewardModal_${flight.id}_${steward.id}" class="modal fade" role="dialog">
                                    <div class="modal-dialog">

                                        <!-- Modal content-->
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                <h4 class="modal-title">Remove Steward from Flight</h4>
                                            </div>
                                            <div class="modal-body">
                                                <p>Are you sure you want to remove this steward from the flight?</p>
                                            </div>
                                            <div class="modal-footer">
                                                <form method="POST" action="${pageContext.request.contextPath}/flights/${flight.id}/removeSteward/${steward.id}">
                                                    <input type="submit" value="OK" class="btn btn-default" onclick="resoudre(this)"/>
                                                </form>
                                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <hr/>
                    <h5> <b> Other available Stewards </b> </h5>
                    <div class="table-responsive">
                        <table class="table table-striped table-hover listTable">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>First name</th>
                                    <th>Last name</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${availableStewards}" var="steward">
                                    <tr class="listTable">
                                        <td onclick="location.href = '${pageContext.request.contextPath}/steward/detail/${steward.id}';">
                                            <c:out value="${steward.id}"/>
                                        </td>
                                        <td onclick="location.href = '${pageContext.request.contextPath}/steward/detail/${steward.id}';">
                                            <c:out value="${steward.firstName}"/>
                                        </td>
                                        <td onclick="location.href = '${pageContext.request.contextPath}/steward/detail/${steward.id}';">
                                            <c:out value="${steward.lastName}"/>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-info btn-xs" data-toggle="modal"
                                                    data-target="#addStewardModal_${flight.id}_${steward.id}">
                                                <span class="glyphicon glyphicon-plus"></span>
                                            </button>
                                        </td>
                                    </tr>
                                <div id="addStewardModal_${flight.id}_${steward.id}" class="modal fade" role="dialog">
                                    <div class="modal-dialog">

                                        <!-- Modal content-->
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                <h4 class="modal-title">Add Steward to Flight</h4>
                                            </div>
                                            <div class="modal-body">
                                                <p>Are you sure you want to add this steward to the flight?</p>
                                            </div>
                                            <div class="modal-footer">
                                                <form method="POST" action="${pageContext.request.contextPath}/flights/${flight.id}/addSteward/${steward.id}">
                                                    <input type="submit" value="OK" class="btn btn-default" onclick="resoudre(this)"/>
                                                </form>
                                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>


                </div>
            </div>
        </div>


    </jsp:attribute>
</tags:pagetemplate>
