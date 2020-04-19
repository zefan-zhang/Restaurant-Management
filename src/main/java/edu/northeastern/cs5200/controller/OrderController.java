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

  @GetMapping("/owner/create_order/{username}")
  public String createOrderByOwner(@PathVariable(name = "username")String username,
                                   Model model) {
    Order order = new Order();
    Owner owner = restaurantDao.findOwnerByUname(username);
    model.addAttribute("order", order);
    model.addAttribute("owner", owner);
    return "owner_create_order";
  }

  @PostMapping(value = "/owner/save_order")
  public String saveOrderByOwner(@ModelAttribute("order") Order order) {
    orderDao.saveOrder(order);
    return "redirect:/owner/orders/admin";
  }

  @PostMapping(value = "/owner/save_update_order")
  public String saveUpdateOrderByOwner(@ModelAttribute("order") Order order) {
    orderDao.updateOrder(order);
    return "redirect:/owner/orders/admin";
  }

  @PostMapping(value = "/cooker/save_update_order/{username}")
  public String saveUpdateOrderByOwner(@PathVariable(name = "username") String username,
                                       @ModelAttribute("order") Order order) {
    orderDao.updateOrder(order);
    return "redirect:/cooker/orders/" + username;
  }

  @GetMapping("/edit_order/{orderId}/{username}")
  public String editOrderByOwner(@PathVariable(name = "orderId") int orderId,
                                 @PathVariable(name = "username") String username,
                                 Model model){
    Order order = orderDao.findOrderById(orderId);
    Owner owner = restaurantDao.findOwnerByUname(username);
    model.addAttribute("order", order);
    model.addAttribute("owner", owner);
    return "onwer_update_order";
  }

  @GetMapping("/cooker/edit_order/{orderId}/{username}")
  public String editOrderByCooker(@PathVariable(name = "orderId") int orderId,
                                 @PathVariable(name = "username") String username,
                                 Model model){
    Order order = orderDao.findOrderById(orderId);
    Cooker cooker = restaurantDao.findCookerByUname(username);
    model.addAttribute("order", order);
    model.addAttribute("cooker", cooker);
    return "cooker_update_order";
  }

  @GetMapping(value = "delete_order/{orderId}")
  public String deleteOrderByOwner(@PathVariable(name = "orderId") int id) {
    Order order = orderDao.findOrderById(id);
    orderDao.deleteOrder(order);
    return "redirect:/owner/orders/admin";
  }


}
