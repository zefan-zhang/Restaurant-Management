package edu.northeastern.cs5200.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.northeastern.cs5200.models.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
