/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.samples.petclinic.model.Booking;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * <code>Validator</code> for <code>Pet</code> forms.
 * <p>
 * We're not using Bean Validation annotations here because it is easier to define such
 * validation rule in Java.
 * </p>
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 */
public class BookingValidator implements Validator {

	@Override
	public void validate(final Object obj, final Errors errors) {

		Booking booking = (Booking) obj;
		Boolean solape = false;
		LocalDate fecha = booking.getDate();
		Boolean anterior = false;
		Boolean estaContenida = false;
		List<Booking> bookings = new ArrayList<>();
		bookings = booking.getPet().getBookings();

		for (Booking b : bookings) {
			if (!b.equals(booking)) {
				if (b.getExitDate().isAfter(fecha)) {
					anterior = booking.getExitDate().isBefore(b.getDate()) || booking.getExitDate().isEqual(b.getDate());
					solape = !anterior && fecha.isBefore(b.getDate()) && (booking.getExitDate().isAfter(b.getDate()) || !booking.getExitDate().isEqual(b.getDate())) || fecha.isEqual(b.getDate());
					estaContenida = fecha.isAfter(b.getDate()) && fecha.isBefore(b.getExitDate());

					if (solape || estaContenida) {
						break;
					}
				}
			}

		}
		// Fechas no solapadas
		if (solape || estaContenida) {
			errors.rejectValue("date", "The date coincides with some other date already taken", "The date coincides with some other date already taken");
		}
		//fecha salida anterior a la fecha de entrada
		if (booking.getExitDate().isBefore(fecha)) {
			errors.rejectValue("exit_date", "The arrival date must be prior to the departure date", "The arrival date must be prior to the departure date");
		}
		//fecha menos de un día
		if (booking.getExitDate().isEqual(fecha)) {
			errors.rejectValue("exit_date", "The booking must be of at least one night", "The booking must be of at least one night");
		}
		//detalles vacios
		if (booking.getDetails().isEmpty()) {
			errors.rejectValue("details", "This field cannot be empty", "This field cannot be empty");
		}
		//fecha de entrada en pasado
		if (fecha.isBefore(LocalDate.now())) {
			errors.rejectValue("date", "The enter date cannot be in the past", "The enter date cannot be in the past");
		}

	}

	/**
	 * This Validator validates *just* Pet instances
	 */
	@Override
	public boolean supports(final Class<?> clazz) {
		return Booking.class.isAssignableFrom(clazz);
	}

}
