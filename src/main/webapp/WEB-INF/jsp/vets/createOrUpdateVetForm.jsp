<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vets">

    <jsp:body>


        <form:form modelAttribute="vet"
                   class="form-horizontal"
                    action="/vets/save/">
            <input type="hidden" name="id" value="${vet.id}"/>
            <div class="form-group has-feedback">
     

                <fmt:message var="firstName" key="firstName"/>
                <fmt:message var="lastName" key="lastName"/>
	<fmt:message var="specialties" key="specialties"/>
                <petclinic:inputField label="${firstName}" name="firstName"/>
                <petclinic:inputField label="${lastName}" name="lastName"/>
                
               <div class="control-group">
                    <petclinic:selectField name="specialties" label="${specialties}" names="${specialities}" size="6"/>
                </div>
                
              	
                
    
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${vet['new']}">
                            <button class="btn btn-default" type="submit"><fmt:message key="addVet"/></button>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-default" type="submit"><fmt:message key="updatePet"/></button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form:form>

    </jsp:body>
</petclinic:layout>
