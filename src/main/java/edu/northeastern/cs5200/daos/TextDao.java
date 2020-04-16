package edu.northeastern.cs5200.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import edu.northeastern.cs5200.models.Text;
import edu.northeastern.cs5200.repositories.TextRepository;

@Service
public class TextDao {

  @Autowired
  TextRepository textRepository;

  public List<Text> findTextByReceiverUsername(String username) {
    return textRepository.findTextsByReceiverUsername(username);
  }
}
