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
@Table(name = "shopping_carts")
public class ShoppingCart {
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

  public ShoppingCart() {
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
}
