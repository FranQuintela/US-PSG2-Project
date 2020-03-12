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

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
public class VetController {

	private final ClinicService clinicService;


	@Autowired
	public VetController(final ClinicService clinicService) {
		this.clinicService = clinicService;
	}

	@GetMapping(value = {
		"/vets"
	})
	public String showVetList(final Map<String, Object> model) {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for Object-Xml mapping
		Vets vets = new Vets();
		vets.getVetList().addAll(this.clinicService.findVets());
		model.put("vets", vets);
		return "vets/vetList";
	}

	@GetMapping(value = {
		"/vets.xml"
	})
	public @ResponseBody Vets showResourcesVetList() {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for JSon/Object mapping
		Vets vets = new Vets();
		vets.getVetList().addAll(this.clinicService.findVets());
		return vets;
	}

	@ModelAttribute("specialities")
	public List<Specialty> populatePetTypes() {
		return this.clinicService.findSpecialties();
	}

	@GetMapping(path = "vets/new")
	public String crearVeterinarian(final ModelMap modelMap) {
		String view = "vets/createOrUpdateVetForm";
		modelMap.addAttribute("vet", new Vet());
		return view;

	}

	@PostMapping(path = "vets/save")
	public String salvarVeterinarian(@Valid final Vet vet, final BindingResult result, final ModelMap modelMap) {

		String view = "/vets";

		if (result.hasErrors()) {
			modelMap.addAttribute("vet", vet);
			modelMap.addAttribute("specialties", vet.getSpecialties());
			return "vets/createOrUpdateVetForm";
		} else {
			for (Specialty s : vet.getSpecialties()) {
				this.clinicService.saveSpecialty(s);
			}

			this.clinicService.saveVet(vet);
			modelMap.addAttribute("message", "vet successfully saved");
			view = this.showVetList(modelMap);
		}
		return view;

	}

	@GetMapping(value = "/vets/{vetId}/edit")
	public String initUpdateForm(@PathVariable("vetId") int vetId, ModelMap model){
		//Initiation of an Update Form for a Vet Object
		Vet vet = this.clinicService.findVetById(vetId);
		model.addAttribute(vet);
		return "vets/createOrUpdateVetForm";
	}

	@PostMapping(value = "/vets/{vetId}/edit")
	public String processUpdateForm(@Valid Vet vet, BindingResult result, @PathVariable("vetId") int vetId, ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("vet", vet);
			model.addAttribute("specialties", vet.getSpecialties());	
			return "vets/createOrUpdateVetForm";
			}
		else {
			vet.setId(vetId);
			this.clinicService.saveVet(vet);
			return "redirect:/vets";
		}
	}

	
	
	/**
	 * Custom handler for displaying an owner.
	 * @param ownerId the ID of the owner to display
	 * @return a ModelMap with the model attributes for the view
	 */
	@GetMapping("/vets/{vetId}")
	public ModelAndView showOwner(@PathVariable("vetId") int vetId) {
		ModelAndView mav = new ModelAndView("vets/vetDetails");
		mav.addObject(this.clinicService.findVetById(vetId));
		return mav;
	}

	@RequestMapping(value = "/vets/{vetId}/delete", method = RequestMethod.GET)
	public String deleteVet(Model model, @PathVariable("vetId") int vetId){
		this.clinicService.deleteVet(vetId);
		return "redirect:/vets";
	}

}
