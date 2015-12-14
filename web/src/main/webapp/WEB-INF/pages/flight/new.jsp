<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:pagetemplate>

    <jsp:attribute name="body">

        <form:form method="post" action="${pageContext.request.contextPath}/flights/create" modelAttribute="flightToCreate">
            <h1 class="page-header">New flight </h1>

            <div class="row">
                <div class="col-sm-5">
                    <h4> International </h4>
                    <div class="well">
                        <form:radiobutton path="international" value="false"/> no
                        <spring:message code="label.roundYes"/>
                        <br>
                        <form:radiobutton path="international"  value="true"/> yes
                        <spring:message code="label.roundNo"/>
                    </div>   
                    <h4> Departure </h4>
                    <div class="well">
                        <form:input size="30" path="departure" type="datetime-local"/>
                    </div>    
                    <h4> Arrival </h4>
                    <div class="well">
                        <form:input size="30" path="arrival" type="datetime-local"/>
                    </div>  
                    <h4> From </h4>
                    <div class="well">
                        <form:label path="from" ></form:label>
                        <form:select path="from">
                            <c:forEach items="${destinations}" var="d">
                                <form:option value="${d}">${d}</form:option>
                            </c:forEach>
                        </form:select>
                        <form:errors path="from" cssClass="error"/>

                    </div>  
                    <h4> To </h4>
                    <div class="well">
                        <form:label path="to" ></form:label>
                        <form:select path="to">
                            <c:forEach items="${destinations}" var="d">
                                <form:option value="${d}">${d}</form:option>
                            </c:forEach>
                        </form:select>
                        <form:errors path="to" cssClass="error"/>

                    </div>  
                </div>
                <div class="col-sm-7">
                    <h4> Airplane </h4>
                    <div class="well">
                        <form:label path="airplane" ></form:label>
                        <form:select path="airplane">
                            <c:forEach items="${airplanes}" var="a">
                                <form:option value="${a}">${a}</form:option>
                            </c:forEach>
                        </form:select>
                        <form:errors path="airplane" cssClass="error"/>
                    </div>
                </div>
            </div>

            <button class="btn btn-primary" type="submit">Create flight</button>
        </form:form>


    </jsp:attribute>
</tags:pagetemplate>
