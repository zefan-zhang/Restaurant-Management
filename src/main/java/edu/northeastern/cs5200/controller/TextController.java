package edu.northeastern.cs5200.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import edu.northeastern.cs5200.daos.RestaurantDao;
import edu.northeastern.cs5200.daos.TextDao;
import edu.northeastern.cs5200.models.Cooker;
import edu.northeastern.cs5200.models.Customer;
import edu.northeastern.cs5200.models.Owner;
import edu.northeastern.cs5200.models.Text;

@Controller
public class TextController {

  @Autowired
  private TextDao textDao;

  @Autowired
  private RestaurantDao restaurantDao;

  @GetMapping("/cooker/message/{cookerId}")
  public String getMessagesByCooker(@PathVariable(name = "cookerId") int id,
                            Model model) {
    List<Text> sentTexts = textDao.findSentTextByCookerId(id);
    Cooker cooker = restaurantDao.findCookerById(id);
    model.addAttribute("sentTexts", sentTexts);
    model.addAttribute("cooker", cooker);
    return "cooker_message";
  }

  @GetMapping("/customer/message/{customerId}")
  public String getMessagesByCustomer(@PathVariable(name = "customerId") int id,
                            Model model) {
    List<Text> receivedTexts = textDao.findReceivedTextByCustomerId(id);
    Customer customer = restaurantDao.findCustomerById(id);
    model.addAttribute("receivedTexts", receivedTexts);
    model.addAttribute("customer", customer);
    return "customer_message";
  }

  @GetMapping("/owner/message/{ownerUsername}")
  public String getAllMessages(@PathVariable(name = "ownerUsername") String ownerUsername, Model model) {
    List<Text> texts = textDao.findAllTexts();
    Owner owner = restaurantDao.findOwnerByUname(ownerUsername);
    model.addAttribute("texts", texts);
    model.addAttribute("owner", owner);
    return "owner_all_messages";
  }

  @GetMapping("/message/{cookerId}")
  public String sendMessage(@PathVariable(name = "cookerId")int id, Model model) {
    Cooker cooker = restaurantDao.findCookerById(id);
    Text text = new Text();
    text.setCooker(cooker);
    model.addAttribute("text", text);
    model.addAttribute("cooker", cooker);
    return "cooker_new_message";
  }

  @PostMapping(value = "/send_message")
  public String sendMessage(@ModelAttribute("text") Text text) {
    textDao.saveText(text);
    return "redirect:/cooker/message/" + text.getCooker().getId();
  }

  @GetMapping(value = "cooker/delete_message/{messageId}")
  public String deleteMessageByCooker(@PathVariable(name = "messageId") int id) {
    Text text = textDao.findTextById(id);
    int cookerId = text.getCooker().getId();
    text.setCooker(null);
    textDao.saveText(text);
    return "redirect:/cooker/message/" + cookerId;
  }

  @GetMapping(value = "customer/delete_message/{messageId}")
  public String deleteMessageByCustomer(@PathVariable(name = "messageId") int id) {
    Text text = textDao.findTextById(id);
    int customerId = text.getCustomer().getId();
    text.setCustomer(null);
    textDao.saveRemoveCustomerText(text);
    return "redirect:/customer/message/" + customerId;
  }

  @GetMapping(value = "owner/delete_message/{messageId}")
  public String deleteMessageByOwner(@PathVariable(name = "messageId") int id) {
    textDao.deleteMessageById(id);
    return "redirect:/owner/message/admin";
  }

  @GetMapping(value = "/delete_all_message")
  public String truncateAllMessages() {
    textDao.truncateMessage();
    return "redirect:/owner/message/admin";
  }

}
