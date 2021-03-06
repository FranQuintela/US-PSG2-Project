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


import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.repository.VetRepository;

/**
 * Spring Data JPA specialization of the {@link VetRepository} interface
 *
 * @author Michael Isvy
 * @since 15.1.2013
 * 
 */
public interface SpringDataVetRepository extends VetRepository, Repository<Vet, Integer> {

	@Override
	@Query("SELECT specialty FROM Specialty specialty ORDER BY specialty.name")
	List<Specialty> findSpecialties() throws DataAccessException;

	@Override
    @Query("SELECT v FROM Vet v left join fetch v.specialties WHERE v.id =:vetid")
    public Vet findVetById(@Param("vetid") int vetId) throws DataAccessException;

    @Override
	@Query("SELECT vet FROM Vet vet WHERE vet.id =:id")
	public Vet findById(@Param("id") int id) throws DataAccessException;
	
	@Modifying
	@Query("DELETE FROM Vet v WHERE v.id =:vetid")
	void deleteVet(int vetid) throws DataAccessException;

}
