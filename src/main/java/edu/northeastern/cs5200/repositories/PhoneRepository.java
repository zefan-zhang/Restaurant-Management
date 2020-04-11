package edu.northeastern.cs5200.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

import edu.northeastern.cs5200.models.Phone;

public interface PhoneRepository extends CrudRepository<Phone, Integer> {

  @Query("SELECT p FROM Phone p WHERE p.person.id =:id")
  Collection<Phone> findPhoneByPersonId(@Param("id") int id);
}
