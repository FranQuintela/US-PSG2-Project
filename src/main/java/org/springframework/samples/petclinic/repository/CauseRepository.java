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

package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;

/**
 * Repository class for <code>Cause</code> domain objects All method names are compliant
 * with Spring Data naming conventions so this interface can easily be extended for Spring
 * Data See here:
 * http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 */
public interface CauseRepository {

	/**
	 * Retrieve all <code>Cause</code>s from the data store.
	 *
	 * @return a <code>Collection</code> of <code>Cause</code>s
	 */
	Collection<Cause> findAll();
	void save(Cause cause);
	Cause findCauseById(int causeId);

	
	/**
	 * Retrieve an <code>Cause</code> from the data store by id.
	 * @param id the id to search for
	 * @return the <code>Cause</code> if found
	 * @throws org.springframework.dao.DataRetrievalFailureException if not found
	 */
	Cause findById(int id);
	
	/**
	 * Deletes a Single Cause
	 */

	 void deleteCause(int id);
	 Collection<Donation> findDonations(int causeId);

	


}
