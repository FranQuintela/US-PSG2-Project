<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="donations">
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#date").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2><c:if test="${donation['new']}"><fmt:message key="new2"/> </c:if><fmt:message key="donation"/></h2>

        <form:form modelAttribute="donation" class="form-horizontal">
            <div class="form-group has-feedback">
                    <fmt:message var="amount" key="amount"/>
                    <fmt:message var="client" key="client"/>
                <petclinic:inputField label="${amount}" name="amount"/>
                <petclinic:inputField label="${client}" name="client"/>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default" type="submit"><fmt:message key="createDonation"/></button>
                </div>
            </div>
        </form:form>

    </jsp:body>

</petclinic:layout>
