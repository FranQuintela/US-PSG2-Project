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

import org.springframework.samples.petclinic.model.Visit;
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
public class VisitValidator implements Validator {

	@Override
	public void validate(final Object obj, final Errors errors) {

		Visit visit = (Visit) obj;

		//fecha de entrada en pasado
		if (visit.getDate().isBefore(LocalDate.now())) {
			errors.rejectValue("date", "The enter date cannot be in the past", "The enter date cannot be in the past");
		}

	}

	/**
	 * This Validator validates *just* Pet instances
	 */
	@Override
	public boolean supports(final Class<?> clazz) {
		return Visit.class.isAssignableFrom(clazz);
	}

}
