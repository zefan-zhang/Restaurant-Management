package edu.northeastern.cs5200.controller;

import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import edu.northeastern.cs5200.daos.RestaurantDao;
import edu.northeastern.cs5200.daos.TextDao;
import edu.northeastern.cs5200.models.Cooker;
import edu.northeastern.cs5200.models.Text;

@Controller
public class TextController {

  @Autowired
  private TextDao textDao;

  @Autowired
  private RestaurantDao restaurantDao;

  @GetMapping("/cooker/message/{username}")
  public String getMessages(@PathVariable(name = "username") String username,
                            Model model) {
    List<Text> texts =textDao.findTextByReceiverUsername(username);
    Cooker cooker = restaurantDao.findCookerByUname(username);
    model.addAttribute("texts", texts);
    model.addAttribute("cooker", cooker);
    return "cooker_message";

  }


}
