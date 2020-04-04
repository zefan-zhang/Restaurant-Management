package edu.northeastern.cs5200.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "menus")
public class Menu {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;


  @OneToMany(mappedBy = "menu")
  private Collection<FoodItem> foodItems;

  @ManyToOne
  @JsonIgnore
  private Customer customer;

  public Menu() {
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

  public Customer getCustomers() {
    return customer;
  }

  public void setCustomers(Customer customers) {
    this.customer = customers;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}