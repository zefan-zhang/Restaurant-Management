package edu.northeastern.cs5200.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

import edu.northeastern.cs5200.models.Text;

public interface TextRepository extends CrudRepository<Text, Integer> {

  @Query("SELECT t FROM Text t WHERE t.receiverUsername=:receiverUsername")
  List<Text> findTextsByReceiverUsername(@Param("receiverUsername") String receiverUsername);
}
