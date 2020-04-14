package edu.northeastern.cs5200.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.List;

import edu.northeastern.cs5200.daos.OwnerDao;
import edu.northeastern.cs5200.models.Address;
import edu.northeastern.cs5200.models.Contract;
import edu.northeastern.cs5200.models.ContractStatus;
import edu.northeastern.cs5200.models.Cooker;
import edu.northeastern.cs5200.models.Customer;
import edu.northeastern.cs5200.models.FoodItem;
import edu.northeastern.cs5200.models.FoodReview;
import edu.northeastern.cs5200.models.Menu;
import edu.northeastern.cs5200.models.Owner;
import edu.northeastern.cs5200.models.Person;
import edu.northeastern.cs5200.models.Phone;
import edu.northeastern.cs5200.models.RoleType;

@Controller
public class OwnerController {

  @Autowired
  private OwnerDao ownerDao;

  // home page
  @GetMapping("/")
  public String homePage(Model model) {
    List<Menu> menus = ownerDao.findAllMenus();
    model.addAttribute("menus", menus);
    return "homepage";
  }

  // menu
  @GetMapping("/menus")
  public String goPublicMenuPage(Model model) {
    List<Menu> menus = ownerDao.findAllMenus();
    model.addAttribute("menus", menus);
    return "public_menu";
  }

  @GetMapping("/menu/{id}")
  public String goPublicMenuFoodPage(@PathVariable(name = "id") int id, Model model) {
    List<FoodItem> foodItems = ownerDao.findFoodsByMenuId(id);
    model.addAttribute("foodsOnMenu", foodItems);
    return "public_menu_foods";
  }

  // foodReviews
  @GetMapping("/public_food_reviews/{foodId}")
  public String goPublicFoodReviews(@PathVariable(name = "foodId") int id, Model model) {
    List<FoodReview> foodReviews = ownerDao.findReviewByFoodId(id);
    model.addAttribute("foodReviews", foodReviews);
    return "public_food_reviews";
  }

  // register
  @RequestMapping("/register")
  public String goRegisterPage(Model model) {
    Person person = new Person();
    model.addAttribute("person" , person);
    return "register";
  }

  @PostMapping(value = "/save_user")
  public String goUserPage(@ModelAttribute("person") Person person) {
    RoleType userRole = person.getRole();
    if (userRole.equals(RoleType.owner)) {
      Owner owner = new Owner(person.getRole(), person.getFistName(), person.getLastName(),
              person.getUserName(), person.getPassword(), person.getGender(), person.getEmail(),
              person.getDob());
      ownerDao.saveOwner(owner);
    }
    else if (userRole.equals(RoleType.cooker)) {
      Cooker cooker = new Cooker(person.getRole(), person.getFistName(), person.getLastName(),
              person.getUserName(), person.getPassword(), person.getGender(), person.getEmail(),
              person.getDob());
      ownerDao.addCooker(cooker);
    }
    else if (userRole.equals(RoleType.customer)) {
      Customer customer = new Customer(person.getRole(), person.getFistName(), person.getLastName(),
              person.getUserName(), person.getPassword(), person.getGender(), person.getEmail(),
              person.getDob());
      ownerDao.addCustomer(customer);
    }
    return "redirect:/" + userRole.toString() + "/" + person.getUserName();
  }

  //login
  @RequestMapping("/login")
  public String goLoginPage(Model model) {
    Person person = new Person();
    model.addAttribute("person", person);
    return "go_login";
  }

  @GetMapping("/get_user")
  public String gerUserPage(@ModelAttribute("person") Person person) {
    String userName = person.getUserName();
    String password = person.getPassword();
    RoleType role = person.getRole();
    if (role.equals(RoleType.owner)) {
      Owner user = ownerDao.findOwnerByUnamePword(userName, password);
      if (user != null) {
        return "redirect:/owner/" + userName;
      }
    } else if (role.equals(RoleType.cooker)) {
      Cooker user = ownerDao.findCookerByUnamePword(userName, password);
      if (user != null) {
        return "redirect:/cooker/" + userName;
      }
    } else if (role.equals(RoleType.customer)) {
      Customer user = ownerDao.findCustomerByUnamePword(userName, password);
      if (user != null) {
        return "redirect:/customer/" + userName;
      }
    }

    return "relogin";
  }

  @GetMapping("/{role}/{username}")
  public String goRoleHomePage(@PathVariable(name = "role") RoleType roleType,
                               @PathVariable(name = "username") String username, Model model) {
    if (roleType.equals(RoleType.owner)) {
      Owner owner = ownerDao.findOwnerByUname(username);
      model.addAttribute("owner", owner);
      return "";
    } else if (roleType.equals(RoleType.cooker)) {
      Cooker cooker = ownerDao.findCookerByUname(username);
      model.addAttribute("cooker", cooker);
      return "";
    } else if (roleType.equals(RoleType.customer)) {
      Customer customer = ownerDao.findCustomerByUname(username);
      model.addAttribute("customer", customer);
      return "customer_homepage";
    }
    return "redirect:/";
  }



  // owner
  @GetMapping("/owner")
  public String ownerPage(Model model) {
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
    ownerDao.deleteMenuById(id);
    return "redirect:/owner";
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
    Person person = ownerDao.findPersonById(address.getPerson().getId());
    return "redirect:/" + person.getRole().toString() + "/" + person.getUserName();
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
    Customer person = ownerDao.findCustomerById(phone.getPerson().getId());
    return "redirect:/" + person.getRole().toString() + "/" + person.getUserName();
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
    return "redirect:/owner";
  }

  @GetMapping("manager/{cookerId}")
  public String getManagerByCookerId(@PathVariable(name = "cookerId") int id, Model model) {
    Cooker manager = ownerDao.getManagerByCookerId(id);
    model.addAttribute("manager", manager);
    return "cooker_manager";
  }

  @GetMapping("/assign_manager/{cookerId}")
  public ModelAndView assignToManagerPage(@PathVariable(name = "cookerId") int id) {
    ModelAndView modelAndView = new ModelAndView("assign_to_manager");
    Cooker cooker = ownerDao.findCookerById(id);
    modelAndView.addObject("cooker", cooker);
    return modelAndView;
  }

  @PostMapping(value = "/save_assigned_manager")
  public String saveAssignedManager(@ModelAttribute("cooker") Cooker cooker) {
    Cooker manager = cooker.getManager();
    Cooker subordinate = ownerDao.findCookerById(cooker.getId());
    subordinate.setManager(manager);
    ownerDao.addCooker(subordinate);
    return "redirect:/owner";
  }

  @GetMapping("/subordinates/{id}")
  public String goSubordinatesPage(@PathVariable(name = "id") int id, Model model) {
    List<Cooker> subordinates = ownerDao.findSubordinateByMId(id);
    model.addAttribute("subordinates", subordinates);
    return "subordinates";
  }

  @GetMapping(value = "/remove_subordinate/{subordinateId}")
  public String removeRelationship(@PathVariable(name = "subordinateId") int id) {
    Cooker cooker = ownerDao.findCookerById(id);
    int managerId = cooker.getManager().getId();
    cooker.setManager(null);
    ownerDao.addCooker(cooker);
    return "redirect:/subordinates/" + managerId;
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
    return "redirect:/menu/" + food.getMenu().getId();
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

  // foodReviews
  @GetMapping("/food_reviews/{foodId}")
  public String getFoodReviews(@PathVariable(name = "foodId") int id, Model model) {
    List<FoodReview> foodReviews = ownerDao.findReviewByFoodId(id);
    model.addAttribute("foodReviews", foodReviews);
    return "food_reviews";
  }

  @GetMapping("/write_food_review/{foodId}")
  public String writeReviewForFood(@PathVariable(name = "foodId")int id, Model model) {
    FoodReview foodReview = new FoodReview();
    foodReview = ownerDao.writeReviewForFood(foodReview, id);
    model.addAttribute("foodReview", foodReview);
    return "new_food_review";
  }

  @PostMapping("/save_review")
  public String saveFoodReview(@ModelAttribute("foodReview") FoodReview foodReview) {
    ownerDao.saveFoodReview(foodReview);
    return "redirect:/food_reviews/" + foodReview.getFoodItem().getId();
  }

  @GetMapping(value = "/delete_review/{id}")
  public String deleteReview(@PathVariable(name = "id") int id){
    ownerDao.deleteReviewById(id);
    return "redirect:/foods";
  }

  @GetMapping("/customer_foodreview/{customerId}")
  public String goCustomerFoodReviews(@PathVariable(name = "customerId") int id, Model model) {
    Collection<FoodReview> foodReviews = ownerDao.findReviewByCustomerId(id);
    model.addAttribute("foodReviews", foodReviews);
    return "customer_food_reviews";
  }

  @GetMapping("/edit_review/{id}")
  public ModelAndView goEditMyFoodReview(@PathVariable(name = "id") int id) {
    ModelAndView modelAndView = new ModelAndView("edit_my_foodreview");
    FoodReview foodReview = ownerDao.findReviewById(id);
    modelAndView.addObject("foodReview", foodReview);
    return modelAndView;
  }

}
