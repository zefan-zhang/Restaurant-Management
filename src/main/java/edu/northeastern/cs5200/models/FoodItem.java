package edu.northeastern.cs5200.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "foodItems")
public class FoodItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;

  @Enumerated(EnumType.STRING)
  private FoodCategory category;
  private double price;
  private int inventory;
  private String description;


  @ManyToOne()
  @JsonIgnore
  private Menu menu;

  @OneToMany(mappedBy = "foodItem")
  private Collection<FoodReview> foodReviews;

  @OneToMany(mappedBy = "foodItem")
  private Collection<WishList> wishLists;

  public FoodItem() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public FoodCategory getCategory() {
    return category;
  }

  public void setCategory(FoodCategory category) {
    this.category = category;
  }

  public void setMenu(Menu menu) {
    this.menu = menu;
  }

  public Menu getMenu() {
    return menu;
  }

  public Collection<WishList> getWishLists() {
    return wishLists;
  }

  public void setWishLists(Collection<WishList> wishLists) {
    this.wishLists = wishLists;
  }

  public Collection<FoodReview> getFoodReviews() {
    return foodReviews;
  }

  public void setFoodReviews(Collection<FoodReview> foodReviews) {
    this.foodReviews = foodReviews;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getInventory() {
    return inventory;
  }

  public void setInventory(int inventory) {
    this.inventory = inventory;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
