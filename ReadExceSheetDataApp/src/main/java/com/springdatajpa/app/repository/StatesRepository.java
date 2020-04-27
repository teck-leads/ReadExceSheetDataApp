package com.springdatajpa.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.springdatajpa.app.model.States;

public interface StatesRepository extends JpaRepository<States, Integer>{

}
