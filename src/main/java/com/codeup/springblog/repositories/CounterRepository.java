package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Counter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounterRepository extends JpaRepository<Counter, Long> {

}
