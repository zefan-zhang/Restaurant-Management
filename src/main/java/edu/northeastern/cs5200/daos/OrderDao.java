package edu.northeastern.cs5200.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import edu.northeastern.cs5200.models.Cooker;
import edu.northeastern.cs5200.models.Customer;
import edu.northeastern.cs5200.models.Order;
import edu.northeastern.cs5200.models.WishList;
import edu.northeastern.cs5200.repositories.OrderRepository;
import edu.northeastern.cs5200.repositories.WishListRepository;

@Service
public class OrderDao {

  @Autowired
  OrderRepository orderRepository;

  @Autowired
  WishListRepository wishListRepository;

  @Autowired
  RestaurantDao restaurantDao;

  public List<Order> findAllOrders() {
    return (List<Order>) orderRepository.findAll();
  }

  public List<Order> findOrdersByCustomerId(int id) {
    return orderRepository.findOrderByCustomerId(id);
  }
  public Order findOrderById(int id) {
    Optional<Order> optional = orderRepository.findById(id);
    if (optional.isPresent()) {
      return optional.get();
    }
    return null;
  }

  public Order saveOrder(Order order) {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    order.setTimeModified(timestamp);
    return orderRepository.save(order);
  }

  public Order updateOrder(Order order) {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    order.setTimeModified(timestamp);
    List<WishList> wishLists = wishListRepository.findWishListByOrderId(order.getId());
    order.setWishLists(wishLists);
    if (order.getTotalPrice() == 0) {
      double totalPrice = 0;
      for (WishList wishList : wishLists) {
        totalPrice += wishList.getTotalPrice();
      }
      order.setTotalPrice(totalPrice);
    }
    Customer customer = restaurantDao.findCustomerById(order.getCustomer().getId());
    order.setCustomer(customer);
    if (order.getCooker() != null) {
      Cooker cooker = restaurantDao.findCookerById(order.getCooker().getId());
      order.setCooker(cooker);
    }
    return orderRepository.save(order);
  }

  public void deleteOrder(Order order) {
    order.setCustomer(null);
    order.setCooker(null);
    Collection<WishList> wishLists = order.getWishLists();
    for (WishList wishList : wishLists) {
     wishList.setOrder(null);
     wishListRepository.save(wishList);
   }

    orderRepository.save(order);
    orderRepository.delete(order);
  }
}
