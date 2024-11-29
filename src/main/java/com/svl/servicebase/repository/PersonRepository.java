package com.svl.servicebase.repository;

import com.svl.servicebase.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
