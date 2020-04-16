package edu.northeastern.cs5200.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "texts")
public class Text {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String message;
  @ManyToOne
  @JsonIgnore
  private Cooker sender;

  @ManyToOne
  @JsonIgnore
  private Customer receiver;

  private Timestamp createdTime;

  public Text() {
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

  public Cooker getCooker() {
    return sender;
  }

  public void setCooker(Cooker cooker) {
    this.sender = cooker;
  }

  public Customer getCustomer() {
    return receiver;
  }

  public void setCustomer(Customer customer) {
    this.receiver = customer;
  }

  public Timestamp getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Timestamp receivedTime) {
    this.createdTime = receivedTime;
  }
}

