package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
public class DonationController {

	private final ClinicService clinicService;

	@Autowired
	public DonationController(final ClinicService clinicService ) {
		this.clinicService = clinicService;
	}

	/**
	 * Called before each and every @GetMapping or @PostMapping annotated method. 2 goals:
	 * - Make sure we always have fresh data - Since we do not use the session scope, make
	 * sure that Pet object always has an id (Even though id is not part of the form
	 * fields)
	 * @param causeId
	 * @return Donation
	 */
	@ModelAttribute("donation")
	public Donation loadDonationToCause(@PathVariable("causeId") int causeId) {
		Donation donation = new Donation();
		donation.setCause(this.clinicService.findCauseById(causeId));
		return donation;
	}

	// Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is called
	@GetMapping(value = "/causes/{causeId}/donations/create")
	public String initNewDonationForm(@PathVariable("causeId") int causeId, Model model) {
		List<String> owners = this.clinicService.findOwners().stream().map(n -> n.getFirstName()+" "+n.getLastName()).collect(Collectors.toList());
		
		model.addAttribute("clients", owners);

		return "causes/donations/createOrUpdateDonationForm";
	}

	// Spring MVC calls method loadPetWithVisit(...) before processNewVisitForm is called
	@PostMapping(value = "/causes/{causeId}/donations/create")
	public String processNewCauseForm(@PathVariable("causeId") int causeId, @Valid Donation donation, BindingResult result, Model model) {
		Cause cause = this.clinicService.findCauseById(causeId);

		if(cause.getAmountAchieved() + donation.getAmount() > cause.getBudgetTarget()){
			result.rejectValue("amount", "error.amount", "You cant exceed the budget target of the cause");
		}

		if (result.hasErrors()) {
			List<String> owners = this.clinicService.findOwners().stream().map(n -> n.getFirstName()+" "+n.getLastName()).collect(Collectors.toList());	
			model.addAttribute("clients", owners);
			return "causes/donations/createOrUpdateDonationForm";
		}
		else {
			donation.setCause(cause);
			donation.setDate(LocalDate.now());

			this.clinicService.saveDonation(donation);
			return "redirect:/causes/{causeId}";
		}
	}
}
