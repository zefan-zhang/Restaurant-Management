package edu.northeastern.cs5200.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name ="orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private Timestamp timePlaced;
  private double totalPrice;

  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;
  @OneToOne
  private OrderList orderList;

  @ManyToOne()
  @JsonIgnore
  private Cooker cooker;

  @ManyToOne()
  @JsonIgnore
  private Customer customer;

  public Order() {
  }

  public OrderList getOrderLists() {
    return orderList;
  }

  public void setOrderLists(OrderList orderList) {
    this.orderList = orderList;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Timestamp getTimePlaced() {
    return timePlaced;
  }

  public void setTimePlaced(Timestamp timePlaced) {
    this.timePlaced = timePlaced;
  }

  public double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }

  public OrderStatus getStatus() {
    return this.orderStatus;
  }

  public void setStatus(OrderStatus status) {
    this.orderStatus = status;
  }

  public void setCooker(Cooker cooker) {
    this.cooker = cooker;
  }

  public Cooker getCooker() {
    return cooker;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Customer getCustomer() {
    return customer;
  }

  public OrderList getOrderList() {
    return orderList;
  }
  public void setOrderList(OrderList orderList) {
    this.orderList = orderList;
  }
}
