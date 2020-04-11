package edu.northeastern.cs5200.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "phones")
public class Phone {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String number;
  private boolean primaryPhone;
  @ManyToOne
  @JsonIgnore
  private Person person;

  public Phone() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String phone) {
    this.number = phone;
  }

  public boolean isPrimaryPhone() {
    return primaryPhone;
  }

  public void setPrimaryPhone(boolean primaryPhone) {
    this.primaryPhone = primaryPhone;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }
}


