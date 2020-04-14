package edu.northeastern.cs5200.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.sql.Date;
import java.util.Collection;

@Entity
public class Owner extends Person{


  public Owner() {
  }

  public Owner(RoleType role, String fistName, String lastName, String username, String password, String gender, String email, Date dob) {
    super(role, fistName, lastName, username, password, gender, email, dob);
  }


}
