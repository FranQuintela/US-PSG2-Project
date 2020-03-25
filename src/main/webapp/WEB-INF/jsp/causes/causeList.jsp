<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="causes">
    <h2><fmt:message key="causes"/></h2>

    <table id="causesTable" class="table table-striped">
        <thead>
        <tr>
            <th><fmt:message key="name"/></th>
            <th><fmt:message key="description"/></th>
            <th><fmt:message key="achieved"/></th>
            <th><fmt:message key="target"/></th>
            <th><fmt:message key="organization"/></th>
        </tr>
        </thead>
        <tbody>
        
        </tbody>
    </table>
    <table><tr><td><a class="btn btn-default" href="/causes/new"><fmt:message key="addcauses"/></a></td></tr></table>

    <table class="table-buttons">
        <tr>
            <td>
                <a href="<spring:url value="/vets.xml" htmlEscape="true" />">
                <fmt:message key="xml"/>
                </a>
            </td>            
        </tr>
    </table>
</petclinic:layout>