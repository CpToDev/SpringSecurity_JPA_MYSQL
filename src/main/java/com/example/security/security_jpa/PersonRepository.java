package com.example.security.security_jpa;

import com.example.security.security_jpa.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Integer> {

     Person getPersonByUsername(String username);
}
