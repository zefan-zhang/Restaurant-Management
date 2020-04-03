package edu.northeastern.cs5200.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.Collection;

@Entity
@Table(name = "owner")
public class Owner extends Person{
  private int id;

  @OneToMany(mappedBy = "owner")
  private Collection<Text> texts;

  public Owner() {
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public void setId(int id) {
    this.id = id;
  }

  public Collection<Text> getTexts() {
    return texts;
  }

  public void setTexts(Collection<Text> texts) {
    this.texts = texts;
  }
}
