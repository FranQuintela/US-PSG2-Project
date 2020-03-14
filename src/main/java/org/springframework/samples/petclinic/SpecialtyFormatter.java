
package org.springframework.samples.petclinic;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Component;

@Component
public class SpecialtyFormatter implements Formatter<Specialty> {

	private final ClinicService clinicService;


	@Autowired
	public SpecialtyFormatter(final ClinicService clinicService) {
		this.clinicService = clinicService;
	}

	@Override
	public String print(final Specialty specialty, final Locale locale) {
		return specialty.getName();
	}

	@Override
	public Specialty parse(final String text, final Locale locale) throws ParseException {
		Collection<Specialty> findSpecialties = this.clinicService.findSpecialties();
		for (Specialty type : findSpecialties) {
			if (type.getName().equals(text)) {
				return type;
			}
		}
		throw new ParseException("Specialty not found: " + text, 0);
	}

}
