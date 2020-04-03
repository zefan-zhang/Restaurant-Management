package edu.northeastern.cs5200.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "foodItem")
public class FoodItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  private FoodCategory category;

  @ManyToOne()
  private Menu menu;

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
}
