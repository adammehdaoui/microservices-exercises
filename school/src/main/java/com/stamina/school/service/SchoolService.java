package com.stamina.school.service;

import com.stamina.school.entity.School;
import com.stamina.school.repository.SchoolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;

    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public List<School> findAllSchools() {
        return schoolRepository.findAll();
    }

    public School getSchoolById(Long id) {
        return schoolRepository.findById(id).orElse(null);
    }
}


