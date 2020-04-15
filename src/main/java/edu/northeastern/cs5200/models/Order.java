package edu.northeastern.cs5200.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
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
@Table(name ="orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private Timestamp timePlaced;
  private double totalPrice;

  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;

  @OneToMany(mappedBy = "order")
  private Collection<WishList> wishLists;

  @ManyToOne()
  @JsonIgnore
  private Cooker cooker;

  @ManyToOne()
  @JsonIgnore
  private Customer customer;

  public Order() {
  }

  public OrderStatus getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(OrderStatus orderStatus) {
    this.orderStatus = orderStatus;
  }

  public Collection<WishList> getWishLists() {
    return wishLists;
  }

  public void setWishLists(Collection<WishList> wishLists) {
    this.wishLists = wishLists;
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


}
