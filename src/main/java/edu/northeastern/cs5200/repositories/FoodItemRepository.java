package edu.northeastern.cs5200.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.northeastern.cs5200.models.FoodItem;

public interface FoodItemRepository extends CrudRepository<FoodItem, Integer>{
}
