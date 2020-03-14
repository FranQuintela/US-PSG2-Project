<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vets">

    <h2><fmt:message key="vetInfo"/></h2>

    <table class="table table-striped">
      
       <thead>  <tr>
            <th><fmt:message key="firstName"/></th>
             <th><fmt:message key="specialities"/></th>
        </tr></thead>
        <tr>
            <td><c:out value="${vet.firstName} ${vet.lastName}"/></td>
          
            <td>
                    <c:forEach var="specialty" items="${vet.specialties}">
                        <c:out value="${specialty.name} "/>
                    </c:forEach>
                    <c:if test="${vet.nrOfSpecialties == 0}">none</c:if>
                </td>
        </tr>
    </table>
    
    <spring:url value="{vetId}/delete" var="deleteUrl">
        <spring:param name="vetId" value="${vet.id}"/>
    </spring:url>
    	<a class="btn btn-default" href="/vets/${vet.id}/edit"><fmt:message key="updateVet"/></a>
    <a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default"><fmt:message key="deleteVet"/></a>

</petclinic:layout>
