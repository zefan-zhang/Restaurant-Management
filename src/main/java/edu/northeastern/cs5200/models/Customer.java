package edu.northeastern.cs5200.models;

import javax.persistence.Entity;

@Entity
public class Customer extends Person {

  private boolean userAgreement;
  private boolean VIP;
  private String paymentMethod;

  public Customer() {
  }

  public boolean isUserAgreement() {
    return userAgreement;
  }

  public void setUserAgreement(boolean userAgreement) {
    this.userAgreement = userAgreement;
  }

  public boolean isVIP() {
    return VIP;
  }

  public void setVIP(boolean VIP) {
    this.VIP = VIP;
  }

  public String getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }
}
