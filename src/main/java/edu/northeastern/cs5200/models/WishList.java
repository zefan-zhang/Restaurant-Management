package edu.northeastern.cs5200.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "wish_list")
public class WishList {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private int quantity;

  @ManyToOne
  @JsonIgnore
  private FoodItem foodItem;

  @ManyToOne
  @JsonIgnore
  private Order order;

  @ManyToOne()
  @JsonIgnore
  private Customer customer;

  public WishList() {
  }


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public FoodItem getFoodItem() {
    return foodItem;
  }

  public void setFoodItem(FoodItem foodItem) {
    this.foodItem = foodItem;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }
}
