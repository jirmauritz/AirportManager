<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:pagetemplate>

    <jsp:attribute name="body">

        <form:form method="post" modelAttribute="stewardDTO"
                   action="${pageContext.request.contextPath}/stewards/${empty stewardId ? 'create' : 'update/'}${empty stewardId ? '' : stewardId}">
            <h1 class="page-header">New steward</h1>

            <div class="row">
                <div class="col-sm-5">
                    <h4>First Name</h4>
                    <div class="well">
                        <form:input size="30" path="firstName" type="text"/>
                    </div>    
                    <h4>Last Name</h4>
                    <div class="well">
                        <form:input size="30" path="lastName" type="text"/>
                    </div>
                </div>
            </div>

            <button class="btn btn-primary" type="submit">${empty stewardId ? 'Create steward' : 'Update steward '}${empty stewardId ? '' : stewardId}</button>
        </form:form>


    </jsp:attribute>
</tags:pagetemplate>
