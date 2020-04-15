package edu.northeastern.cs5200.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import edu.northeastern.cs5200.models.WishList;

public interface WishListRepository extends CrudRepository<WishList, Integer> {

  @Query("SELECT s FROM WishList s WHERE s.customer.id =:id")
  List<WishList> findWishListByCustomerId(@Param("id") int id);
}
