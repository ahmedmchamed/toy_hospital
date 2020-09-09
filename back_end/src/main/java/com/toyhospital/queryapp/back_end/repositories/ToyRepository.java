package com.toyhospital.queryapp.back_end.repositories;

import com.toyhospital.queryapp.back_end.models.Toy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToyRepository extends JpaRepository<Toy, Long> {
}
