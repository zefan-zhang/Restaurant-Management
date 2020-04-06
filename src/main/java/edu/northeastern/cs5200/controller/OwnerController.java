package edu.northeastern.cs5200.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

import edu.northeastern.cs5200.daos.OwnerDao;
import edu.northeastern.cs5200.models.Contract;
import edu.northeastern.cs5200.models.ContractStatus;
import edu.northeastern.cs5200.models.Cooker;
import edu.northeastern.cs5200.models.FoodItem;
import edu.northeastern.cs5200.models.Person;

@Controller
public class OwnerController {
  @Autowired
  private OwnerDao ownerDao;

  // contract
  @GetMapping("/contracts")
  public String listAllCookerContracts(Model model) {
    List<Contract> contracts = ownerDao.findAllContract();
    model.addAttribute("contracts", contracts);
    return "contracts";
  }

  @GetMapping("/create_contract")
  public String CreateContract(Model model) {
    Contract contract = new Contract();
    model.addAttribute("contract", contract);
    return "new_contract";
  }

  @PostMapping(value = "/save_contract")
  public String saveCooker(@ModelAttribute("contract") Contract contract) {
    ownerDao.saveContract(contract);
    return "redirect:/contract/" + contract.getId();
  }

  @GetMapping("edit_contract/{id}")
  public ModelAndView goEditContractPage(@PathVariable(name = "id") int id) {
    ModelAndView modelAndView = new ModelAndView("update_contract");
    Contract contract = ownerDao.findContractById(id);
    modelAndView.addObject("contract", contract);
    return modelAndView;
  }

  // cooker
  @GetMapping("/cookers")
  public String listAllCookers(Model model) {
    List<Cooker> cookers = ownerDao.findAllCookers();
    model.addAttribute("cookers", cookers);
    return "all_cookers";
  }

  @GetMapping("/add_cooker")
  public String goAddCookerPage(Model model) {
    Cooker cooker = new Cooker();
    model.addAttribute("cooker", cooker);
    return "new_cooker";
  }

  @PostMapping(value = "/save_cooker")
  public String saveCooker(@ModelAttribute("cooker") Cooker cooker) {
    Contract contract = new Contract();
    contract.setContractStatus(ContractStatus.no_sign);
    cooker.setContract(contract);
    ownerDao.AddCooker(cooker);
    return "redirect:/cookers";
  }

  @GetMapping("contract/{id}")
  public String getCookerContract(@PathVariable(name = "id") int id, Model model) {
    Contract contract = ownerDao.findContractById(id);
    model.addAttribute("contract", contract);
    return "cooker_contract";
  }

  @GetMapping("/edit_cooker/{id}")
  public ModelAndView goEditCookerPage(@PathVariable(name = "id") int id) {
    ModelAndView modelAndView = new ModelAndView("update_cooker");
    Cooker cooker = ownerDao.findCookerById(id);
    modelAndView.addObject("cooker", cooker);
    return modelAndView;
  }

  @GetMapping(value = "/delete_cooker/{id}")
  public String deleteCooker(@PathVariable(name = "id") int id){
    Cooker cooker = ownerDao.findCookerById(id);
    ownerDao.deleteContractById(cooker.getContract().getId());
    ownerDao.deleteCookerById(id);
    return "redirect:/cookers";
  }


  // foodItem
  @GetMapping("/")
  public String goFoodItemPage(Model model) {
    List<FoodItem> foodItems = ownerDao.findAllFoodItem();
    model.addAttribute("foodItems", foodItems);
    return "foods";
  }
  @GetMapping("/create_food")
  public String goCreateFoodPage(Model model){
    FoodItem food = new FoodItem();
    model.addAttribute("food", food);
    return "new_food";
  }

  @PostMapping(value = "/save_food")
  public String saveFood(@ModelAttribute("food") FoodItem food) {
    ownerDao.CreateFoodItem(food);
    return "redirect:/";
  }

  @GetMapping("/edit_food/{id}")
  public ModelAndView goEditFoodPage(@PathVariable(name = "id") int id) {
    ModelAndView modelAndView = new ModelAndView("update_food");
    FoodItem foodItem = ownerDao.findFoodById(id);
    modelAndView.addObject("food", foodItem);
    return modelAndView;
  }

  @GetMapping(value = "/delete_food/{id}")
  public String deleteFood(@PathVariable(name = "id") int id){
    ownerDao.deleteFoodById(id);
    return "redirect:/";
  }

}
