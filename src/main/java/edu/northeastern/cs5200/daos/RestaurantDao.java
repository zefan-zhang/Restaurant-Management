package edu.northeastern.cs5200.daos;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import edu.northeastern.cs5200.models.Address;
import edu.northeastern.cs5200.models.Contract;
import edu.northeastern.cs5200.models.Cooker;
import edu.northeastern.cs5200.models.Customer;
import edu.northeastern.cs5200.models.FoodItem;
import edu.northeastern.cs5200.models.FoodReview;
import edu.northeastern.cs5200.models.Menu;
import edu.northeastern.cs5200.models.Owner;
import edu.northeastern.cs5200.models.Person;
import edu.northeastern.cs5200.models.Phone;
import edu.northeastern.cs5200.models.WishList;
import edu.northeastern.cs5200.repositories.AddressRepository;
import edu.northeastern.cs5200.repositories.ContractRepository;
import edu.northeastern.cs5200.repositories.CookerRepository;
import edu.northeastern.cs5200.repositories.CustomerRepository;
import edu.northeastern.cs5200.repositories.FoodItemRepository;
import edu.northeastern.cs5200.repositories.FoodReviewRepository;
import edu.northeastern.cs5200.repositories.MenuRepository;
import edu.northeastern.cs5200.repositories.OwnerRepository;
import edu.northeastern.cs5200.repositories.PersonRepository;
import edu.northeastern.cs5200.repositories.PhoneRepository;
import edu.northeastern.cs5200.repositories.WishListRepository;

@Service
public class OwnerDao {
  @Autowired
  private FoodItemRepository foodItemRepository;

  @Autowired
  private CookerRepository cookerRepository;

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private ContractRepository contractRepository;

  @Autowired
  private MenuRepository menuRepository;

  @Autowired
  private AddressRepository addressRepository;

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private PhoneRepository phoneRepository;

  @Autowired
  private FoodReviewRepository foodReviewRepository;

  @Autowired
  private OwnerRepository ownerRepository;

  @Autowired
  private WishListRepository wishListRepository;

  // login
  public Person findUserByUnameAndPword(String username, String password) {
    return personRepository.findPersonByUnamePword(username, password);
  }

  // owner
  public Owner findOwnerById(int id) {
    Optional<Owner> optional = ownerRepository.findById(id);
    if (optional.isPresent()) {
      return optional.get();
    }
    return null;
  }

  public void saveOwner(Owner owner) {
    ownerRepository.save(owner);
  }

  public Owner findOwnerByUnamePword(String username, String password) {
    return ownerRepository.findOwnerByUnamePword(username, password);
  }

  public Owner findOwnerByUname(String username) {
    return ownerRepository.findOwnerByUname(username);
  }

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

  public List<FoodItem> findFoodsByMenuId(int id) {
    return (List<FoodItem>) foodItemRepository.findFoodItemByMenuId(id);
  }

  public void deleteMenuById(int id) {
    Menu menu = this.findMenuById(id);
    List<FoodItem> foodItems = (List<FoodItem>) menu.getFoodItems();
    for (FoodItem foodItem : foodItems) {
      this.deleteFoodById(foodItem.getId());
    }
    menuRepository.deleteById(id);
  }

  // address
  public List<Address> getAddressByPersonId(int id) {
    return (List<Address>) addressRepository.findAddressByPersonId(id);
  }

  public Address findAddressById(int id) {
    Optional<Address> optional = addressRepository.findById(id);
    if (optional.isPresent()) {
      return optional.get();
    }
    return null;
  }

  public void saveAddress(Address address) {
    addressRepository.save(address);
  }

  public void deleteAddressById(int id) {
    addressRepository.deleteById(id);
  }

  // phone
  public List<Phone> getPhoneByPersonId(int id) {
    return (List<Phone>) phoneRepository.findPhoneByPersonId(id);
  }

  public Phone findPhoneById(int id) {
    Optional<Phone> optional = phoneRepository.findById(id);
    if (optional.isPresent()) {
      return optional.get();
    }
    return null;
  }

  public void savePhone(Phone phone) {
    phoneRepository.save(phone);
  }

  public void deletePhoneById(int id) {
    phoneRepository.deleteById(id);
  }

  // person
  public Person findPersonById(int id) {
    Optional<Person> optional = personRepository.findById(id);
    if (optional.isPresent()) {
      return optional.get();
    }
    return null;
  }

  public void savePerson(Person person){
    personRepository.save(person);
  }

  // cooker
  public List<Cooker> findAllCookers() {
    return (List<Cooker>) cookerRepository.findAll();
  }

  public void addCooker(Cooker cooker) {
    cookerRepository.save(cooker);
  }

  public Cooker findCookerById(int id) {
    Optional<Cooker> optional = cookerRepository.findById(id);
    if (optional.isPresent()) {
      return optional.get();
    }
    return null;
  }

  public Cooker getManagerByCookerId(int id){
    Cooker cooker = this.findCookerById(id);
    Cooker manager = cooker.getManager();
    return manager;
  }

  public void deleteUserPhoneAndAdd(int id) {
    List<Phone> phones = this.getPhoneByPersonId(id);
    for (Phone phone : phones) {
      phoneRepository.delete(phone);
    }
    List<Address> addresses = this.getAddressByPersonId(id);
    for (Address address : addresses) {
      addressRepository.delete(address);
    }
  }

  public void deleteCookerById(int id) {
    Cooker cooker = this.findCookerById(id);
    this.deleteContractById(cooker.getContract().getId());
    this.deleteUserPhoneAndAdd(id);
    cooker.setManager(null);
    cookerRepository.save(cooker);
    List<Cooker> subordinates = (List<Cooker>) cooker.getSubordinates();
    if (!subordinates.isEmpty()) {
      for (Cooker s : subordinates) {
        s.setManager(null);
        cookerRepository.save(s);
      }
    }
    cookerRepository.deleteById(id);
  }

  public List<Cooker> findSubordinateByMId(int id) {
    return (List<Cooker>) cookerRepository.findSubordinateByMId(id);
  }

  public Cooker findCookerByUnamePword(String username, String password) {
    return cookerRepository.findCookerByUnamePword(username, password);
  }

  public Cooker findCookerByUname(String username) {
    return cookerRepository.findCookerByUname(username);
  }

  // customer
  public List<Customer> findAllCustomer() {
    return (List<Customer>) customerRepository.findAll();
  }

  public void addCustomer(Customer customer) {
    customerRepository.save(customer);
  }

  public Customer findCustomerById(int id) {
    Optional<Customer> optional = customerRepository.findById(id);
    if (optional.isPresent()) {
      return optional.get();
    }
    return null;
  }

  public void deleteCustomerById(int id) {
    this.deleteUserPhoneAndAdd(id);
    Customer customer = this.findCustomerById(id);
    List<FoodReview> foodReviews = (List<FoodReview>) this.findReviewByCustomerId(id);
    for (FoodReview foodReview : foodReviews) {
      this.deleteReviewById(foodReview.getId());
    }
    customerRepository.deleteById(id);
  }

  public Customer findCustomerByUnamePword(String username, String password) {
    return customerRepository.findCustomerByUnamePword(username, password);
  }

  public Customer findCustomerByUname(String username) {
    return customerRepository.findCustomerByUname(username);
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
    FoodItem foodItem = this.findFoodById(id);
    Collection<FoodReview> foodReviews = foodItem.getFoodReviews();
    for (FoodReview review : foodReviews) {
      this.deleteReviewById(review.getId());
    }
    foodItemRepository.deleteById(id);
  }

  // reviews
  public void writeReview(FoodReview foodReview) {
    foodReviewRepository.save(foodReview);
  }

  public List<FoodReview> findReviewByFoodId(int id) {
    return (List<FoodReview>) foodReviewRepository.findReviewByFoodId(id);
  }

  public FoodReview writeReviewForFood(FoodReview foodReview, int foodId) {
    FoodItem foodItem = this.findFoodById(foodId);
    foodReview.setFoodItem(foodItem);
    return foodReview;
  }

  public void saveFoodReview(FoodReview foodReview) {
    foodReviewRepository.save(foodReview);
  }

  public FoodReview findReviewById(int id) {
    Optional<FoodReview> optional= foodReviewRepository.findById(id);
    if (optional.isPresent()) {
      return optional.get();
    }
    return null;
  }

  public Collection<FoodReview> findReviewByCustomerId(int id) {
    return foodReviewRepository.findReviewByCustomerId(id);
  }

  public void deleteReviewById(int id) {
    FoodReview foodReview = this.findReviewById(id);
    foodReview.setFoodItem(null);
    foodReview.setCustomer(null);
    this.saveFoodReview(foodReview);
    foodReviewRepository.delete(foodReview);
  }

  // shopping cart
  public List<WishList> findWishListByCustomerId(int id){
    return wishListRepository.findWishListByCustomerId(id);
  }

}
