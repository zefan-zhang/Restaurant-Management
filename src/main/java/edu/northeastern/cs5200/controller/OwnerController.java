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

import edu.northeastern.cs5200.daos.OwnerDao;
import edu.northeastern.cs5200.models.Address;
import edu.northeastern.cs5200.models.Contract;
import edu.northeastern.cs5200.models.ContractStatus;
import edu.northeastern.cs5200.models.Cooker;
import edu.northeastern.cs5200.models.Customer;
import edu.northeastern.cs5200.models.FoodItem;
import edu.northeastern.cs5200.models.Menu;
import edu.northeastern.cs5200.models.Person;
import edu.northeastern.cs5200.models.Phone;

@Controller
public class OwnerController {
  @Autowired
  private OwnerDao ownerDao;

  // menu
  @GetMapping("/owner")
  public String homePage(Model model) {
    List<Menu> menus = ownerDao.findAllMenus();
    model.addAttribute("menus", menus);
    List<FoodItem> foodItems = ownerDao.findAllFoodItem();
    model.addAttribute("foodItems", foodItems);
    List<Cooker> cookers = ownerDao.findAllCookers();
    model.addAttribute("cookers", cookers);
    List<Customer> customers = ownerDao.findAllCustomer();
    model.addAttribute("customers", customers);
    return "owner";
  }

  @GetMapping("add_menu")
  public String createMenu(Model model) {
    Menu menu = new Menu();
    model.addAttribute("menu", menu);
    return "new_menu";
  }

  @PostMapping(value = "/save_menu")
  public String saveMenu(@ModelAttribute("menu") Menu menu) {
    ownerDao.saveMenu(menu);
    return "redirect:/owner";
  }

  @GetMapping("edit_menu/{id}")
  public ModelAndView goEditMenuPage(@PathVariable(name = "id") int id) {
    ModelAndView modelAndView = new ModelAndView("update_menu");
    Menu menu = ownerDao.findMenuById(id);
    modelAndView.addObject("menu", menu);
    return modelAndView;
  }

  @GetMapping(value = "/delete_menu/{id}")
  public String deleteMenu(@PathVariable(name = "id") int id){
    Menu menu = ownerDao.findMenuById(id);
    List<FoodItem> foodItems = (List<FoodItem>) menu.getFoodItems();
    for (FoodItem foodItem : foodItems) {
      ownerDao.deleteFoodById(foodItem.getId());
    }
    ownerDao.deleteMenuById(id);
    return "redirect:/owner";
  }

  @GetMapping("/menu/{id}")
  public String getFoodItemsByMenuId(@PathVariable(name = "id") int id, Model model) {
    List<FoodItem> foodItems = ownerDao.findFoodsByMenuId(id);
    model.addAttribute("foodsOnMenu", foodItems);
    return "menu_foods";
  }

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
  // customer
  @GetMapping("/add_customer")
  public String addCustomerPage(Model model) {
    Customer customer = new Customer();
    model.addAttribute("customer", customer);
    return "new_customer";
  }

  @PostMapping(value = "/save_customer")
  public String saveCustomer(@ModelAttribute("customer") Customer customer) {
    ownerDao.addCustomer(customer);
    return "redirect:/owner";
  }

  @GetMapping("edit_customer/{id}")
  public ModelAndView goEditCustomer(@PathVariable(name = "id") int id) {
    ModelAndView modelAndView = new ModelAndView("update_customer");
    Customer customer = ownerDao.findCustomerById(id);
    modelAndView.addObject("customer", customer);
    return modelAndView;
  }

  @GetMapping(value = "/delete_customer/{id}")
  public String deleteCustomer(@PathVariable(name = "id") int id) {
    ownerDao.deleteCustomerById(id);
    return "redirect:/owner";
  }

  // address
  @GetMapping("address/{personId}")
  public String getAddressByUserId(@PathVariable(name = "personId") int id, Model model) {
    List<Address> addresses = ownerDao.getAddressByPersonId(id);
    model.addAttribute("addresses", addresses);
    return "user_address";
  }

  @GetMapping("add_address/{personId}")
  public String addAddress(@PathVariable(name = "personId") int id, Model model) {
    Person person = ownerDao.findPersonById(id);
    Address address = new Address();
    person.addAddress(address);
    address.setPerson(person);
    model.addAttribute("address", address);
    return "new_address";
  }

  @GetMapping("edit_address/{id}")
  public ModelAndView goEditAddress(@PathVariable(name = "id") int id) {
    ModelAndView modelAndView = new ModelAndView("update_address");
    Address address = ownerDao.findAddressById(id);
    modelAndView.addObject("address", address);
    return modelAndView;
  }

  @PostMapping(value = "/save_address")
  public String saveAddress(@ModelAttribute("address") Address address) {
    ownerDao.saveAddress(address);
    return "redirect:/address/" + address.getPerson().getId();
  }

  @GetMapping(value = "/delete_address/{id}")
  public String deleteAddress(@PathVariable(name = "id") int id){
    Address address = ownerDao.findAddressById(id);
    ownerDao.deleteAddressById(id);
    return "redirect:/address/" + address.getPerson().getId();
  }

  // phone
  @GetMapping("phone/{personId}")
  public String getPhoneByUserId(@PathVariable(name = "personId") int id, Model model){
    List<Phone> phones = ownerDao.getPhoneByPersonId(id);
    model.addAttribute("phones", phones);
    return "user_phones";
  }

  @GetMapping("add_phone/{personId}")
  public String addPhone(@PathVariable(name = "personId") int id, Model model) {
    Person person = ownerDao.findPersonById(id);
    Phone phone = new Phone();
    phone.setPerson(person);
    model.addAttribute("phone", phone);
    return "new_phone";
  }

  @GetMapping("edit_phone/{id}")
  public ModelAndView goEditPhone(@PathVariable(name = "id") int id) {
    ModelAndView modelAndView = new ModelAndView("update_phone");
    Phone phone = ownerDao.findPhoneById(id);
    modelAndView.addObject("phone", phone);
    return modelAndView;
  }

  @PostMapping(value = "/save_phone")
  public String savePhone(@ModelAttribute("phone") Phone phone) {
    ownerDao.savePhone(phone);
    return "redirect:/phone/" + phone.getPerson().getId();
  }

  @GetMapping(value = "/delete_phone/{id}")
  public String deletePhone(@PathVariable(name = "id") int id) {
    Phone phone = ownerDao.findPhoneById(id);
    ownerDao.deletePhoneById(id);
    return "redirect:/phone/" + phone.getPerson().getId();
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
    ownerDao.addCooker(cooker);
    return "redirect:/owner";
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
    ownerDao.deleteCookerById(id);
    return "redirect:/cookers";
  }

  @GetMapping("/subordinates/{id}")
  public String goSubordinatesPage(@PathVariable(name = "id") int id, Model model) {
    List<Cooker> subordinates = ownerDao.findSubordinateByMId(id);
    model.addAttribute("subordinates", subordinates);
    return "subordinates";
  }


  // foodItem
  @GetMapping("/foods")
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
    return "redirect:/owner";
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
    return "redirect:/owner";
  }

}
