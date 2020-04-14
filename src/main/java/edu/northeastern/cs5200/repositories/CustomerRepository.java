package edu.northeastern.cs5200.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.models.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

  @Query("SELECT p FROM Customer p WHERE p.username =:userName And p.password =:password")
  Customer findCustomerByUnamePword(@Param("userName") String userName, @Param("password") String password);

  @Query("SELECT p FROM Customer p WHERE p.username =:userName")
  Customer findCustomerByUname(@Param("userName") String userName);
}