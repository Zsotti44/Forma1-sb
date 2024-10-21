package com.f1sb.forma1sb.model.repositories;

import com.f1sb.forma1sb.model.Uzenet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UzenetRepository extends JpaRepository<Uzenet, Integer> {

}
