<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:pagetemplate>

    <jsp:attribute name="body">

        <form:form method="post" action="${pageContext.request.contextPath}/airplanes/update/${airplaneToUpdate.id}" modelAttribute="airplaneToUpdate">
            <h1 class="page-header">New airplane</h1>

            <div class="row">
                <div class="col-sm-5">
                    <h4>Name</h4>
                    <div class="well">
                        <form:input size="30" path="name" type="text"/> 
                    </div>    
                    <h4>Type</h4>
                    <div class="well">
                        <form:label path="type"></form:label>
                        <form:select path="type" class="selectpicker" data-live-search="true" data-size="10" data-width="fit">
                            <c:forEach items="${airplaneTypes}" var="t">
                                <form:option value="${t}">${t}</form:option>
                            </c:forEach>
                        </form:select>
                        <form:errors path="type" cssClass="error"/>
                    </div>  
                    <h4>Capacity</h4>
                    <div class="well">
                        <form:input size="30" path="capacity" type="number" min="0"/>
                    </div>  
                </div>
            </div>

            <button class="btn btn-primary" type="submit">Update</button>
        </form:form>


    </jsp:attribute>
</tags:pagetemplate>
