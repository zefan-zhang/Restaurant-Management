package edu.northeastern.cs5200.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.northeastern.cs5200.models.Text;

public interface TextRepository extends CrudRepository<Text, Integer> {
}
