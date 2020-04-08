package edu.northeastern.cs5200.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

import edu.northeastern.cs5200.models.FoodItem;

public interface FoodItemRepository extends CrudRepository<FoodItem, Integer>{

  @Query("SELECT f FROM FoodItem f WHERE f.menu.id =:id")
  Collection<FoodItem> findFoodItemByMenuId(@Param("id") int id);
}
