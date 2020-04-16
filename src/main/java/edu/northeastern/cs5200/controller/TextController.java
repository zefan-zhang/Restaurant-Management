package edu.northeastern.cs5200.controller;

import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import edu.northeastern.cs5200.daos.RestaurantDao;
import edu.northeastern.cs5200.daos.TextDao;
import edu.northeastern.cs5200.models.Cooker;
import edu.northeastern.cs5200.models.Person;
import edu.northeastern.cs5200.models.Text;

@Controller
public class TextController {

  @Autowired
  private TextDao textDao;

  @Autowired
  private RestaurantDao restaurantDao;

  @GetMapping("/cooker/message/{cookerId}")
  public String getMessages(@PathVariable(name = "cookerId") int id,
                            Model model) {
    List<Text> sentTexts = textDao.findSentTextByCookerId(id);
    Cooker cooker = restaurantDao.findCookerById(id);
    model.addAttribute("sentTexts", sentTexts);
    model.addAttribute("cooker", cooker);
    return "cooker_message";
  }

  @GetMapping("/message/{cookerId}")
  public String sendMessage(@PathVariable(name = "cookerId")int id, Model model) {
    Cooker cooker = restaurantDao.findCookerById(id);
    Text text = new Text();
    text.setCooker(cooker);
    model.addAttribute("text", text);
    model.addAttribute("cooker", cooker);
    return "new_message";
  }

  @PostMapping(value = "/send_message")
  public String sendMessage(@ModelAttribute("text") Text text) {
    textDao.saveText(text);
    return "redirect:/cooker/message/" + text.getCooker().getId();
  }

  @GetMapping(value = "cooker/delete_message/{messageId}")
  public String deleteMessage(@PathVariable(name = "messageId") int id) {
    Text text = textDao.findTextById(id);
    int cookerId = text.getCooker().getId();
    text.setCooker(null);
    textDao.saveText(text);
    return "redirect:/cooker/message/" + cookerId;
  }
}
