package edu.northeastern.cs5200.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.Collection;

@Entity
@Table(name = "owner")
public class Owner extends Person{


  public Owner() {
  }
}
