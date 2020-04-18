package edu.northeastern.cs5200.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import edu.northeastern.cs5200.models.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {

  @Query("SELECT c FROM Order c WHERE c.customer.id =:id")
  List<Order> findOrderByCustomerId(@Param("id") int id);
}
