package edu.northeastern.cs5200.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "text")
public class Text {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String message;
  private Cooker cooker;
  private Owner owner;
  private Customer customer;

  public Text() {
  }

  public Cooker getCooker() {
    return cooker;
  }

  public void setCooker(Cooker cooker) {
    this.cooker = cooker;
  }

  public Owner getOwner() {
    return owner;
  }

  public void setOwner(Owner owner) {
    this.owner = owner;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
