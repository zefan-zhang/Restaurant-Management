package edu.northeastern.cs5200;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import java.sql.Date;
import java.util.List;

import edu.northeastern.cs5200.daos.RestaurantDao;
import edu.northeastern.cs5200.daos.TextDao;
import edu.northeastern.cs5200.models.Cooker;
import edu.northeastern.cs5200.models.Customer;
import edu.northeastern.cs5200.models.Owner;
import edu.northeastern.cs5200.models.RoleType;
import edu.northeastern.cs5200.models.Text;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSuit {

  @Autowired
  private RestaurantDao restaurantDao;

  @Autowired
  private TextDao textDao;

  /*@Test
  public void test(){
    restaurantDao.truncate();

    //create admin
    Owner admin = new Owner(RoleType.owner, "Nineteen", "Team", "admin", "admin", "female", "team19@neu.edu", new Date(2020,04,01));
    restaurantDao.saveOwner(admin);

    //create cooker
    Cooker cooker = new Cooker(RoleType.cooker, "Alice", "Wonder","alice","alice","female","alice@neu.edu", new Date(2020,03,03));
    restaurantDao.addCooker(cooker);

    //create customer
    Customer customer = new Customer(RoleType.customer, "Bob", "Hope", "bob", "bob", "male", "bob@neu.edu", new Date(2020,02,02));
    restaurantDao.addCustomer(customer);
  }*/

  //admin creates customer
  @Test
  public void testOwnerCreateCustomer(){
    restaurantDao.truncate();
    Owner admin = new Owner(RoleType.owner, "Nineteen", "Team", "admin", "admin", "female", "team19@neu.edu", new Date(2020,04,01));
    restaurantDao.saveOwner(admin);

    assertEquals(restaurantDao.findOwnerByUname("admin").getRole(), RoleType.owner);
    Customer customer = new Customer(RoleType.customer, "Bob", "Hope", "bob", "bob", "male", "bob@neu.edu", new Date(2020,02,02));
    restaurantDao.addCustomer(customer);
  }

  //admin creates cooker
  @Test
  public void testOwnerCreateCooker(){
    restaurantDao.truncate();
    Owner admin = new Owner(RoleType.owner, "Nineteen", "Team", "admin", "admin", "female", "team19@neu.edu", new Date(2020,04,01));
    restaurantDao.saveOwner(admin);

    assertEquals(restaurantDao.findOwnerByUname("admin").getRole(), RoleType.owner);
    Cooker cooker = new Cooker(RoleType.cooker, "Alice", "Wonder","alice","alice","female","alice@neu.edu", new Date(2020,03,03));
    restaurantDao.addCooker(cooker);
  }


  //admin finds all customers
  @Test
  public void testOwnerFindAllCustomer() {
    restaurantDao.truncate();
    Owner admin = new Owner(RoleType.owner, "Nineteen", "Team", "admin", "admin", "female", "team19@neu.edu", new Date(2020,04,01));
    restaurantDao.saveOwner(admin);

    Customer customer = new Customer(RoleType.customer, "Bob", "Hope", "bob", "bob", "male", "bob@neu.edu", new Date(2020,02,02));
    restaurantDao.addCustomer(customer);

    assertEquals(restaurantDao.findOwnerByUname("admin").getRole(), RoleType.owner);
    List<Customer> customers = restaurantDao.findAllCustomer();
    assertEquals(customers.size(),1);
  }

  //admin finds all cookers
  @Test
  public void testOwnerFindAllCooker(){
    restaurantDao.truncate();
    Owner admin = new Owner(RoleType.owner, "Nineteen", "Team", "admin", "admin", "female", "team19@neu.edu", new Date(2020,04,01));
    restaurantDao.saveOwner(admin);

    Cooker cooker = new Cooker(RoleType.cooker, "Alice", "Wonder","alice","alice","female","alice@neu.edu", new Date(2020,03,03));
    restaurantDao.addCooker(cooker);

    assertEquals(restaurantDao.findOwnerByUname("admin").getRole(), RoleType.owner);
    List<Cooker> cookers = restaurantDao.findAllCookers();
    assertEquals(cookers.size(),1);
  }

  //admin edits customer
  @Test
  public void testOwnerEditCustomer(){
    restaurantDao.truncate();
    Customer customer = new Customer(RoleType.customer, "Bob", "Hope", "bob", "bob", "male", "bob@neu.edu", new Date(2020,02,02));
    restaurantDao.addCustomer(customer);

    Owner admin = new Owner(RoleType.owner, "Nineteen", "Team", "admin", "admin", "female", "team19@neu.edu", new Date(2020,04,01));
    restaurantDao.saveOwner(admin);

    assertEquals(restaurantDao.findOwnerByUname("admin").getRole(), RoleType.owner);
    List<Customer> customers = restaurantDao.findAllCustomer();
    for (int i = 0; i < customers.size(); i++){
      Customer c = customers.get(i);
      Customer c2 = restaurantDao.findCustomerById(c.getId());
      c2.setEmail("bobb@neu.edu");
      restaurantDao.addCustomer(c2);
    }

    List<Customer> customers2 = restaurantDao.findAllCustomer();
    for (int i = 0; i < customers2.size(); i++){
      Customer c = customers.get(i);
      Customer c2 = restaurantDao.findCustomerById(c.getId());
      assertEquals(c2.getEmail(), "bobb@neu.edu");
    }
  }

  //admin edits cooker
  @Test
  public void testOwnerEditCooker(){
    restaurantDao.truncate();
    Cooker cooker = new Cooker(RoleType.cooker, "Alice", "Wonder","alice","alice","female","alice@neu.edu", new Date(2020,03,03));
    restaurantDao.addCooker(cooker);

    Owner admin = new Owner(RoleType.owner, "Nineteen", "Team", "admin", "admin", "female", "team19@neu.edu", new Date(2020,04,01));
    restaurantDao.saveOwner(admin);

    assertEquals(restaurantDao.findOwnerByUname("admin").getRole(), RoleType.owner);
    List<Cooker> cookers = restaurantDao.findAllCookers();
    for (int i = 0; i < cookers.size(); i++){
      Cooker c = cookers.get(i);
      Cooker c2 = restaurantDao.findCookerById(c.getId());
      c2.setEmail("aliceeee@neu.edu");
      restaurantDao.addCooker(c2);
    }

    List<Cooker> cookers2 = restaurantDao.findAllCookers();
    for (int i = 0; i < cookers2.size(); i++){
      Cooker c = cookers.get(i);
      Cooker c2 = restaurantDao.findCookerById(c.getId());
      assertEquals(c2.getEmail(), "aliceeee@neu.edu");
    }
  }


  /*@Test
  public void testOwnerRemoveCustomer(){
    assertEquals(restaurantDao.findOwnerByUname("admin").getRole(), RoleType.owner);
    List<Customer> customers = restaurantDao.findAllCustomer();
    for (int i = 0; i < customers.size(); i++){
      Customer c = customers.get(i);
      restaurantDao.deleteCustomerById(c.getId());
    }
    assertEquals(restaurantDao.findAllCustomer().size(),0);
  }

  /*@Test
  public void testOwnerRemoveCooker(){
    assertEquals(restaurantDao.findOwnerByUname("admin").getRole(), RoleType.owner);
    List<Cooker> cookers = restaurantDao.findAllCookers();
    for (int i = 0; i < cookers.size(); i++){
      Cooker c = cookers.get(i);
      restaurantDao.deleteCookerById(c.getId());
    }
    assertEquals(restaurantDao.findAllCookers().size(),0);
  }*/

  //user to user relationship
  @Test
  public void testCookerSendMessageToCustomer() {
    restaurantDao.truncate();

    Cooker cooker = new Cooker(RoleType.cooker, "Alice", "Wonder", "alice", "alice", "female", "alice@neu.edu", new Date(2020, 03, 03));
    restaurantDao.addCooker(cooker);

    Customer customer = new Customer(RoleType.customer, "Bob", "Hope", "bob", "bob", "male", "bob@neu.edu", new Date(2020, 02, 02));
    restaurantDao.addCustomer(customer);

    Text text = new Text();
    text.setMessage("Dinner is ready");
    text.setCooker(cooker);
    text.setCustomer(customer);
    textDao.saveText(text);
    List<Text> texts = textDao.findReceivedTextByCustomerId(customer.getId());
    List<Text> texts2 = textDao.findSentTextByCookerId(cooker.getId());

    String message1 = "";
    for (int i = 0; i < texts.size(); i++){
      message1 = texts.get(i).getMessage();
    }

    String message2 = "";
    for (int i = 0; i < texts2.size(); i++){
      message2 = texts2.get(i).getMessage();
    }

    assertEquals(message1, message2);
  }





}
