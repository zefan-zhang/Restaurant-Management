package edu.northeastern.cs5200.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.sql.Date;
import java.util.Collection;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.util.Collection;

@Entity
public class Cooker extends Person{
  private int level;
  private String responsibility;
  @OneToMany(mappedBy = "cooker")
  private Collection<Text> texts;
  @OneToMany(mappedBy = "cooker")
  private Collection<Order> orders;

  @ManyToOne
  private Cooker manager;

  @OneToMany(mappedBy = "manager")
  private Collection<Cooker> subordinates;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn
  private Contract contract;

  public Cooker() {
    super();
  }

  public Cooker(RoleType role, String fistName, String lastName, String username, String password, String gender, String email, Date dob) {
    super(role, fistName, lastName, username, password, gender, email, dob);
  }

  public String getResponsibility() {
    return responsibility;
  }

  public void setResponsibility(String responsibility) {
    this.responsibility = responsibility;
  }

  public Collection<Text> getTexts() {
    return texts;
  }

  public void setTexts(Collection<Text> texts) {
    this.texts = texts;
  }

  public Collection<Order> getOrders() {
    return orders;
  }

  public void setOrders(Collection<Order> orders) {
    this.orders = orders;
  }

  public Contract getContract() {
    return contract;
  }

  public void setContract(Contract contract) {
    this.contract = contract;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public Cooker getManager() {
    return manager;
  }

  public void setManager(Cooker manager) {
    this.manager = manager;
  }

  public Collection<Cooker> getSubordinates() {
    return subordinates;
  }

  public void addSubordinate(Cooker cooker) {
    this.subordinates.add(cooker);
  }

  public void setSubordinates(Collection<Cooker> subordinates) {
    this.subordinates = subordinates;
  }
}
