package edu.northeastern.cs5200.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.thymeleaf.standard.expression.Fragment;

import java.util.Collection;

import javax.persistence.Entity;
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
  private FoodCategory category;

  @ManyToOne()
  @JsonIgnore
  private Menu menu;

  @OneToMany(mappedBy = "foodItem")
  private Collection<FoodReview> foodReviews;

  @ManyToOne()
  private OrderList orderList;

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

  public void setOrderList(OrderList orderList){
    this.orderList = orderList;
  }

  public OrderList getOrderList() {
    return orderList;
  }

  public Collection<FoodReview> getFoodReviews() {
    return foodReviews;
  }

  public void setFoodReviews(Collection<FoodReview> foodReviews) {
    this.foodReviews = foodReviews;
  }


}
