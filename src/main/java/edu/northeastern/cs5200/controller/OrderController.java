package edu.northeastern.cs5200.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import edu.northeastern.cs5200.daos.OrderDao;
import edu.northeastern.cs5200.daos.RestaurantDao;
import edu.northeastern.cs5200.models.Cooker;
import edu.northeastern.cs5200.models.Customer;
import edu.northeastern.cs5200.models.Order;
import edu.northeastern.cs5200.models.Owner;

@Controller
public class OrderController {

  @Autowired
  private OrderDao orderDao;

  @Autowired
  private RestaurantDao restaurantDao;

  @GetMapping("/owner/orders/{username}")
  public String getAllOrders(@PathVariable(name = "username") String username,
                             Model model) {
    Owner owner = restaurantDao.findOwnerByUname(username);
    List<Order> orders = orderDao.findAllOrders();
    model.addAttribute("owner", owner);
    model.addAttribute("orders", orders);
    return "owner_orders";
  }

  @GetMapping("/cooker/orders/{username}")
  public String getAllOrdersByCooker(@PathVariable(name = "username") String username,
                                     Model model) {
    Cooker cooker = restaurantDao.findCookerByUname(username);
    List<Order> orders = orderDao.findAllOrders();
    model.addAttribute("cooker", cooker);
    model.addAttribute("orders", orders);
    return "cooker_orders";
  }

  @GetMapping("/customer/orders/{userId}")
  public String getAllOrdersByCooker(@PathVariable(name = "userId") int id,
                                     Model model) {
    Customer customer = restaurantDao.findCustomerById(id);
    List<Order> orders = orderDao.findOrdersByCustomerId(id);
    model.addAttribute("customer", customer);
    model.addAttribute("orders", orders);
    return "customer_order";
  }

  @GetMapping("/owner/create_order/{username}")
  public String createOrderByOwner(@PathVariable(name = "username") String username,
                                   Model model) {
    Order order = new Order();
    Owner owner = restaurantDao.findOwnerByUname(username);
    model.addAttribute("order", order);
    model.addAttribute("owner", owner);
    return "owner_create_order";
  }

  @GetMapping("/customer/create_order/{userId}")
  public String createOrderByCustomer(@PathVariable(name = "userId") int userId,
                                      Model model) {
    Order order = new Order();
    Customer customer = restaurantDao.findCustomerById(userId);
    order.setCustomer(customer);
    model.addAttribute("order", order);
    model.addAttribute("customer", customer);
    return "customer_create_order";
  }

  @PostMapping(value = "/owner/save_order")
  public String saveOrderByOwner(@ModelAttribute("order") Order order) {
    orderDao.saveOrder(order);
    return "redirect:/owner/orders/admin";
  }

  @PostMapping(value = "/customer/save_order")
  public String saveOrderByCustomer(@ModelAttribute("order") Order order) {
    orderDao.saveOrder(order);
    return "redirect:/customer/orders/" + order.getCustomer().getId();
  }

  @PostMapping(value = "/owner/save_update_order")
  public String saveUpdateOrderByOwner(@ModelAttribute("order") Order order) {
    orderDao.updateOrder(order);
    return "redirect:/owner/orders/admin";
  }

  @PostMapping(value = "/cooker/save_update_order/{username}")
  public String saveUpdateOrderByCooker(@PathVariable(name = "username") String username,
                                       @ModelAttribute("order") Order order) {
    orderDao.updateOrder(order);
    return "redirect:/cooker/orders/" + username;
  }

  @PostMapping(value = "/customer/save_update_order")
  public String saveUpdateOrderByCustomer(@ModelAttribute("order") Order order) {
    orderDao.updateOrder(order);
    return "redirect:/customer/orders/" + order.getCustomer().getId();
  }

  @GetMapping("/edit_order/{orderId}/{username}")
  public String editOrderByOwner(@PathVariable(name = "orderId") int orderId,
                                 @PathVariable(name = "username") String username,
                                 Model model) {
    Order order = orderDao.findOrderById(orderId);
    Owner owner = restaurantDao.findOwnerByUname(username);
    model.addAttribute("order", order);
    model.addAttribute("owner", owner);
    return "owner_update_order";
  }

  @GetMapping("/cooker/edit_order/{orderId}/{username}")
  public String editOrderByCooker(@PathVariable(name = "orderId") int orderId,
                                  @PathVariable(name = "username") String username,
                                  Model model) {
    Order order = orderDao.findOrderById(orderId);
    Cooker cooker = restaurantDao.findCookerByUname(username);
    model.addAttribute("order", order);
    model.addAttribute("cooker", cooker);
    return "cooker_update_order";
  }

  @GetMapping("/customer/edit_order/{orderId}")
  public String editOrderByCooker(@PathVariable(name = "orderId") int orderId,
                                  Model model) {
    Order order = orderDao.findOrderById(orderId);
    Customer customer = restaurantDao.findCustomerById(order.getCustomer().getId());
    model.addAttribute("order", order);
    model.addAttribute("customer", customer);
    return "customer_update_order";
  }

  @GetMapping(value = "delete_order/{orderId}")
  public String deleteOrderByOwner(@PathVariable(name = "orderId") int id) {
    Order order = orderDao.findOrderById(id);
    orderDao.deleteOrder(order);
    return "redirect:/owner/orders/admin";
  }

  @GetMapping(value = "delete_order/{orderId}/{userId}")
  public String deleteOrderByOwner(@PathVariable(name = "orderId") int orderId,
                                   @PathVariable(name = "userId") int userId) {
    Order order = orderDao.findOrderById(orderId);
    orderDao.deleteOrder(order);
    return "redirect:/customer/orders/" + userId;
  }


}
