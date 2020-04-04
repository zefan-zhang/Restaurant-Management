//package edu.northeastern.cs5200.models;
//
//import java.sql.Time;
//import java.util.Collection;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//@Entity
//@Table(name ="orders")
//public class Order {
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  private int id;
//  private Time orderTime;
//  private double totalPrice;
//  private String instruction;
//  private OrderStatus status;
////  private Collection<OrderList> orderLists;
//  private int cookerId;
//  private int customerId;
//
//  public Order() {
//  }
//
//  public int getCustomerId() {
//    return customerId;
//  }
//
//  public void setCustomerId(int customerId) {
//    this.customerId = customerId;
//  }
//
//  public int getCookerId() {
//    return cookerId;
//  }
//
//  public void setCookerId(int cookerId) {
//    this.cookerId = cookerId;
//  }
//
////  public Collection<OrderList> getOrderLists() {
////    return orderLists;
////  }
////
////  public void setOrderLists(Collection<OrderList> orderLists) {
////    this.orderLists = orderLists;
////  }
//
//  public int getId() {
//    return id;
//  }
//
//  public void setId(int id) {
//    this.id = id;
//  }
//
//  public Time getOrderTime() {
//    return orderTime;
//  }
//
//  public void setOrderTime(Time orderTime) {
//    this.orderTime = orderTime;
//  }
//
//  public double getTotalPrice() {
//    return totalPrice;
//  }
//
//  public void setTotalPrice(double totalPrice) {
//    this.totalPrice = totalPrice;
//  }
//
//  public String getInstruction() {
//    return instruction;
//  }
//
//  public void setInstruction(String instruction) {
//    this.instruction = instruction;
//  }
//
//  public OrderStatus getStatus() {
//    return status;
//  }
//
//  public void setStatus(OrderStatus status) {
//    this.status = status;
//  }
//}
