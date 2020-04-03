package edu.northeastern.cs5200.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "foodReview")
public class FoodReview {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String review;

  @OneToOne()
  private Customer customer;

  @OneToOne()
  private FoodItem foodItem;

  public FoodReview() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getReview() {
    return review;
  }

  public void setReview(String review) {
    this.review = review;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Customer getCustomer() {
    return customer;
  }

  public FoodItem getFoodItem() {
    return foodItem;
  }

  public void setFoodItem(FoodItem foodItem) {
    this.foodItem = foodItem;
  }
}
