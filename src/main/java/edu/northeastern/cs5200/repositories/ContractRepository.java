package edu.northeastern.cs5200.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.models.Contract;
import edu.northeastern.cs5200.models.Cooker;

public interface ContractRepository extends CrudRepository<Contract, Integer> {

}
