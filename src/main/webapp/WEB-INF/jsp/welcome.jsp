<%@ page session="false" trimDirectiveWhitespaces="true" %> <%@ taglib
prefix="spring" uri="http://www.springframework.org/tags" %> <%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <%@ taglib
prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="home">
  <div class="center">
        <h1 class="strong"><fmt:message key="welcome"/></h1>

        <div class="col-md-12">
            <spring:url value="/resources/images/logo-line.png" htmlEscape="true" var="petsImage"/>
            <img class="img-responsive" src="${petsImage}"/>
        </div>
    </div>


</petclinic:layout>
