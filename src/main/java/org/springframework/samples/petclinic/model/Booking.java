/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

/**
 * Simple JavaBean domain object representing a booking.
 *
 * @author Ken Krebs
 */
@Entity
@Table(name = "bookings")
public class Booking extends BaseEntity {

	/**
	 * Holds value of property date.
	 */
	@Column(name = "date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate date;
	/**
	 * Holds value of property date.
	 */
	@Column(name = "exitDate")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate exitDate;

	/**
	 * Holds value of property description.
	 */
	@NotEmpty
	@Column(name = "details")
	private String details;

	/**
	 * Holds value of property pet.
	 */
	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;

	/**
	 * Creates a new instance of Visit for the current date
	 */
	public Booking() {
		this.date = LocalDate.now();
	}

	/**
	 * Getter for property date.
	 * @return Value of property date.
	 */
	public LocalDate getDate() {
		return this.date;
	}

	/**
	 * Setter for property date.
	 * @param date New value of property date.
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * Getter for property date.
	 * @return Value of property date.
	 */
	public LocalDate getExitDate() {
		return this.exitDate;
	}
	/**
	 * Setter for property date.
	 * @param date New value of property date.
	 */
	public void setExitDate(LocalDate date) {
		this.exitDate = date;
	}

	/**
	 * Getter for property details.
	 * @return Value of property details.
	 */
	public String getDetails() {
		return this.details;
	}

	/**
	 * Setter for property description.
	 * @param details New value of property description.
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	/**
	 * Getter for property pet.
	 * @return Value of property pet.
	 */
	public Pet getPet() {
		return this.pet;
	}

	/**
	 * Setter for property pet.
	 * @param pet New value of property pet.
	 */
	public void setPet(Pet pet) {
		this.pet = pet;
	}

}
