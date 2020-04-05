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
            <th><fmt:message key="donate"/></th>
        </tr>
        </thead>
        <tbody>
         <c:forEach items="${causes}" var="cause">
            <tr>
                <td>                
                    <spring:url value="/causes/{causeId}" var="causeUrl">
                        <spring:param name="causeId" value="${cause.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(causeUrl)}"><c:out value="${cause.name} "/></a>
                </td>
                <td>
                   <c:out value="${cause.description} "/>
                </td>
                 <td>
                   <c:out value="${cause.amountAchieved} "/>
                </td>
                 <td>
                   <c:out value="${cause.budgetTarget} "/>
                </td>
                 <td>
                   <c:out value="${cause.organization} "/>
                </td>
                <td>                
                    <spring:url value="/causes/{causeId}/donations/create" var="danationUrl">
                        <spring:param name="causeId" value="${cause.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(danationUrl)}"><fmt:message key="donate"/></a>
                </td>
            </tr>
        </c:forEach>
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