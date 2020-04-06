package edu.northeastern.cs5200.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import edu.northeastern.cs5200.models.Contract;
import edu.northeastern.cs5200.models.Cooker;
import edu.northeastern.cs5200.models.FoodItem;
import edu.northeastern.cs5200.models.Menu;
import edu.northeastern.cs5200.models.Person;
import edu.northeastern.cs5200.repositories.ContractRepository;
import edu.northeastern.cs5200.repositories.CookerRepository;
import edu.northeastern.cs5200.repositories.FoodItemRepository;
import edu.northeastern.cs5200.repositories.MenuRepository;

@Service
public class OwnerDao {
  @Autowired
  private FoodItemRepository foodItemRepository;

  @Autowired
  private CookerRepository cookerRepository;

  @Autowired
  private ContractRepository contractRepository;

  @Autowired
  private MenuRepository menuRepository;

  // menu
  public List<Menu> findAllMenus() {
    return (List<Menu>) menuRepository.findAll();
  }

  public void saveMenu(Menu menu) {
    menuRepository.save(menu);
  }

  public Menu findMenuById(int id) {
    Optional<Menu> optional = menuRepository.findById(id);
    if (optional.isPresent()) {
      return optional.get();
    }
    return null;
  }

  public void deleteMenuById(int id) {
    menuRepository.deleteById(id);
  }
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

  public void assignCookerForManager(int subordinateId, int mangerId) {
    Optional<Cooker> cooker = cookerRepository.findById(subordinateId);
    Optional<Cooker> manager = cookerRepository.findById(mangerId);
    if (cooker.isPresent() && manager.isPresent()) {
      cooker.get().setManager(manager.get());
    }
  }
  public void deleteCookerById(int id) {
    cookerRepository.deleteById(id);
  }

  // contract
  public List<Contract> findAllContract() {
    return (List<Contract>) contractRepository.findAll();
  }

  public Contract findContractById(int id) {
    Optional<Contract> optional = contractRepository.findById(id);
    if (optional.isPresent())
    {
      return optional.get();
    }
    return null;
  }

  public void saveContract(Contract contract) {
    contractRepository.save(contract);
  }

  public void deleteContractById(int id) {
    contractRepository.deleteById(id);
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
