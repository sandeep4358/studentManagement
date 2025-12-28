package com.smanagement.repository;

import com.smanagement.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
    School findBySchoolId(String schoolId);
}

