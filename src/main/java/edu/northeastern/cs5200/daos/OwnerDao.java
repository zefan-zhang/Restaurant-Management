package edu.northeastern.cs5200.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import edu.northeastern.cs5200.models.Cooker;
import edu.northeastern.cs5200.models.FoodItem;
import edu.northeastern.cs5200.models.Person;
import edu.northeastern.cs5200.repositories.CookerRepository;
import edu.northeastern.cs5200.repositories.FoodItemRepository;

@Service
public class OwnerDao {
  @Autowired
  private FoodItemRepository foodItemRepository;

  @Autowired
  private CookerRepository cookerRepository;

  // cooker
  public List<Cooker> findAllCookers() {
    return (List<Cooker>) cookerRepository.findAll();
  }

  public void AddCooker(Cooker cooker) {
    cookerRepository.save(cooker);
  }

  public Cooker findCookerById(int id) {
    Optional<Cooker> optional = cookerRepository.findById(id);
    if (optional.isPresent()) {
      return optional.get();
    }
    return null;
  }

  public void deleteCookerById(int id) {
    cookerRepository.deleteById(id);
  }

  // foodItem
  public void CreateFoodItem(FoodItem foodItem) {
    foodItemRepository.save(foodItem);
  }
  public List<FoodItem> findAllFoodItem() {
    return (List<FoodItem>) foodItemRepository.findAll();
  }

  public FoodItem findFoodById(int id){
    Optional<FoodItem> optional = foodItemRepository.findById(id);
    if (optional.isPresent()) {
      return optional.get();
    }
    return null;
  }

  public void deleteFoodById(int id) {
    foodItemRepository.deleteById(id);
  }
  public void deleteAllFoodItem() {
    foodItemRepository.deleteAll();
  }

}
