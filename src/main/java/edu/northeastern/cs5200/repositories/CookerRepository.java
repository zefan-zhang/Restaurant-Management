package edu.northeastern.cs5200.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.northeastern.cs5200.models.Cooker;

public interface CookerRepository extends CrudRepository<Cooker, Integer> {
}
