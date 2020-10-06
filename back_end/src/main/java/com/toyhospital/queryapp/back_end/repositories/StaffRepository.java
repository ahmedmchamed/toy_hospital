package com.toyhospital.queryapp.back_end.repositories;

import com.toyhospital.queryapp.back_end.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
}
