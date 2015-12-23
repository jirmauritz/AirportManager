<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:pagetemplate>

    <jsp:attribute name="body">

        <form:form method="post" action="${pageContext.request.contextPath}/destinations/create"
                   modelAttribute="destinationToCreate">
            <h1 class="page-header">New destination</h1>

            <div class="row">
                <div class="col-sm-5">
                    <h4>Name</h4>
                    <div class="well">
                        <form:input size="30" path="name" type="text"/>
                    </div>
                    <h4>City</h4>
                    <div class="well">
                        <form:input size="30" path="city" type="text"/>
                    </div>
                    <h4>Country</h4>
                    <div class="well">
                        <form:input size="30" path="country" type="text"/>
                    </div>
                </div>
            </div>

            <button class="btn btn-primary" type="submit">Create destination</button>
        </form:form>
    </jsp:attribute>
</tags:pagetemplate>
