package edu.northeastern.cs5200.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fooditems")
public class FoodItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  private FoodCategory category;
  private int menuId;
  private int orderListId;

  public FoodItem() {
  }

  public int getMenuId() {
    return menuId;
  }

  public int getOrderListId() {
    return orderListId;
  }

  public void setOrderListId(int orderListId) {
    this.orderListId = orderListId;
  }

  public void setMenuId(int menuId) {
    this.menuId = menuId;
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
}
