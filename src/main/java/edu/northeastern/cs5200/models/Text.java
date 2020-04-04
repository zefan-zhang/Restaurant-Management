//package edu.northeastern.cs5200.models;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "texts")
//public class Text {
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  private int id;
//  private String message;
//  private int cookerId;
//  private int ownerId;
//  private int customerId;
//
//  public Text() {
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
//  public int getOwnerId() {
//    return ownerId;
//  }
//
//  public void setOwnerId(int ownerId) {
//    this.ownerId = ownerId;
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
//  public int getId() {
//    return id;
//  }
//
//  public void setId(int id) {
//    this.id = id;
//  }
//
//  public String getMessage() {
//    return message;
//  }
//
//  public void setMessage(String message) {
//    this.message = message;
//  }
//}
