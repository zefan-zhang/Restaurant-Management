package edu.northeastern.cs5200.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.models.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Integer> {
  @Query("SELECT p FROM Owner p WHERE p.username =:userName And p.password =:password")
  Owner findOwnerByUnamePword(@Param("userName") String userName, @Param("password") String password);

  @Query("SELECT p FROM Owner p WHERE p.username =:userName")
  Owner findOwnerByUname(@Param("userName") String userName);
}
