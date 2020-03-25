<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="causes">

    <h2><fmt:message key="causeInfo"/></h2>

    <table class="table table-striped">
      
       <thead>  <tr>
            <th><fmt:message key="name"/></th>
            <th><fmt:message key="description"/></th>
            <th><fmt:message key="achieved"/></th>
            <th><fmt:message key="target"/></th>
            <th><fmt:message key="organization"/></th>
        </tr></thead>
        <tbody>
            <tr>
                <td>                
                   <c:out value="${cause.name} "/>
                </td>
                <td>
                   <c:out value="${cause.description} "/>
                </td>
                 <td>
                    <c:out value="Here should be amount achieved"/>
                   <%-- <c:out value="${cause.achieved} "/> --%>
                </td>
                 <td>
                   <c:out value="${cause.budgetTarget} "/>
                </td>
                 <td>
                   <c:out value="${cause.organization} "/>
                </td>
            </tr>
        </tbody>
    </table>

     <h2><fmt:message key="Donations"/></h2>

    <table id="donationsTable" class="table table-striped">
        <thead>
        <tr>
            <th><fmt:message key="date"/></th>
            <th><fmt:message key="amount"/></th>
            <th><fmt:message key="client"/></th>
        </tr>
        </thead>
        <tbody>
         <%-- <c:forEach items="${donations}" var="donation"> --%>
            <tr>
                <td>
                    <c:out value="Here should be date"/>
                </td>
                <td>
                    <c:out value="Here should be amount"/>
                   <%-- <c:out value="${cause.achieved} "/> --%>
                </td>
                 <td>
                    <c:out value="Here should be client"/>
                </td>

            </tr>
        <%-- </c:forEach> --%>
        </tbody>
    </table>
    
    <spring:url value="{causeId}/delete" var="deleteUrl">
        <spring:param name="causeId" value="${cause.id}"/>
    </spring:url>
    	<a class="btn btn-default" href="/causes/${cause.id}/edit"><fmt:message key="updateCause"/></a>
    <a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default"><fmt:message key="deleteCause"/></a>

</petclinic:layout>
