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

package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.model.Booking;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.samples.petclinic.repository.VisitRepository;
import org.springframework.samples.petclinic.repository.BookingRepository;
import org.springframework.samples.petclinic.repository.CauseRepository;
import org.springframework.samples.petclinic.repository.DonationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class ClinicService {

	private PetRepository	petRepository;

	private VetRepository	vetRepository;

	private OwnerRepository	ownerRepository;

	private VisitRepository	visitRepository;

	private BookingRepository bookingRepository;

	private CauseRepository causeRepository;

	private DonationRepository donationRepository;

	@Autowired

	public ClinicService(PetRepository petRepository, VetRepository vetRepository, OwnerRepository ownerRepository,
			VisitRepository visitRepository, BookingRepository bookingRepository, CauseRepository causeRepository, 
			DonationRepository donationRepository){

		this.petRepository = petRepository;
		this.vetRepository = vetRepository;
		this.ownerRepository = ownerRepository;
		this.visitRepository = visitRepository;
		this.bookingRepository = bookingRepository;
		this.causeRepository = causeRepository;
		this.donationRepository = donationRepository;


	}
	@Transactional(readOnly = true)
	public Collection<Owner> findOwners()   {
		return this.ownerRepository.findOwners();
	}
	@Transactional(readOnly = true)
	public Collection<PetType> findPetTypes()   {
		return this.petRepository.findPetTypes();
	}
	@Transactional(readOnly = true)
	public List<Specialty> findSpecialties()   {
		return this.vetRepository.findSpecialties();
	}

	@Transactional(readOnly = true)
	public Owner findOwnerById(final int id)   {
		return this.ownerRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public Collection<Owner> findOwnerByLastName(final String lastName)   {
		return this.ownerRepository.findByLastName(lastName);
	}

	@Transactional
	public void saveOwner(final Owner owner)   {
		this.ownerRepository.save(owner);
	}

	@Transactional
	public void saveVisit(final Visit visit)   {
		this.visitRepository.save(visit);
	}

	@Transactional(readOnly = true)
	public Pet findPetById(final int id)   {
		return this.petRepository.findById(id);
	}

	@Transactional
	public void savePet(final Pet pet)   {
		this.petRepository.save(pet);
	}

	@Transactional(readOnly = true)
	@Cacheable(value = "vets")
	public Collection<Vet> findVets()   {
		return this.vetRepository.findAll();
	}

	public Collection<Visit> findVisitsByPetId(final int petId) {
		return this.visitRepository.findByPetId(petId);
	}

	@Transactional
	public void deletePet(final int id)   {
		this.petRepository.deletePet(id);
	}

	@Transactional
	public void saveVet(@Valid final Vet vet) {
		this.vetRepository.save(vet);

	}
	@Transactional
	public void saveSpecialty(@Valid final Specialty specialty) {
		this.vetRepository.save(specialty);

	}

	public Vet findVetById(int vetId)  {
		return vetRepository.findVetById(vetId);
	}
	
	@Transactional
	public void deleteVet(int id)  {
		vetRepository.deleteVet(id);
	}

	@Transactional
	public void deleteOwner(Owner owner)   {
		ownerRepository.delete(owner.getId());
	}

	// BOOKING
	@Transactional
	public void saveBooking(Booking booking)   {
		bookingRepository.save(booking);
	}

	public Collection<Booking> findBookingsByPetId(int petId) {
		return bookingRepository.findByPetId(petId);
	}
	
	@Transactional
	public void deleteBooking(int id)  {
		bookingRepository.deleteById(id);
  	}
	@Transactional
	public void deleteVisit(int id)   {
		visitRepository.deleteById(id);
	}
	// CAUSES
	@Transactional(readOnly = true)
	@Cacheable(value = "causes")
	public Collection<Cause> findCauses()   {
		return this.causeRepository.findAll();
	}
	public Cause findCauseById(int causeId)   {
		return causeRepository.findCauseById(causeId);
	}

	@Transactional
	public void saveCause(Cause cause)  {
		this.causeRepository.save(cause);
	}
	// DONATIONS
	public Collection<Donation> findDonations(int causeId)   {
		return causeRepository.findDonations(causeId);
	}
	@Transactional
	public void saveDonation(Donation donation)   {
		donationRepository.save(donation);
	}
}
