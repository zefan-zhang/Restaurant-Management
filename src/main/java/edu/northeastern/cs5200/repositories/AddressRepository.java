package edu.northeastern.cs5200.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.northeastern.cs5200.models.Address;

public interface AddressRepository extends CrudRepository<Address, Integer> {
}
