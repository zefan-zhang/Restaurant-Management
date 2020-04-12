package edu.northeastern.cs5200.daos;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import edu.northeastern.cs5200.models.Address;
import edu.northeastern.cs5200.models.Contract;
import edu.northeastern.cs5200.models.Cooker;
import edu.northeastern.cs5200.models.Customer;
import edu.northeastern.cs5200.models.FoodItem;
import edu.northeastern.cs5200.models.Menu;
import edu.northeastern.cs5200.models.Person;
import edu.northeastern.cs5200.models.Phone;
import edu.northeastern.cs5200.repositories.AddressRepository;
import edu.northeastern.cs5200.repositories.ContractRepository;
import edu.northeastern.cs5200.repositories.CookerRepository;
import edu.northeastern.cs5200.repositories.CustomerRepository;
import edu.northeastern.cs5200.repositories.FoodItemRepository;
import edu.northeastern.cs5200.repositories.MenuRepository;
import edu.northeastern.cs5200.repositories.PersonRepository;
import edu.northeastern.cs5200.repositories.PhoneRepository;

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
    customerRepository.deleteById(id);
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

}
