package edu.northeastern.cs5200.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "menu")
public class Menu {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String itemName;
  private double price;

  @OneToMany(mappedBy = "menu")
  private Collection<FoodItem> foodItems;

  @OneToOne()
  private Customer customer;

  public Menu() {
  }

  public void setCustomer(Customer customer){
    this.customer = customer;
  }

  public Customer getCustomer(){
    return customer;
  }

  public Collection<FoodItem> getFoodItems() {
    return foodItems;
  }

  public void setFoodItems(Collection<FoodItem> foodItems) {
    this.foodItems = foodItems;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }
}
