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

import edu.northeastern.cs5200.daos.OrderDao;
import edu.northeastern.cs5200.daos.RestaurantDao;
import edu.northeastern.cs5200.models.Address;
import edu.northeastern.cs5200.models.Contract;
import edu.northeastern.cs5200.models.Cooker;
import edu.northeastern.cs5200.models.Customer;
import edu.northeastern.cs5200.models.FoodItem;
import edu.northeastern.cs5200.models.FoodReview;
import edu.northeastern.cs5200.models.Menu;
import edu.northeastern.cs5200.models.Order;
import edu.northeastern.cs5200.models.Owner;
import edu.northeastern.cs5200.models.Person;
import edu.northeastern.cs5200.models.Phone;
import edu.northeastern.cs5200.models.RoleType;
import edu.northeastern.cs5200.models.WishList;

@Controller
public class RestaurantController {

  @Autowired
  private RestaurantDao restaurantDao;

  @Autowired
  private OrderDao orderDao;

  // home page
  @GetMapping("/")
  public String homePage(Model model) {
    List<Menu> menus = restaurantDao.findAllMenus();
    model.addAttribute("menus", menus);
    return "public_homepage";
  }

  // menu
  @GetMapping("/menus")
  public String goPublicMenuPage(Model model) {
    List<Menu> menus = restaurantDao.findAllMenus();
    model.addAttribute("menus", menus);
    return "public_menu";
  }

  @GetMapping("/{role}/menus/{userId}")
  public String goLoginUserMenu(@PathVariable(name = "role") RoleType roleType,
                                @PathVariable(name = "userId") int id, Model model) {
    List<Menu> menus = restaurantDao.findAllMenus();
    model.addAttribute("menus", menus);
    if (roleType.equals(RoleType.owner)) {
      Owner owner = restaurantDao.findOwnerById(id);
      model.addAttribute("owner", owner);
      return "owner_menu";
    } else if (roleType.equals(RoleType.customer)) {
      Customer customer = restaurantDao.findCustomerById(id);
      model.addAttribute("customer", customer);
      return "customer_menu";
    }
    return "/";
  }

  @GetMapping("/menu/{id}")
  public String goPublicMenuFoodPage(@PathVariable(name = "id") int id, Model model) {
    List<FoodItem> foodItems = restaurantDao.findFoodsByMenuId(id);
    model.addAttribute("foodsOnMenu", foodItems);
    return "public_menu_foods";
  }

  @GetMapping("/{role}/menu/{id}/{userId}")
  public String goLoginUerMenuFood(@PathVariable(name = "role") RoleType roleType,
                                   @PathVariable(name = "id") int id,
                                   @PathVariable(name = "userId") int userId, Model model) {
    List<FoodItem> foodItems = restaurantDao.findFoodsByMenuId(id);
    Menu menu = restaurantDao.findMenuById(id);
    model.addAttribute("foodsOnMenu", foodItems);
    model.addAttribute("menu", menu);
    if (roleType.equals(RoleType.owner)) {
      Owner owner = restaurantDao.findOwnerById(userId);
      model.addAttribute("owner", owner);
      return "owner_menu_foods";
    } else if (roleType.equals(RoleType.customer)) {
      Customer customer = restaurantDao.findCustomerById(userId);
      model.addAttribute("customer", customer);
      return "customer_menu_foods";
    }
    return "public_menu_foods";

  }

  @GetMapping("/customer/food_reviews/{foodId}/{username}")
  public String seeFoodReviews(@PathVariable(name = "foodId") int foodId,
                             @PathVariable(name = "username") String username, Model model) {
    List<FoodReview> reviews = restaurantDao.findReviewByFoodId(foodId);
    Customer customer = restaurantDao.findCustomerByUname(username);
    model.addAttribute("reviews", reviews);
    model.addAttribute("customer", customer);
    return "customer_see_reviews";
  }

  // foodReviews
  @GetMapping("/public_food_reviews/{foodId}")
  public String goPublicFoodReviews(@PathVariable(name = "foodId") int id, Model model) {
    List<FoodReview> foodReviews = restaurantDao.findReviewByFoodId(id);
    model.addAttribute("foodReviews", foodReviews);
    return "public_food_reviews";
  }

  // register
  @RequestMapping("/register")
  public String goRegisterPage(Model model) {
    Person person = new Person();
    model.addAttribute("person", person);
    return "public_register";
  }

  @PostMapping(value = "/save_user")
  public String goUserPage(@ModelAttribute("person") Person person) {
    RoleType userRole = person.getRole();
    if (userRole.equals(RoleType.owner)) {
      Owner owner = new Owner(person.getRole(), person.getFistName(), person.getLastName(),
              person.getUserName(), person.getPassword(), person.getGender(), person.getEmail(),
              person.getDob());
      restaurantDao.saveOwner(owner);
    } else if (userRole.equals(RoleType.cooker)) {
      Cooker cooker = new Cooker(person.getRole(), person.getFistName(), person.getLastName(),
              person.getUserName(), person.getPassword(), person.getGender(), person.getEmail(),
              person.getDob());
      restaurantDao.addCooker(cooker);
    } else if (userRole.equals(RoleType.customer)) {
      Customer customer = new Customer(person.getRole(), person.getFistName(), person.getLastName(),
              person.getUserName(), person.getPassword(), person.getGender(), person.getEmail(),
              person.getDob());
      restaurantDao.addCustomer(customer);
    }
    return "redirect:/" + userRole.toString() + "/" + person.getUserName();
  }

  //login
  @RequestMapping("/login")
  public String goLoginPage(Model model) {
    Person person = new Person();
    model.addAttribute("person", person);
    return "public_go_login";
  }

  @GetMapping("/get_user")
  public String gerUserPage(@ModelAttribute("person") Person person) {
    String userName = person.getUserName();
    String password = person.getPassword();
    RoleType role = person.getRole();
    if (role.equals(RoleType.owner)) {
      Owner user = restaurantDao.findOwnerByUnamePword(userName, password);
      if (user != null) {
        return "redirect:/owner/" + userName;
      }
    } else if (role.equals(RoleType.cooker)) {
      Cooker user = restaurantDao.findCookerByUnamePword(userName, password);
      if (user != null) {
        return "redirect:/cooker/" + userName;
      }
    } else if (role.equals(RoleType.customer)) {
      Customer user = restaurantDao.findCustomerByUnamePword(userName, password);
      if (user != null) {
        return "redirect:/customer/" + userName;
      }
    }

    return "public_relogin";
  }

  @GetMapping("/{role}/{username}")
  public String goRoleHomePage(@PathVariable(name = "role") RoleType roleType,
                               @PathVariable(name = "username") String username, Model model) {
    if (roleType.equals(RoleType.owner)) {
      Owner owner = restaurantDao.findOwnerByUname(username);
      model.addAttribute("owner", owner);
      return "owner_homepage";
    } else if (roleType.equals(RoleType.cooker)) {
      Cooker cooker = restaurantDao.findCookerByUname(username);
      model.addAttribute("cooker", cooker);
      return "cooker_homepage";
    } else if (roleType.equals(RoleType.customer)) {
      Customer customer = restaurantDao.findCustomerByUname(username);
      model.addAttribute("customer", customer);
      return "customer_homepage";
    }
    return "redirect:/";
  }


  // owner
  @GetMapping("/owner")
  public String ownerPage(Model model) {
    List<Cooker> cookers = restaurantDao.findAllCookers();
    model.addAttribute("cookers", cookers);
    List<Customer> customers = restaurantDao.findAllCustomer();
    model.addAttribute("customers", customers);
    return "owner";
  }

  @GetMapping("/all_customers/{username}")
  public String goAllCustomersPage(@PathVariable(name = "username") String username,
                                   Model model) {
    List<Customer> customers = restaurantDao.findAllCustomer();
    Owner owner = restaurantDao.findOwnerByUname(username);
    model.addAttribute("customers", customers);
    model.addAttribute("owner", owner);
    return "owner_all_customers";
  }

  @GetMapping("add_menu")
  public String createMenu(Model model) {
    Menu menu = new Menu();
    model.addAttribute("menu", menu);
    return "owner_new_menu";
  }

  @PostMapping(value = "/save_menu")
  public String saveMenu(@ModelAttribute("menu") Menu menu) {
    restaurantDao.saveMenu(menu);
    return "redirect:/owner/menus/" + menu.getCreatorId();
  }

  @GetMapping("edit_menu/{id}")
  public ModelAndView goEditMenuPage(@PathVariable(name = "id") int id) {
    ModelAndView modelAndView = new ModelAndView("owner_update_menu");
    Menu menu = restaurantDao.findMenuById(id);
    Owner owner = restaurantDao.findOwnerById(menu.getCreatorId());
    modelAndView.addObject("menu", menu);
    modelAndView.addObject("owner", owner);
    return modelAndView;
  }

  @GetMapping(value = "/delete_menu/{menuId}/{ownerId}")
  public String deleteMenu(@PathVariable(name = "menuId") int menuId,
                           @PathVariable(name = "ownerId") int ownerId) {
    restaurantDao.deleteMenuById(menuId);
    return "redirect:/owner/menus/" + ownerId;
  }

  @GetMapping("/customer/menus/{username}")
  public String goCustomerMenus(@PathVariable(name = "username") String username, Model model) {
    List<Menu> menus = restaurantDao.findAllMenus();
    Customer customer = restaurantDao.findCustomerByUname(username);
    model.addAttribute("menus", menus);
    model.addAttribute("customer", customer);
    return "customer_menus";
  }

  @GetMapping("/customer/menu/{menuId}/{username}")
  public String goCustomerFoods(@PathVariable(name = "menuId")int id,
                                @PathVariable(name = "username")String username,
                                Model model){
    List<FoodItem> foodsOnMenu = restaurantDao.findFoodsByMenuId(id);
    Customer customer = restaurantDao.findCustomerByUname(username);
    Menu menu = restaurantDao.findMenuById(id);
    model.addAttribute("foodsOnMenu", foodsOnMenu);
    model.addAttribute("customer", customer);
    model.addAttribute("menu", menu);
    return "customer_menu_foods";

  }

  // contract
  @PostMapping(value = "/save_contract/{cookerId}")
  public String saveContract(@PathVariable(name = "cookerId") int cookerId,
                             @ModelAttribute("contract") Contract contract) {
    restaurantDao.saveContract(contract);
    return "redirect:/cooker_contract/" + cookerId;
  }

  @PostMapping(value = "/owner_save_contract")
  public String saveContractrByOwner(@ModelAttribute("contract") Contract contract) {
    restaurantDao.saveContract(contract);
    return "redirect:/contract/" + contract.getId() + "/admin";
  }

  @GetMapping("owner_edit_contract/{id}/{ownerUsername}")
  public ModelAndView editContractByOwner(@PathVariable(name = "id") int id,
                                          @PathVariable(name = "ownerUsername") String ownerUsername) {
    ModelAndView modelAndView = new ModelAndView("owner_update_contract");
    Contract contract = restaurantDao.findContractById(id);
    Owner owner = restaurantDao.findOwnerByUname(ownerUsername);
    modelAndView.addObject("owner", owner);
    modelAndView.addObject("contract", contract);
    return modelAndView;
  }

  @GetMapping("edit_contract/{contractId}")
  public ModelAndView goEditContractPage(@PathVariable(name = "contractId") int contractId) {
    ModelAndView modelAndView = new ModelAndView("cooker_update_contract");
    Contract contract = restaurantDao.findContractById(contractId);
    Cooker cooker = restaurantDao.findCookerByContract(contract);
    modelAndView.addObject("contract", contract);
    modelAndView.addObject("cooker", cooker);
    return modelAndView;
  }

  // customer
  @GetMapping("/add_customer/{ownerId}")
  public String addCustomerPage(@PathVariable(name = "ownerId") int id,
                                Model model) {
    Customer customer = new Customer();
    Owner owner = restaurantDao.findOwnerById(id);
    model.addAttribute("customer", customer);
    model.addAttribute("owner", owner);
    return "owner_new_customer";
  }

  @PostMapping(value = "/save_customer")
  public String saveCustomer(@ModelAttribute("customer") Customer customer) {
    restaurantDao.addCustomer(customer);
    return "redirect:/customer/" + customer.getUserName();
  }

  @PostMapping(value = "/owner_save_customer")
  public String saveCustomerByOwner(@ModelAttribute("customer") Customer customer) {
    restaurantDao.addCustomer(customer);
    return "redirect:/all_customers/admin";
  }

  @GetMapping("owner_edit_customer/{id}/{ownerUsername}")
  public ModelAndView editCustomerByOwner(@PathVariable(name = "id") int id,
                                          @PathVariable(name = "ownerUsername") String ownerUsername) {
    ModelAndView modelAndView = new ModelAndView("owner_update_customer");
    Customer customer = restaurantDao.findCustomerById(id);
    Owner owner = restaurantDao.findOwnerByUname(ownerUsername);
    modelAndView.addObject("customer", customer);
    modelAndView.addObject("owner", owner);
    return modelAndView;
  }

  @GetMapping("edit_customer/{id}")
  public ModelAndView editCustomerByUser(@PathVariable(name = "id") int id) {
    ModelAndView modelAndView = new ModelAndView("customer_update_profile");
    Customer customer = restaurantDao.findCustomerById(id);
    modelAndView.addObject("customer", customer);
    return modelAndView;
  }

  @GetMapping(value = "/delete_customer/{id}")
  public String deleteCustomer(@PathVariable(name = "id") int id) {
    restaurantDao.deleteCustomerById(id);
    return "redirect:/all_customers/admin";
  }

  // address
  @GetMapping("address/{personId}/{ownerUsername}")
  public String getAddressByUserId(@PathVariable(name = "personId") int id,
                                   @PathVariable(name = "ownerUsername") String ownerUsername,
                                   Model model) {
    List<Address> addresses = restaurantDao.getAddressByPersonId(id);
    Owner owner = restaurantDao.findOwnerByUname(ownerUsername);
    model.addAttribute("addresses", addresses);
    model.addAttribute("owner", owner);
    return "owner_user_address";
  }

  @GetMapping("add_address/{personId}")
  public String addAddress(@PathVariable(name = "personId") int id, Model model) {
    Person person = restaurantDao.findPersonById(id);
    Address address = new Address();
    person.addAddress(address);
    address.setPerson(person);
    model.addAttribute("address", address);
    return "new_address";
  }

  @GetMapping("add_address/{personId}/{ownerUsername}")
  public String addAddressByOwner(@PathVariable(name = "personId") int id,
                                  @PathVariable(name = "ownerUsername") String ownerUsername,
                                  Model model) {
    Person person = restaurantDao.findPersonById(id);
    Owner owner = restaurantDao.findOwnerByUname(ownerUsername);
    Address address = new Address();
    person.addAddress(address);
    address.setPerson(person);
    model.addAttribute("address", address);
    model.addAttribute("owner", owner);
    return "owner_new_address";
  }

  @GetMapping("edit_address/{id}")
  public ModelAndView goEditAddress(@PathVariable(name = "id") int id) {
    ModelAndView modelAndView = new ModelAndView("update_address");
    Address address = restaurantDao.findAddressById(id);
    modelAndView.addObject("address", address);
    return modelAndView;
  }

  @GetMapping("owner_edit_address/{addressId}/{ownerUsername}")
  public ModelAndView editAddressByOwner(@PathVariable(name = "addressId") int addressId,
                                         @PathVariable(name = "ownerUsername") String ownerUsername) {
    ModelAndView modelAndView = new ModelAndView("owner_update_address");
    Address address = restaurantDao.findAddressById(addressId);
    Owner owner = restaurantDao.findOwnerByUname(ownerUsername);
    modelAndView.addObject("address", address);
    modelAndView.addObject("owner", owner);
    return modelAndView;
  }

  @PostMapping(value = "/owner_save_address")
  public String saveAddressByOwner(@ModelAttribute("address") Address address) {
    restaurantDao.saveAddress(address);
    return "redirect:/address/" + address.getPerson().getId() + "/admin";
  }

  @PostMapping(value = "/save_address")
  public String saveAddress(@ModelAttribute("address") Address address) {
    restaurantDao.saveAddress(address);
    Person person = restaurantDao.findPersonById(address.getPerson().getId());
    return "redirect:/" + person.getRole().toString() + "/" + person.getUserName();
  }

  @RequestMapping(value = "/owner_delete_address/{id}")
  public String deleteAddressByOwner(@PathVariable(name = "id") int id) {
    Address address = restaurantDao.findAddressById(id);
    Person person = restaurantDao.findPersonById(address.getPerson().getId());
    restaurantDao.deleteAddressById(id);
    return "redirect:/address/" + person.getId() + "/admin";
  }

  @RequestMapping(value = "/delete_address/{id}")
  public String deleteAddress(@PathVariable(name = "id") int id) {
    Address address = restaurantDao.findAddressById(id);
    Person person = restaurantDao.findPersonById(address.getPerson().getId());
    restaurantDao.deleteAddressById(id);
    return "redirect:/" + person.getRole().toString() + "/" + person.getUserName();
  }

  // phone
  @GetMapping("phone/{personId}/{ownerUsername}")
  public String getPhoneByUserId(@PathVariable(name = "personId") int id,
                                 @PathVariable(name = "ownerUsername") String ownerUsername,
                                 Model model) {
    List<Phone> phones = restaurantDao.getPhoneByPersonId(id);
    Owner owner = restaurantDao.findOwnerByUname(ownerUsername);
    model.addAttribute("phones", phones);
    model.addAttribute("owner", owner);
    return "owner_user_phones";
  }

  @GetMapping("add_phone/{personId}/{ownerUsername}")
  public String addPhoneByOwner(@PathVariable(name = "personId") int id,
                                @PathVariable(name = "ownerUsername") String ownerUsername,
                                Model model) {
    Person person = restaurantDao.findPersonById(id);
    Owner owner = restaurantDao.findOwnerByUname(ownerUsername);
    Phone phone = new Phone();
    phone.setPerson(person);
    model.addAttribute("phone", phone);
    model.addAttribute("owner", owner);
    return "owner_new_phone";
  }

  @GetMapping("add_phone/{personId}")
  public String addPhone(@PathVariable(name = "personId") int id, Model model) {
    Person person = restaurantDao.findPersonById(id);
    Phone phone = new Phone();
    phone.setPerson(person);
    model.addAttribute("phone", phone);
    return "new_phone";
  }

  @GetMapping("owner_edit_phone/{phoneId}/{ownerUsername}")
  public ModelAndView editPhoneByOwner(@PathVariable(name = "phoneId") int phoneId,
                                       @PathVariable(name = "ownerUsername") String ownerUsername) {
    ModelAndView modelAndView = new ModelAndView("owner_update_phone");
    Phone phone = restaurantDao.findPhoneById(phoneId);
    Owner owner = restaurantDao.findOwnerByUname(ownerUsername);
    modelAndView.addObject("phone", phone);
    modelAndView.addObject("owner", owner);
    return modelAndView;
  }

  @GetMapping("edit_phone/{id}")
  public ModelAndView goEditPhone(@PathVariable(name = "id") int id) {
    ModelAndView modelAndView = new ModelAndView("update_phone");
    Phone phone = restaurantDao.findPhoneById(id);
    modelAndView.addObject("phone", phone);
    return modelAndView;
  }

  @PostMapping(value = "/owner_save_phone")
  public String savePhoneByOwner(@ModelAttribute("phone") Phone phone) {
    restaurantDao.savePhone(phone);
    return "redirect:/phone/" + phone.getPerson().getId() + "/admin";
  }

  @PostMapping(value = "/save_phone")
  public String savePhone(@ModelAttribute("phone") Phone phone) {
    restaurantDao.savePhone(phone);
    Person person = restaurantDao.findPersonById(phone.getPerson().getId());
    return "redirect:/" + person.getRole().toString() + "/" + person.getUserName();
  }

  @RequestMapping(value = "/owner_delete_phone/{id}")
  public String deletePhoneByOwner(@PathVariable(name = "id") int id) {
    Phone phone = restaurantDao.findPhoneById(id);
    Person person = restaurantDao.findPersonById(phone.getPerson().getId());
    restaurantDao.deletePhoneById(id);
    return "redirect:/phone/" + person.getId() + "/admin";
  }

  @GetMapping(value = "/delete_phone/{id}")
  public String deletePhone(@PathVariable(name = "id") int id) {
    Phone phone = restaurantDao.findPhoneById(id);
    Person person = restaurantDao.findPersonById(phone.getPerson().getId());
    restaurantDao.deletePhoneById(id);
    return "redirect:/" + person.getRole().toString() + "/" + person.getUserName();
  }

  // cooker
  @GetMapping("/all_cookers/{ownerUsername}")
  public String listAllCookers(@PathVariable(name = "ownerUsername") String ownerUsername,
                               Model model) {
    List<Cooker> cookers = restaurantDao.findAllCookers();
    Owner owner = restaurantDao.findOwnerByUname(ownerUsername);
    model.addAttribute("cookers", cookers);
    model.addAttribute("owner", owner);
    return "owner_all_cookers";
  }

  @GetMapping("/add_cooker/{ownerId}")
  public String goAddCookerPage(@PathVariable(name = "ownerId") int id,
                                Model model) {
    Cooker cooker = new Cooker();
    model.addAttribute("cooker", cooker);
    Owner owner = restaurantDao.findOwnerById(id);
    model.addAttribute("owner", owner);
    return "owner_new_cooker";
  }

  @PostMapping(value = "/save_cooker")
  public String saveCooker(@ModelAttribute("cooker") Cooker cooker) {
    restaurantDao.addCooker(cooker);
    return "redirect:/cooker/" + cooker.getUserName();
  }

  @PostMapping(value = "/owner_save_cooker")
  public String saveCookerByOwner(@ModelAttribute("cooker") Cooker cooker) {
    restaurantDao.addCooker(cooker);
    return "redirect:/all_cookers/admin";
  }

  @PostMapping(value = "/save_owner")
  public String saveOwner(@ModelAttribute("owner") Owner owner) {
    restaurantDao.saveOwner(owner);
    return "redirect:/owner/admin";
  }

  @GetMapping("contract/{id}/{ownerUsername}")
  public String getCookerContract(@PathVariable(name = "id") int id,
                                  @PathVariable(name = "ownerUsername") String ownerUsername,
                                  Model model) {
    Contract contract = restaurantDao.findContractById(id);
    Owner owner = restaurantDao.findOwnerByUname(ownerUsername);
    model.addAttribute("contract", contract);
    model.addAttribute("owner", owner);
    return "owner_cooker_contract";
  }

  @GetMapping("cooker_contract/{id}")
  public String getCookerContract(@PathVariable(name = "id") int id, Model model) {
    Cooker cooker = restaurantDao.findCookerById(id);
    Contract contract = cooker.getContract();
    model.addAttribute("contract", contract);
    model.addAttribute("cooker", cooker);
    return "cooker_contract";
  }

  @GetMapping("owner_edit_cooker/{id}/{ownerUsername}")
  public ModelAndView editCookerByOwner(@PathVariable(name = "id") int id,
                                        @PathVariable(name = "ownerUsername") String ownerUsername) {
    ModelAndView modelAndView = new ModelAndView("owner_update_cooker");
    Cooker cooker = restaurantDao.findCookerById(id);
    Owner owner = restaurantDao.findOwnerByUname(ownerUsername);
    modelAndView.addObject("cooker", cooker);
    modelAndView.addObject("owner", owner);
    return modelAndView;
  }

  @GetMapping("/edit_owner_profile/{id}")
  public ModelAndView editOwnerProfile(@PathVariable(name = "id") int id) {
    ModelAndView modelAndView = new ModelAndView("owner_update_profile");
    Owner owner = restaurantDao.findOwnerById(id);
    modelAndView.addObject("owner", owner);
    return modelAndView;
  }

  @GetMapping("/edit_cooker/{id}")
  public ModelAndView editCookerByUser(@PathVariable(name = "id") int id) {
    ModelAndView modelAndView = new ModelAndView("cooker_update_profile");
    Cooker cooker = restaurantDao.findCookerById(id);
    modelAndView.addObject("cooker", cooker);
    return modelAndView;
  }

  @GetMapping(value = "/delete_cooker/{id}")
  public String deleteCooker(@PathVariable(name = "id") int id) {
    restaurantDao.deleteCookerById(id);
    return "redirect:/all_cookers/admin";
  }

  @GetMapping("manager/{cookerId}/{ownerUsername}")
  public String getManagerByCookerId(@PathVariable(name = "cookerId") int id,
                                     @PathVariable(name = "ownerUsername") String ownerUsername,
                                     Model model) {
    Cooker manager = restaurantDao.getManagerByCookerId(id);
    Owner owner = restaurantDao.findOwnerByUname(ownerUsername);
    model.addAttribute("manager", manager);
    model.addAttribute("owner", owner);
    return "owner_cooker_manager";
  }

  @GetMapping("cooker_manager/{cookerId}")
  public String getManagerByCookerId(@PathVariable(name = "cookerId") int id, Model model) {
    Cooker manager = restaurantDao.getManagerByCookerId(id);
    Cooker cooker = restaurantDao.findCookerById(id);
    model.addAttribute("manager", manager);
    model.addAttribute("cooker", cooker);
    return "cooker_manager";
  }

  @GetMapping("/assign_manager/{cookerId}/{ownerUsername}")
  public ModelAndView assignToManagerPage(@PathVariable(name = "cookerId") int id,
                                          @PathVariable(name = "ownerUsername") String ownerUsername) {
    ModelAndView modelAndView = new ModelAndView("owner_assign_to_manager");
    Cooker cooker = restaurantDao.findCookerById(id);
    Owner owner = restaurantDao.findOwnerByUname(ownerUsername);
    modelAndView.addObject("cooker", cooker);
    modelAndView.addObject("owner", owner);
    return modelAndView;
  }

  @PostMapping(value = "/save_assigned_manager")
  public String saveAssignedManager(@ModelAttribute("cooker") Cooker cooker) {
    Cooker manager = cooker.getManager();
    Cooker subordinate = restaurantDao.findCookerById(cooker.getId());
    subordinate.setManager(manager);
    restaurantDao.addCooker(subordinate);
    return "redirect:/all_cookers/admin";
  }

  @GetMapping("/subordinates/{id}/{ownerUsername}")
  public String getCookerSubordinates(@PathVariable(name = "id") int id,
                                      @PathVariable(name = "ownerUsername") String ownerUsername,
                                      Model model) {
    List<Cooker> subordinates = restaurantDao.findSubordinateByMId(id);
    Owner owner = restaurantDao.findOwnerByUname(ownerUsername);
    model.addAttribute("owner", owner);
    model.addAttribute("subordinates", subordinates);
    return "owner_subordinates";
  }

  @GetMapping("/cooker_subordinate/{id}")
  public String goSubordinatesPage(@PathVariable(name = "id") int id, Model model) {
    List<Cooker> subordinates = restaurantDao.findSubordinateByMId(id);
    Cooker cooker = restaurantDao.findCookerById(id);
    model.addAttribute("subordinates", subordinates);
    model.addAttribute("cooker", cooker);
    return "cooker_subordinates";
  }

  @GetMapping(value = "/remove_subordinate/{subordinateId}")
  public String removeSubordinateByCooker(@PathVariable(name = "subordinateId") int id) {
    Cooker subordinate = restaurantDao.findCookerById(id);
    int managerId = subordinate.getManager().getId();
    subordinate.setManager(null);
    restaurantDao.addCooker(subordinate);
    return "redirect:/cooker_subordinate/" + managerId;
  }

  @GetMapping(value = "/remove_subordinate/{subordinateId}/{username}")
  public String removeRelationship(@PathVariable(name = "subordinateId") int id,
                                   @PathVariable(name = "username") String username) {
    Cooker cooker = restaurantDao.findCookerById(id);
    int managerId = cooker.getManager().getId();
    cooker.setManager(null);
    restaurantDao.addCooker(cooker);
    Person person = restaurantDao.findUserByUsername(username);
    return "redirect:/subordinates/" + managerId + "/" + person.getUserName();
  }

  // foodItem
  @GetMapping("/create_food/{menuId}")
  public String goCreateFoodPage(@PathVariable(name = "menuId") int menuId, Model model) {
    FoodItem food = new FoodItem();
    Menu menu = restaurantDao.findMenuById(menuId);
    food.setMenu(menu);
    model.addAttribute("food", food);
    return "owner_new_food";
  }

  @PostMapping(value = "/save_food")
  public String saveFood(@ModelAttribute("food") FoodItem food) {
    restaurantDao.CreateFoodItem(food);
    Menu menu = restaurantDao.findMenuById(food.getMenu().getId());
    return "redirect:/owner/menu/" + food.getMenu().getId() + "/" + menu.getCreatorId();
  }

  @GetMapping("/edit_food/{foodId}/{menuId}/{ownerId}")
  public ModelAndView goEditFoodPage(@PathVariable(name = "foodId") int foodId,
                                     @PathVariable(name = "menuId") int menuId,
                                     @PathVariable(name = "ownerId") int ownerId) {
    ModelAndView modelAndView = new ModelAndView("owner_update_food");
    FoodItem foodItem = restaurantDao.findFoodById(foodId);
    Menu menu = restaurantDao.findMenuById(menuId);
    Owner owner = restaurantDao.findOwnerById(ownerId);
    modelAndView.addObject("food", foodItem);
    modelAndView.addObject("menu", menu);
    modelAndView.addObject("owner", owner);
    return modelAndView;
  }

  @GetMapping(value = "/delete_food/{foodId}/{menuId}/{ownerId}")
  public String deleteFood(@PathVariable(name = "foodId") int foodId,
                           @PathVariable(name = "menuId") int menuId,
                           @PathVariable(name = "ownerId") int ownerId) {
    restaurantDao.deleteFoodById(foodId);
    return "redirect:/owner/menu/" + menuId + "/" + ownerId;
  }

  // foodReviews
  @GetMapping("/food_reviews/{foodId}/{menuId}/{ownerId}")
  public String getFoodReviews(@PathVariable(name = "menuId") int menuId,
                               @PathVariable(name = "ownerId") int ownerId,
                               @PathVariable(name = "foodId") int id, Model model) {
    List<FoodReview> foodReviews = restaurantDao.findReviewByFoodId(id);
    Menu menu = restaurantDao.findMenuById(menuId);
    Owner owner = restaurantDao.findOwnerById(ownerId);
    model.addAttribute("foodReviews", foodReviews);
    model.addAttribute("owner", owner);
    model.addAttribute("menu", menu);
    return "owner_food_reviews";
  }

  @GetMapping("/write_food_review/{foodId}/{menuId}/{ownerId}")
  public String writeReviewForFoodByOwner(@PathVariable(name = "menuId") int menuId,
                                          @PathVariable(name = "ownerId") int ownerId,
                                          @PathVariable(name = "foodId") int foodId, Model model) {
    FoodReview foodReview = new FoodReview();
    foodReview = restaurantDao.writeReviewForFood(foodReview, foodId);
    Menu menu = restaurantDao.findMenuById(menuId);
    Owner owner = restaurantDao.findOwnerById(ownerId);
    model.addAttribute("foodReview", foodReview);
    model.addAttribute("owner", owner);
    model.addAttribute("menu", menu);
    return "owner_new_food_review";
  }

  @PostMapping("/save_review_owner")
  public String saveFoodReviewByOwner(@ModelAttribute("foodReview") FoodReview foodReview) {
    restaurantDao.saveFoodReview(foodReview);
    int foodId = foodReview.getFoodItem().getId();
    FoodItem foodItem = restaurantDao.findFoodById(foodId);
    int menuId = foodItem.getMenu().getId();
    int ownerId = foodItem.getMenu().getCreatorId();
    return "redirect:/food_reviews/" + foodId + "/" + menuId + "/" + ownerId;
  }

  @PostMapping("/save_review")
  public String saveFoodReviewByCustomer(@ModelAttribute("foodReview") FoodReview foodReview) {
    restaurantDao.saveFoodReview(foodReview);
    return "redirect:/customer_foodreview/" + foodReview.getCustomer().getId();
  }


  @GetMapping(value = "/delete_review/{customerId}/{reviewId}")
  public String deleteReviewByCustomer(@PathVariable(name = "customerId") int customerId, @PathVariable(name = "reviewId") int reviewId) {
    restaurantDao.deleteReviewById(reviewId);
    return "redirect:/customer_foodreview/" + customerId;
  }

  @GetMapping(value = "/delete_review/{reviewId}/{menuId}/{ownerId}")
  public String deleteReviewByOwner(@PathVariable(name = "ownerId") int ownerId,
                                    @PathVariable(name = "reviewId") int reviewId,
                                    @PathVariable(name = "menuId") int menuId) {
    FoodReview foodReview = restaurantDao.findReviewById(reviewId);
    int foodId = foodReview.getFoodItem().getId();
    restaurantDao.deleteReviewById(reviewId);
    return "redirect:/food_reviews/" + foodId + "/" + menuId + "/" + ownerId;
  }

  @GetMapping("/customer_foodreview/{customerId}")
  public String gorFoodReviews(@PathVariable(name = "customerId") int id, Model model) {
    Collection<FoodReview> foodReviews = restaurantDao.findReviewByCustomerId(id);
    model.addAttribute("foodReviews", foodReviews);
    Customer customer = restaurantDao.findCustomerById(id);
    model.addAttribute("customer", customer);
    return "customer_food_reviews";
  }

  @GetMapping("/owner_customer_foodreview/{customerId}/{ownerUsername}")
  public String goCustomerFoodReviews(@PathVariable(name = "customerId") int id,
                                      @PathVariable(name = "ownerUsername") String ownerUsername,
                                      Model model) {
    Collection<FoodReview> foodReviews = restaurantDao.findReviewByCustomerId(id);
    model.addAttribute("foodReviews", foodReviews);
    Owner owner = restaurantDao.findOwnerByUname(ownerUsername);
    model.addAttribute("owner", owner);
    return "owner_customer_food_reviews";
  }


  @GetMapping("/edit_review/{id}")
  public ModelAndView goEditMyFoodReview(@PathVariable(name = "id") int id) {
    ModelAndView modelAndView = new ModelAndView("customer_edit_foodreview");
    FoodReview foodReview = restaurantDao.findReviewById(id);
    modelAndView.addObject("foodReview", foodReview);
    return modelAndView;
  }

}
