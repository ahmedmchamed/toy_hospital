package com.toyhospital.queryapp.back_end.repositories;

import com.toyhospital.queryapp.back_end.models.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Long> {
}
