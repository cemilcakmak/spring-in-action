package com.spring.tacocloud.repository;

import com.spring.tacocloud.domain.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {

    Taco save(Taco design);
}