package edu.northeastern.cs5200.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contract")
public class Contract {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private Date employmentDate;
  private double salary;
  private String title;

  @OneToOne()
  private Cooker cooker;

  @OneToOne()
  private Owner owner;

  public Contract() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Cooker getCooker() {
    return cooker;
  }

  public void setCooker(Cooker cooker) {
    this.cooker = cooker;
  }

  public void setOwner(Owner owner) {
    this.owner = owner;
  }

  public Owner getOwner() {
    return owner;
  }
}
