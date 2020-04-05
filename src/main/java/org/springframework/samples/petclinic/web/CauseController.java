package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
public class CauseController {

	private final ClinicService clinicService;


	@Autowired
	public CauseController(final ClinicService clinicService) {
		this.clinicService = clinicService;
	}

	@GetMapping(value = {
		"/causes"
	})
	public String showCauseList(final Map<String, Object> model) {

		model.put("causes", this.clinicService.findCauses());
		return "causes/causeList";
	}

	/**
	 * Custom handler for displaying an cause.
	 * @param causeId the ID of the cause to display
	 * @return a ModelMap with the model attributes for the view
	 */
	@GetMapping("/causes/{causeId}")
	public ModelAndView showCause(@PathVariable("causeId") int causeId, final Map<String, Object> model) {
		Collection<Donation> donations;
    	donations = this.clinicService.findDonations(causeId);
        model.put("donations", donations);
		ModelAndView mav = new ModelAndView("causes/causeDetails");
		mav.addObject(this.clinicService.findCauseById(causeId));
		return mav;
	}
}
