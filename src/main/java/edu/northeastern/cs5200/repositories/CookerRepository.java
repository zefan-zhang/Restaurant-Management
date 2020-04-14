package edu.northeastern.cs5200.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

import edu.northeastern.cs5200.models.Cooker;

public interface CookerRepository extends CrudRepository<Cooker, Integer> {

  @Query("SELECT s FROM Cooker s WHERE s.manager.id =:id")
  Collection<Cooker> findSubordinateByMId(@Param("id") int id);

  @Query("SELECT p FROM Cooker p WHERE p.username =:userName And p.password =:password")
  Cooker findCookerByUnamePword(@Param("userName") String userName, @Param("password") String password);

  @Query("SELECT p FROM Cooker p WHERE p.username =:userName")
  Cooker findCookerByUname(@Param("userName") String userName);
}
