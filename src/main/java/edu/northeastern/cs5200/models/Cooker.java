package edu.northeastern.cs5200.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Cooker extends Person{
  private int level;
  private String responsibility;

  public Cooker() {
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
