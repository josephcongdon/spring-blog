package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Counter;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CounterRepository extends JpaRepository<Counter, Long> {
    @Override
    <S extends Counter> Optional<S> findOne(Example<S> example);
}
