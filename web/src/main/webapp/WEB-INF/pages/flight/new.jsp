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
                <div class="col-sm-6">
                    <h4> Departure </h4>
                    <div class="well">
                        <form:input id="departureDateTimePicker" path="departure"/>
                    </div>    

                    <h4> From </h4>
                    <div class="well">
                        <form:label path="from"/>
                        <form:select path="from" class="selectpicker" data-live-search="true" data-container="body" data-width="fit">
                            <c:forEach items="${destinations}" var="d">
                                <form:option value="${d}">${d}</form:option>
                            </c:forEach>
                        </form:select>
                        <form:errors path="from" cssClass="error"/>

                    </div>  
                    <h4> International </h4>
                    <div class="well">
                        <form:radiobutton path="international" value="false"/> no
                        <spring:message code="label.roundYes"/>
                        <br>
                        <form:radiobutton path="international"  value="true"/> yes
                        <spring:message code="label.roundNo"/>
                    </div>   
                </div>
                <div class="col-sm-6">
                    <h4> Arrival </h4>
                    <div class="well">
                        <form:input id="arrivalDateTimePicker" path="arrival"/>
                    </div>  

                    <h4> To </h4>
                    <div class="well">
                        <form:label path="to" ></form:label>
                        <form:select path="to" class="selectpicker" data-live-search="true" data-container="body" data-width="fit">
                            <c:forEach items="${destinations}" var="d">
                                <form:option value="${d}">${d}</form:option>
                            </c:forEach>
                        </form:select>
                        <form:errors path="to" cssClass="error"/>
                    </div>  
                </div>
            </div>

            <button class="btn btn-primary" type="submit">Create flight</button>
        </form:form>


    </jsp:attribute>
</tags:pagetemplate>
