package edu.northeastern.cs5200.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.Collection;

@Entity
@Table(name = "customer")
public class Customer extends Person {

  private boolean userAgreement;
  private boolean VIP;
  private String paymentMethod;

  @OneToMany(mappedBy = "customer")
  private Collection<Menu> menus;

  @OneToMany(mappedBy = "customer")
  private Collection<Order> orders;

  @OneToMany(mappedBy = "customer")
  private Collection<Text> texts;


  public Customer() {
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

  public Collection<Menu> getMenus() {
    return menus;
  }

  public void setMenus(Collection<Menu> menus) {
    this.menus = menus;
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
