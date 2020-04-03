package edu.northeastern.cs5200.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.Collection;

@Entity
@Table(name = "cooker")
public class Cooker extends Person{
  private int level;
  private String responsibility;

  @OneToMany(mappedBy = "cooker")
  private Collection<Order> orders;

  @OneToMany(mappedBy = "cooker")
  private Collection<Text> texts;

  public Cooker() {
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

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public String getResponsibility() {
    return responsibility;
  }

  public void setResponsibility(String responsibility) {
    this.responsibility = responsibility;
  }
}
