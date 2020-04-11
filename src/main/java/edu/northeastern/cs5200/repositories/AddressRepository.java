package edu.northeastern.cs5200.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

import edu.northeastern.cs5200.models.Address;

public interface AddressRepository extends CrudRepository<Address, Integer> {

  @Query("SELECT p FROM Address p WHERE p.person.id =:id")
  Collection<Address> findAddressByPersonId(@Param("id") int id);
}
