<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="owners">
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#date").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2><c:if test="${booking['new']}"><fmt:message key="new2"/> </c:if><fmt:message key="booking"/></h2>

        <b><fmt:message key="pet"/></b>
        <table class="table table-striped">
            <thead>
            <tr>
                <th><fmt:message key="name"/></th>
                <th><fmt:message key="birthDate"/></th>
                <th><fmt:message key="type"/></th>
                <th><fmt:message key="owner"/></th>
            </tr>
            </thead>
            <tr>
                <td><c:out value="${booking.pet.name}"/></td>
                <td><petclinic:localDate date="${booking.pet.birthDate}" pattern="yyyy/MM/dd"/></td>
                <td><c:out value="${booking.pet.type.name}"/></td>
                <td><c:out value="${booking.pet.owner.firstName} ${booking.pet.owner.lastName}"/></td>
            </tr>
        </table>

        <form:form modelAttribute="booking" class="form-horizontal">
            <div class="form-group has-feedback">
                    <fmt:message var="date" key="entry_date"/>
                    <fmt:message var="exit_date" key="exit_date"/>
            		<fmt:message var="details" key="details"/>
                <petclinic:inputField label="${date}" name="date"/>
                <petclinic:inputField label="${exit_date}" name="exit_date"/>
                <petclinic:inputField label="${details}" name="details"/>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="petId" value="${booking.pet.id}"/>
                    <button class="btn btn-default" type="submit"><fmt:message key="addBooking"/></button>
                </div>
            </div>
        </form:form>

        <br/>
        <b><fmt:message key="previousBooking"/></b>
        <table class="table table-striped">
            <tr class="brown">
                <th><fmt:message key="bookingDate"/></th>
                <th><fmt:message key="exit_date"/></th>
                <th><fmt:message key="details"/></th>
            </tr>
            <c:forEach var="booking" items="${booking.pet.bookings}">
                <c:if test="${!booking['new']}">
                    <tr>
                        <td><petclinic:localDate date="${booking.date}" pattern="yyyy/MM/dd"/></td>
                        <td><petclinic:localDate date="${booking.exit_date}" pattern="yyyy/MM/dd"/></td>
                        <td><c:out value="${booking.details}"/></td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </jsp:body>

</petclinic:layout>
