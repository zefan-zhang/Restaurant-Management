package edu.northeastern.cs5200.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.northeastern.cs5200.models.Contract;

public interface ContractRepository extends CrudRepository<Contract, Integer> {
}
