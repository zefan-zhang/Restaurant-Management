package edu.northeastern.cs5200.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contracts")
public class Contract {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private Date hiredDate;
  private double salary;

  @OneToOne
  private Cooker cooker;

  public Contract() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getHiredDate() {
    return hiredDate;
  }

  public void setHiredDate(Date hiredDate) {
    this.hiredDate = hiredDate;
  }

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  public Cooker getCooker() {
    return cooker;
  }

  public void setCooker(Cooker cooker) {
    this.cooker = cooker;
  }
}
