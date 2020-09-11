package com.toyhospital.queryapp.back_end.repositories;

import com.toyhospital.queryapp.back_end.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
