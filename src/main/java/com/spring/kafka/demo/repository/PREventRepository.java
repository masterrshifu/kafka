package com.spring.kafka.demo.repository;

import com.spring.kafka.demo.model.PREvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PREventRepository extends CrudRepository<PREvent, Long> {
}