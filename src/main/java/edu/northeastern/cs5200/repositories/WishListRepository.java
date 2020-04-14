package edu.northeastern.cs5200.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import edu.northeastern.cs5200.models.ShoppingCart;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Integer> {

  @Query("SELECT s FROM ShoppingCart s WHERE s.customer.id =:id")
  List<ShoppingCart> findShoppingCartByCustomerId(@Param("id") int id);
}
