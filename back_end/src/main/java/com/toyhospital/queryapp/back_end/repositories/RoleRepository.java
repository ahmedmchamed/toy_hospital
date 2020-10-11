package com.toyhospital.queryapp.back_end.repositories;

import com.toyhospital.queryapp.back_end.models.ERole;
import com.toyhospital.queryapp.back_end.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}
