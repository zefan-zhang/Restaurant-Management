package edu.northeastern.cs5200.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
  private Cooker cooker;

  @ManyToOne
  @JsonIgnore
  private Customer customer;

  private String senderUsername;

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
    return cooker;
  }

  public void setCooker(Cooker cooker) {
    this.cooker = cooker;
  }

  public String getSenderUsername() {
    return senderUsername;
  }

  public void setSenderUsername(String senderUsername) {
    this.senderUsername = senderUsername;
  }
}

