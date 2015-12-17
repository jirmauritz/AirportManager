<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:pagetemplate>

    <jsp:attribute name="body">

        <c:if test="${not empty error}">
        <div class="alert alert-danger" role="alert">
            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
            
            <strong>Error: </strong><c:out value="${error}"/>
        </div>
        </c:if>
              
        <c:if test="${not empty warning}">
        <div class="alert alert-warning" role="alert">
            <span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>
            <strong>Warning: </strong><c:out value="${warning}"/>
        </div>
        </c:if>
        
        <c:if test="${not empty success}">
        <div class="alert alert-success" role="alert">
            <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
            <span class="sr-only">Success:</span>
            <strong>Success: </strong><c:out value="${success}"/>
        </div>
        </c:if>

</jsp:attribute>
</tags:pagetemplate>
