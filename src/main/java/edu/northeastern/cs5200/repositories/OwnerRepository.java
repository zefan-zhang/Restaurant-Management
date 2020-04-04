package edu.northeastern.cs5200.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.northeastern.cs5200.models.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Integer> {
}
