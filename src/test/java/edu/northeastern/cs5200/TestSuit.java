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
import edu.northeastern.cs5200.models.Customer;
import edu.northeastern.cs5200.models.Owner;
import edu.northeastern.cs5200.models.RoleType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSuit {

  @Autowired
  private RestaurantDao restaurantDao;

  @Test
  public void test(){
    restaurantDao.truncate();

    //create admin
    Owner admin = new Owner(RoleType.owner, "Nineteen", "Team", "admin", "admin", "female", "team19@neu.edu", new Date(2020,04,01));
    restaurantDao.saveOwner(admin);

    //create cooker
    //Cooker cooker = new Cooker(RoleType.cooker, "Alice", "Wonder","alice","alice","female","alice@neu.edu", new Date(2020,03,03));
    //restaurantDao.addCooker(cooker);
  }

  @Test
  public void testOwnerCreateCustomer(){
    Customer customer = new Customer(RoleType.customer, "Bob", "Hope", "bob", "bob", "male", "bob@neu.edu", new Date(2020,02,02));
    restaurantDao.addCustomer(customer);
  }


  @Test
  public void testOwnerFindAllCustomer() {
    List<Customer> customers = restaurantDao.findAllCustomer();
    assertEquals(customers.size(),1);
  }

  @Test
  public void testOwnerEditCustomer(){
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

  @Test
  public void testOwnerRemoveCustomer(){
    List<Customer> customers = restaurantDao.findAllCustomer();
    for (int i = 0; i < customers.size(); i++){
      Customer c = customers.get(i);
      restaurantDao.deleteCustomerById(c.getId());
    }
    assertEquals(restaurantDao.findAllCustomer().size(),0);
  }
}
