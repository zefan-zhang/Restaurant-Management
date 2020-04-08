package edu.northeastern.cs5200.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

import edu.northeastern.cs5200.models.Cooker;

public interface CookerRepository extends CrudRepository<Cooker, Integer> {

  @Query("SELECT s FROM Cooker s WHERE s.manager.id =:id")
  Collection<Cooker> findSubordinateByMId(@Param("id") int id);
}
