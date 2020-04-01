package edu.northeastern.cs5200.models;

import javax.persistence.Entity;
import java.util.Collection;

@Entity
public class Owner extends Person{

  private int id;
  private Collection<Text> texts;
  private int contractId;

  public Owner() {
  }

  public int getContractId() {
    return contractId;
  }

  public void setContractId(int contractId) {
    this.contractId = contractId;
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
