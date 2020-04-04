package edu.northeastern.cs5200.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.sql.Date;
import java.util.Collection;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.Collection;

@Entity
@Table(name = "cookers")
public class Cooker extends Person{
  private String responsibility;
  private Date employmentDate;
  private double salary;
  @OneToMany(mappedBy = "cooker")
  private Collection<Text> texts;
  @OneToMany(mappedBy = "cooker")
  private Collection<Order> orders;

  public Cooker() {
  }

  public String getResponsibility() {
    return responsibility;
  }

  public void setResponsibility(String responsibility) {
    this.responsibility = responsibility;
  }

  public Date getEmploymentDate() {
    return employmentDate;
  }

  public void setEmploymentDate(Date employmentDate) {
    this.employmentDate = employmentDate;
  }

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
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
}
