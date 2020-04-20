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

package org.springframework.samples.petclinic.repository.springdatajpa;


import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.repository.CauseRepository;

/**
 * Spring Data JPA specialization of the {@link CauseRepository} interface
 *
 * @author Michael Isvy
 * @since 15.1.2013
 * 
 */
public interface SpringDataCauseRepository extends CauseRepository, Repository<Cause, Integer> {


	@Override
    @Query("SELECT c FROM Cause c WHERE c.id =:causeid")
    public Cause findCauseById(@Param("causeid") int causeId)  ;

    @Override
	@Query("SELECT cause FROM Cause cause WHERE cause.id =:id")
	public Cause findById(@Param("id") int id)  ;
	
	@Modifying
	@Query("DELETE FROM Cause c WHERE c.id =:causeid")
	void deleteCause(int causeid)  ;
	
	@Query("SELECT d FROM Donation d WHERE d.cause.id=:causeId")
	Collection<Donation> findDonations(int causeId)  ;

}
