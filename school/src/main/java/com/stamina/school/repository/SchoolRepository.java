package com.stamina.school.repository;

import com.stamina.school.entity.School;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
}
