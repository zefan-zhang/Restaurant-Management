package edu.northeastern.cs5200.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

import edu.northeastern.cs5200.models.Text;

public interface TextRepository extends CrudRepository<Text, Integer> {

  @Query("SELECT t FROM Text t WHERE t.sender.id=:id")
  List<Text> findSentTextsByCookerId(@Param("id") int id);

  @Query("SELECT t FROM Text t WHERE t.receiver.id =:id")
  List<Text> findReceivedTextsByCustomerId(@Param("id") int id);

  @Transactional
  @Modifying
  @Query("DELETE FROM Text r WHERE r.receiver.id =:id")
  void deleteByCustomerId(@Param("id") int id);

  @Transactional
  @Modifying
  @Query("DELETE FROM Text r WHERE r.sender.id =:id")
  void deleteByCookerId(@Param("id") int id);
}
