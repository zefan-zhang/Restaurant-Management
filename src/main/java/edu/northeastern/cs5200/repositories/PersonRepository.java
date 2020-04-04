package edu.northeastern.cs5200.repositories;

import org.springframework.data.repository.CrudRepository;

public interface Person extends CrudRepository<Person, Integer> {
}
