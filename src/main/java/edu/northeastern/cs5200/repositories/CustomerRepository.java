package edu.northeastern.cs5200.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.northeastern.cs5200.models.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
