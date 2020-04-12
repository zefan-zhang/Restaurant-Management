package edu.northeastern.cs5200.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

import edu.northeastern.cs5200.models.FoodReview;

public interface FoodReviewRepository extends CrudRepository<FoodReview, Integer> {
  @Query("SELECT f FROM FoodReview f WHERE f.foodItem.id =:id")
  Collection<FoodReview> findReviewByFoodId(@Param("id") int id);
}
