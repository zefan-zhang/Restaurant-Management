package edu.northeastern.cs5200.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import edu.northeastern.cs5200.models.Customer;
import edu.northeastern.cs5200.models.Text;
import edu.northeastern.cs5200.repositories.CustomerRepository;
import edu.northeastern.cs5200.repositories.TextRepository;

@Service
public class TextDao {

  @Autowired
  TextRepository textRepository;

  @Autowired
  CustomerRepository customerRepository;

  public List<Text> findSentTextByCookerId(int id) {
    return textRepository.findSentTextsByCookerId(id);
  }

  public List<Text> findReceivedTextByCustomerId(int id) {
    return textRepository.findReceivedTextsByCustomerId(id);
  }

  public Text findTextById(int id) {
    Optional<Text> optional = textRepository.findById(id);
    if (optional.isPresent()) {
      return optional.get();
    }
    return null;
  }

  public List<Text> findAllTexts() {
    return (List<Text>) textRepository.findAll();
  }

  public void saveText(Text text) {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    text.setCreatedTime(timestamp);
    Customer customer = customerRepository.findCustomerByUname(text.getCustomer().getUserName());
    text.setCustomer(customer);
    textRepository.save(text);
  }

  public void saveRemoveCustomerText(Text text) {
    textRepository.save(text);
  }

  public void deleteMessageById(int id) {
    textRepository.deleteById(id);
  }

  public void truncateMessage() {
    textRepository.deleteAll();
  }

}
