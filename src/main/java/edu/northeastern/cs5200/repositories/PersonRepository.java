package edu.northeastern.cs5200.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.models.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {
  @Query("SELECT p FROM Person p WHERE p.username =:userName And p.password =:password")
  Person findPersonByUnamePword(@Param("userName") String userName, @Param("password") String password);

  @Query("SELECT p FROM Person p WHERE p.username =:userName")
  Person findPersonByUname(@Param("userName") String userName);
}
