package edu.northeastern.cs5200.models;

import java.sql.Time;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name ="order")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private Time orderTime;
  private double totalPrice;
  private String instruction;
  private OrderStatus status;
  private OrderList orderList;

  @ManyToOne()
  private Cooker cooker;
  @ManyToOne()
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

  public Time getOrderTime() {
    return orderTime;
  }

  public void setOrderTime(Time orderTime) {
    this.orderTime = orderTime;
  }

  public double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }

  public String getInstruction() {
    return instruction;
  }

  public void setInstruction(String instruction) {
    this.instruction = instruction;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
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
}
